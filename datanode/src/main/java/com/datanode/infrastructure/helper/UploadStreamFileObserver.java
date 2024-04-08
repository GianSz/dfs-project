package com.datanode.infrastructure.helper;

import com.datanode.grpc.BlockDataRequest;
import com.datanode.grpc.BlockDataResponse;
import io.grpc.stub.StreamObserver;
import lombok.extern.slf4j.Slf4j;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

@Slf4j
public class UploadStreamFileObserver implements StreamObserver<BlockDataRequest> {

    private String blockName = "";
    private final StreamObserver<BlockDataResponse> responseObserver;

    public UploadStreamFileObserver(StreamObserver<BlockDataResponse> responseObserver) {
        this.responseObserver = responseObserver;
    }

    @Override
    public void onNext(BlockDataRequest request) {
        log.info("Received request: {}", request.getBlockName());
        if(request.hasField(request.getDescriptorForType().findFieldByName("data"))){
            try{
                String path = "";
                String tempDirPath = System.getProperty("java.io.tmpdir");
                Path filePath = Files.createDirectories(Paths.get(tempDirPath, "files/dataNode/"));
                File fileDir = filePath.toFile();
                if("".equals(this.blockName)){
                    this.blockName = request.getBlockName();
                    File file = new File(fileDir, request.getBlockName());
                    if(file.createNewFile()){
                        path = file.getAbsolutePath();
                        Files.write(Path.of(path), request.getData().toByteArray(), StandardOpenOption.APPEND);
                    }else{
                        throw new IOException("Filename already exist");
                    }
                }else{
                    Files.write(Path.of("/tmp/files/dataNode/" + this.blockName), request.getData().toByteArray(), StandardOpenOption.APPEND);
                }
            }catch (IOException e){
                e.printStackTrace();
            }
        }
    }

    @Override
    public void onError(Throwable t) {

    }

    @Override
    public void onCompleted() {
//        try{
//            String tempDirPath = System.getProperty("java.io.tmpdir");
//            Path uploadPath = Files.createDirectories(Paths.get(tempDirPath, "files/dataNode/" ));
//            File uploadDir = uploadPath.toFile();
//            FileOutputStream fileOutputStream = new FileOutputStream(uploadDir.getAbsolutePath() + this.blockName);
//            fileBytes.writeTo(fileOutputStream);
//            fileOutputStream.close();
//        }catch (IOException e){
//            e.printStackTrace();
//        }
        this.blockName = "";
        BlockDataResponse blockDataResponse = BlockDataResponse.newBuilder().setMessage("OK").build();
        responseObserver.onNext(blockDataResponse);
        responseObserver.onCompleted();
    }
}
