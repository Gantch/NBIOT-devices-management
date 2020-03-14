package com.gantch.nbiotmanagement.test;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimerTask;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author lcw332
 * Date 2020-03-12-10:14
 * Description:  nbiot-management , com.gantch.nbiotmanagement.websocket
 **/
public class ScheduledExecutorServiceTest {
    public static void main(String[] args) {
        ScheduledThreadPoolExecutor scheduled = new ScheduledThreadPoolExecutor(2);
        // 任务需要 3000 ms 才能执行完毕
        TimerTask timerTask = new TimerTask(3000);

        // 延时 1 秒后，按 3 秒的周期执行任务
        scheduled.scheduleAtFixedRate(timerTask, 1000, 3000, TimeUnit.MILLISECONDS);
    }

    private static class TimerTask implements Runnable {

         private final int sleepTime;
         private final SimpleDateFormat dateFormat;

                 public TimerTask(int sleepTime) {
                         this.sleepTime = sleepTime;
                         dateFormat = new SimpleDateFormat("HH:mm:ss");
                     }
                 @Override
                 public void run() {
                         System.out.println("任务开始，当前时间：" + dateFormat.format(new Date()));
                         try {
                             System.out.println("模拟任务运行...");
                             Thread.sleep(sleepTime);
                             } catch (InterruptedException ex) {
                                ex.printStackTrace(System.err);
                             }
                        System.out.println("任务结束，当前时间：" + dateFormat.format(new Date()));
                        System.out.println();
                     }

            }
 }
