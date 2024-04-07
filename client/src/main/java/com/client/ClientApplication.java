package com.client;

import com.client.infrastructure.drivenadapters.namenode.NameNodeClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ClientApplication implements CommandLineRunner {

    @Autowired
    NameNodeClient nameNodeGrpc;

    public static void main(String[] args)  {
        SpringApplication.run(ClientApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
//        nameNodeGrpc.upload("test", 12L);
    }
}
