package com.client.infrastructure.drivenadapters.datanode;

import com.client.infrastructure.helpers.UploadFileStreamObserver;
import com.datanode.grpc.BlockDataRequest;
import com.datanode.grpc.BlockDataResponse;
import com.datanode.grpc.DataNodeGrpc;
import com.google.protobuf.ByteString;
import com.namenode.grpc.NameNodeGrpc;
import io.grpc.ManagedChannel;
import io.grpc.netty.shaded.io.grpc.netty.NettyChannelBuilder;
import io.grpc.stub.StreamObserver;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
@Slf4j
public class DataNodeClient {

    private final String dataNodeHost = "localhost:9091";


    public void upload(String blockName){
        ManagedChannel channel = NettyChannelBuilder.forTarget(dataNodeHost).usePlaintext().build();
        DataNodeGrpc.DataNodeStub stub = DataNodeGrpc.newStub(channel);
        UploadFileStreamObserver responseObserver = new UploadFileStreamObserver();
        StreamObserver<BlockDataRequest> requestObserver = stub.uploadBlock(responseObserver);

        String tempDirPath = System.getProperty("java.io.tmpdir");
        Path path = Paths.get(tempDirPath, "files/" + blockName);
        String filePath = tempDirPath + "/files/" + blockName;

        try (FileInputStream fileInputStream = new FileInputStream(filePath)) {
            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = fileInputStream.read(buffer)) != -1) {
                BlockDataRequest request = BlockDataRequest.newBuilder().setBlockName(blockName)
                        .setData(ByteString.copyFrom(buffer, 0, bytesRead))
                        .build();
                requestObserver.onNext(request);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        requestObserver.onCompleted();
        responseObserver.awaitCompletion();
        channel.shutdown();
    }

}
