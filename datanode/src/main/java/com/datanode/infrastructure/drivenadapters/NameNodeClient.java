package com.datanode.infrastructure.drivenadapters;

import io.grpc.ManagedChannel;
import io.grpc.netty.shaded.io.grpc.netty.NettyChannelBuilder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import com.namenode.grpc.*;

@Service
@Slf4j
public class NameNodeClient {

    public void login(){
        ManagedChannel channel = NettyChannelBuilder.forTarget("localhost:5000").usePlaintext().build();
        LoggingGrpc.LoggingBlockingStub stub = LoggingGrpc.newBlockingStub(channel);
        Message loginResponse = stub.login(Empty.newBuilder().build());
        log.info("Name node login response: {}", loginResponse.getMessage());
    }

}
