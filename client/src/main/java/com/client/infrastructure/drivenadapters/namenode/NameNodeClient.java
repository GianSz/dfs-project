package com.client.infrastructure.drivenadapters.namenode;

import com.namenode.grpc.*;
import io.grpc.ManagedChannel;
import io.grpc.netty.shaded.io.grpc.netty.NettyChannelBuilder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
@Slf4j
public class NameNodeClient {

    @Value("${nameNode.host}")
    private String nameNodeHost;

    @Value("${nameNode.port}")
    private String nameNodePort;

    public Map<String, DataNodes> upload(String fileName, Long fileSize){
        ManagedChannel channel = NettyChannelBuilder.forTarget(nameNodeHost + ":" + nameNodePort).usePlaintext().build();
        FileTransferGrpc.FileTransferBlockingStub stub = FileTransferGrpc.newBlockingStub(channel);
        Response uploadResponse = stub.upload(
                UploadRequest
                        .newBuilder()
                        .setFileName(fileName)
                        .setFileSize(fileSize)
                        .build()
        );
        Map<String, DataNodes> response = uploadResponse.getInfo().getBlocksInfoMap();
        log.info("Response: {}", response);

        return response;
    }

    public Map<String, DataNodes> download(String fileName){
        ManagedChannel channel = NettyChannelBuilder.forTarget(nameNodeHost + ":" + nameNodePort).usePlaintext().build();
        FileTransferGrpc.FileTransferBlockingStub stub = FileTransferGrpc.newBlockingStub(channel);
        Response downloadResponse = stub.download(DownloadRequest.newBuilder().setFileName(fileName).build());
        if(downloadResponse.getMessage().equals("File not found")){
            throw new RuntimeException("File not found");        }
        Map<String, DataNodes> response = downloadResponse.getInfo().getBlocksInfoMap();
        Set<String> keys = response.keySet();
        keys.forEach(key -> log.info("Key: {}", key));
        List<DataNodes> dataNodes =  new ArrayList<>(response.values());
        dataNodes.forEach(dataNode -> log.info("Data node: {}", dataNode));
        return response;
    }

}
