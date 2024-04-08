package com.datanode.infrastructure.entrypoints;

import com.datanode.grpc.*;
import com.datanode.infrastructure.helper.UploadStreamFileObserver;
import com.google.protobuf.ByteString;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;
import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@GrpcService
public class DataNodeGrpcImpl extends DataNodeGrpc.DataNodeImplBase {

    @Override
    public StreamObserver<BlockDataRequest> uploadBlock(StreamObserver<BlockDataResponse> responseObserver) {
        return new UploadStreamFileObserver(responseObserver);
    }

    @Override
    public void downloadBlock(BlockInfo request, StreamObserver<BlockData> responseObserver) {
        try{
            String blockName = request.getBlockName();
            String tempDirPath = System.getProperty("java.io.tmpdir");
            Path path = Paths.get(tempDirPath, "files/dataNode/" + blockName);
            byte[] bytes = Files.readAllBytes(path);
            BufferedInputStream fileStream = new BufferedInputStream(new ByteArrayInputStream(bytes));
            byte[] bufferBytes = new byte[bytes.length];
            int length;
            while ((length = fileStream.read(bufferBytes, 0, bufferBytes.length)) != -1) {
                responseObserver.onNext(BlockData.newBuilder()
                        .setData(ByteString.copyFrom(bufferBytes, 0, length))
                        .build());
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
