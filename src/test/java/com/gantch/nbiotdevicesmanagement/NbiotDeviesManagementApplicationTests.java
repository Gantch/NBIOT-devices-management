package com.gantch.nbiotdevicesmanagement;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.UUID;

public class NbiotDeviesManagementApplicationTests {

    @Test
    void contextLoads() {

    }
    public static String getRandomUUID(){
        UUID uuid = UUID.randomUUID();
        String str = uuid.toString();
        return str;
    }

    public static void main(String[] args) {
        System.out.println(getRandomUUID());
    }
}
