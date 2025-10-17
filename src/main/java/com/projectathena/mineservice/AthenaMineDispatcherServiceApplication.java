package com.projectathena.mineservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class AthenaMineDispatcherServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(AthenaMineDispatcherServiceApplication.class, args);
    }

}
