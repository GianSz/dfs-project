package com.client.infrastructure.helpers;

import com.datanode.grpc.BlockDataResponse;
import io.grpc.stub.StreamObserver;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class UploadFileStreamObserver implements StreamObserver<BlockDataResponse> {

    private final Object lock = new Object();
    private boolean completed = false;

    @Override
    public void onNext(BlockDataResponse value) {
        log.info("Data Node response: {}", value.getMessage());
    }

    @Override
    public void onError(Throwable t) {
        log.error("Error occurred: " + t.getMessage());
        synchronized (lock) {
            completed = true;
            lock.notifyAll();
        }
    }

    @Override
    public void onCompleted() {
        log.info("File uploaded successfully!");
        synchronized (lock) {
            completed = true;
            lock.notifyAll();
        }
    }

    public void awaitCompletion() {
        synchronized (lock) {
            while (!completed) {
                try {
                    lock.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
