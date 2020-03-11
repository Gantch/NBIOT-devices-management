package com.gantch.nbiotmanagement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author lcw332
 */
@SpringBootApplication
@ComponentScan({"com.gantch.nbiotmanagement", "com.gantch.nbiotmanagement.websocket"})
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
