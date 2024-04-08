package com.client.infrastructure.drivenadapters.datanode;

import com.client.infrastructure.helpers.UploadFileStreamObserver;
import com.datanode.grpc.*;
import com.google.protobuf.ByteString;
import io.grpc.ManagedChannel;
import io.grpc.netty.shaded.io.grpc.netty.NettyChannelBuilder;
import io.grpc.stub.StreamObserver;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Iterator;

@Service
@Slf4j
public class DataNodeClient {

    @Value("${dataNode.port}")
    private String dataNodePort;

    public void upload(String blockName, String dataNodeHost){
        ManagedChannel channel = NettyChannelBuilder.forTarget(dataNodeHost + ":" + dataNodePort).usePlaintext().build();
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

    public byte[] download(String blockName, String dataNodeHost){
        ManagedChannel channel = NettyChannelBuilder.forTarget(dataNodeHost + ":" + dataNodePort).usePlaintext().build();
        DataNodeGrpc.DataNodeBlockingStub stub = DataNodeGrpc.newBlockingStub(channel);
        ByteArrayOutputStream responseBytes = new ByteArrayOutputStream();
        Iterator<BlockData> response = stub.downloadBlock(BlockInfo.newBuilder().setBlockName(blockName).build());
        response.forEachRemaining(blockData -> {
            try {
                responseBytes.write(blockData.toByteArray());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        return responseBytes.toByteArray();
    }

}
