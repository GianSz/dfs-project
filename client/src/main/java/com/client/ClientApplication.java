package com.client;

import com.client.infrastructure.drivenadapters.namenode.NameNodeClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@Slf4j
public class ClientApplication implements CommandLineRunner {

    @Autowired
    NameNodeClient nameNodeGrpc;

    public static void main(String[] args)  {
        SpringApplication.run(ClientApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        log.info("Client started...");
    }
}
