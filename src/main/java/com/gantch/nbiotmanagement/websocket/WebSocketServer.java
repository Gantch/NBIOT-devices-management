package com.gantch.nbiotmanagement.websocket;

import com.gantch.nbiotmanagement.pojo.DeviceAlarmLog;
import com.gantch.nbiotmanagement.pojo.DeviceRelation;
import com.gantch.nbiotmanagement.service.DeviceGroupService;
import com.gantch.nbiotmanagement.service.DeviceService;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author lcw332
 * Date 2020-03-09-9:10
 * Description:  NBIotResolution-master , com.gantch.nbiot.websocket
 **/
@Slf4j
@ServerEndpoint("/api/v1/nbiot/device/nbwebsokcet")
@Component
public class WebSocketServer {

    @Autowired
    private DeviceService deviceService;

    @Autowired
    private DeviceGroupService deviceGroupService;

    private static DeviceService deviceStaticService;
    private static DeviceGroupService deviceGroupStaticService;

    //静态变量，用来记录当前在线连接数。应该把它设计成线程安全的。
    private static Integer onlineCount = 0;

    private Session session;

    private static Logger logger = LoggerFactory.getLogger(WebSocketServer.class);

    // 以下都是单例成员
    public static Map<String,Set<Session>> deviceSessionMap = new ConcurrentHashMap<>();

    private int customerId ;

    //向客户端发送 heartMessage
    private String heartMessage = "@heart";

    private List<String> deviceIdList;
    private List<DeviceAlarmLog> alarmList;


    @PostConstruct
    public void init(){
        deviceStaticService = deviceService;
        deviceGroupStaticService = deviceGroupService;
    }

    /**
     * 建立连接
     * @param session
     */
    @OnOpen
    public void onOpen(Session session) {
        this.session = session;
        addOnlineCount();           //在线数加1
        logger.info("有新连接加入！当前在线人数为" + getOnlineCount());
    }

    /**
     * 连接关闭调用的方法
     */
    @OnClose
    public void onClose() {
        deviceSessionMap.get(customerId).remove(this.session);
        subOnlineCount();           //在线数减1
        log.info("有一连接关闭！当前在线人数为" + getOnlineCount());
    }

    /***
     * @param message 客户端发送过来的消息
     * @param session
     */
    @OnMessage
    public void onMessage(String message, Session session)throws Exception{
        logger.info("客户端消息: " + message);

        if(heartMessage.equals(message)) {
            //心跳
            sendMessage(heartMessage,this.session);
        }else{
            //查数据库设备信息 传入customerId 获取DeviceId再去查log
            JsonObject object = (JsonObject) new JsonParser().parse(message);
            Integer customerId = object.get("customerId").getAsInt();
            this.customerId = customerId;
            //根据用户Id查询所有设备
            deviceIdList = new ArrayList<>();
            List<DeviceRelation> deviceList=deviceStaticService.getCustomerDevices(customerId,100,1);
            for (int i = 0; i < deviceList.size(); i++) {
                deviceIdList.add(deviceList.get(i).getId());
            }
            //查找数据库设备信息
            alarmList = new ArrayList<>();
            for (int i = 0; i < deviceIdList.size(); i++) {
                alarmList.addAll(deviceStaticService.getLatestMessage(deviceIdList.get(i)));
            }
            String result = new Gson().toJson(alarmList);
            sendMessage(result,session);

            // 保存 Session
            if(deviceSessionMap.containsKey(customerId)){
                deviceSessionMap.get(customerId).add(session);
            }else{
                Set<Session> s = new HashSet<>();
                s.add(this.session);
                deviceSessionMap.put(String.valueOf(customerId),s);
            }
        }

    }



    public void sendMessage(String message, Session session) throws IOException {
        System.out.println(message);
        session.getBasicRemote().sendText(message);
    }

    public static synchronized int getOnlineCount() {
        return onlineCount;
    }

    public static synchronized void addOnlineCount() {
        WebSocketServer.onlineCount++;
    }

    public static synchronized void subOnlineCount() {
        WebSocketServer.onlineCount--;
    }
}
