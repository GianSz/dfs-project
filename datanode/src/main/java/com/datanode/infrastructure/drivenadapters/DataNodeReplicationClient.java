package com.datanode.infrastructure.drivenadapters;

import com.datanode.grpc.BlockDataRequest;
import com.datanode.grpc.DataNodeGrpc;
import com.datanode.infrastructure.helper.UploadFileStreamObserver;
import com.google.protobuf.ByteString;
import io.grpc.ManagedChannel;
import io.grpc.netty.shaded.io.grpc.netty.NettyChannelBuilder;
import io.grpc.stub.StreamObserver;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
@Slf4j
public class DataNodeReplicationClient {

    @Value("${grpc.server.port}")
    private String grpcPort;

    public void upload(String blockName, String dataNodeHost){
        ManagedChannel channel = NettyChannelBuilder.forTarget(dataNodeHost + ":" + grpcPort).usePlaintext().build();
        DataNodeGrpc.DataNodeStub stub = DataNodeGrpc.newStub(channel);
        UploadFileStreamObserver responseObserver = new UploadFileStreamObserver();
        StreamObserver<BlockDataRequest> requestObserver = stub.uploadBlock(responseObserver);

        String tempDirPath = System.getProperty("java.io.tmpdir");
        Path path = Paths.get(tempDirPath, "files/dataNode/" + blockName);
        String filePath = tempDirPath + "/files/dataNode/" + blockName;

        log.info("Replicating block {} to {}", blockName, dataNodeHost);

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
            channel.shutdown();
            e.printStackTrace();
        }

        requestObserver.onCompleted();
        responseObserver.awaitCompletion();
        channel.shutdown();
    }

}
