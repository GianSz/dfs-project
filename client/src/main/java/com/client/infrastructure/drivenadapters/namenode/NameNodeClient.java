package com.client.infrastructure.drivenadapters.namenode;

import com.namenode.grpc.*;
import io.grpc.ManagedChannel;
import io.grpc.netty.shaded.io.grpc.netty.NettyChannelBuilder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
@Slf4j
public class NameNodeClient {

    private final String nameNodeHost = "localhost:5000";

    public Map<String, DataNodes> upload(String fileName, Long fileSize){
        ManagedChannel channel = NettyChannelBuilder.forTarget(nameNodeHost).usePlaintext().build();
        NameNodeGrpc.NameNodeBlockingStub stub = NameNodeGrpc.newBlockingStub(channel);
        UploadResponse uploadResponse = stub.upload(
                UploadRequest
                        .newBuilder()
                        .setFileName(fileName)
                        .setFileSize(fileSize)
                        .build()
        );
        Map<String, DataNodes> response = uploadResponse.getBlocksInfoMap();
        log.info("Response: {}", response.toString());

        return response;
    }

    public void download(String fileName){
        ManagedChannel channel = NettyChannelBuilder.forTarget(nameNodeHost).usePlaintext().build();
        NameNodeGrpc.NameNodeBlockingStub stub = NameNodeGrpc.newBlockingStub(channel);
        DownloadResponse downloadResponse = stub.download(DownloadRequest.newBuilder().setFileName(fileName).build());
        Map<String, DataNodes> response = downloadResponse.getBlocksInfoMap();
        Set<String> keys = response.keySet();
        keys.forEach(key -> log.info("Key: {}", key));
        List<DataNodes> dataNodes =  new ArrayList<>(response.values());
        dataNodes.forEach(dataNode -> log.info("Data node: {}", dataNode));
    }

}
