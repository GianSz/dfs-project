package com.datanode.infrastructure.entrypoints;

import com.datanode.infrastructure.drivenadapters.DataNodeReplicationClient;
import com.namenode.grpc.*;
import io.grpc.stub.StreamObserver;
import lombok.RequiredArgsConstructor;
import net.devh.boot.grpc.server.service.GrpcService;

@GrpcService
@RequiredArgsConstructor
public class NameNodeGrpcImpl extends CheckingGrpc.CheckingImplBase{

    private final DataNodeReplicationClient dataNodeReplicationClient;

    @Override
    public void checkHealth(Empty request, StreamObserver<HealthCheckResponse> responseObserver) {
        responseObserver.onNext(HealthCheckResponse.newBuilder().setStatus("UP").build());
        responseObserver.onCompleted();
    }

    @Override
    public void replicateBlock(ReplicateBlockRequest request, StreamObserver<Empty> responseObserver) {
        String blockName = request.getBlockName() + ".csv";
        String dataNodeName = request.getDataNode();
        dataNodeReplicationClient.upload(blockName, dataNodeName);
    }
}
