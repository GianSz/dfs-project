package com.datanode;

import com.datanode.infrastructure.drivenadapters.NameNodeClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DataNode implements CommandLineRunner {

    @Autowired
    private NameNodeClient nameNodeClient;

    public static void main(String[] args) {
        SpringApplication.run(DataNode.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        nameNodeClient.login();
    }
}
