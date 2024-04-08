package com.datanode.grpc;

import static io.grpc.MethodDescriptor.generateFullMethodName;

/**
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.43.1)",
    comments = "Source: datanode.proto")
@io.grpc.stub.annotations.GrpcGenerated
public final class DataNodeGrpc {

  private DataNodeGrpc() {}

  public static final String SERVICE_NAME = "DataNode";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<com.datanode.grpc.BlockDataRequest,
      com.datanode.grpc.BlockDataResponse> getUploadBlockMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "uploadBlock",
      requestType = com.datanode.grpc.BlockDataRequest.class,
      responseType = com.datanode.grpc.BlockDataResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.CLIENT_STREAMING)
  public static io.grpc.MethodDescriptor<com.datanode.grpc.BlockDataRequest,
      com.datanode.grpc.BlockDataResponse> getUploadBlockMethod() {
    io.grpc.MethodDescriptor<com.datanode.grpc.BlockDataRequest, com.datanode.grpc.BlockDataResponse> getUploadBlockMethod;
    if ((getUploadBlockMethod = DataNodeGrpc.getUploadBlockMethod) == null) {
      synchronized (DataNodeGrpc.class) {
        if ((getUploadBlockMethod = DataNodeGrpc.getUploadBlockMethod) == null) {
          DataNodeGrpc.getUploadBlockMethod = getUploadBlockMethod =
              io.grpc.MethodDescriptor.<com.datanode.grpc.BlockDataRequest, com.datanode.grpc.BlockDataResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.CLIENT_STREAMING)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "uploadBlock"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.datanode.grpc.BlockDataRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.datanode.grpc.BlockDataResponse.getDefaultInstance()))
              .setSchemaDescriptor(new DataNodeMethodDescriptorSupplier("uploadBlock"))
              .build();
        }
      }
    }
    return getUploadBlockMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.datanode.grpc.BlockInfo,
      com.datanode.grpc.BlockData> getDownloadBlockMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "downloadBlock",
      requestType = com.datanode.grpc.BlockInfo.class,
      responseType = com.datanode.grpc.BlockData.class,
      methodType = io.grpc.MethodDescriptor.MethodType.SERVER_STREAMING)
  public static io.grpc.MethodDescriptor<com.datanode.grpc.BlockInfo,
      com.datanode.grpc.BlockData> getDownloadBlockMethod() {
    io.grpc.MethodDescriptor<com.datanode.grpc.BlockInfo, com.datanode.grpc.BlockData> getDownloadBlockMethod;
    if ((getDownloadBlockMethod = DataNodeGrpc.getDownloadBlockMethod) == null) {
      synchronized (DataNodeGrpc.class) {
        if ((getDownloadBlockMethod = DataNodeGrpc.getDownloadBlockMethod) == null) {
          DataNodeGrpc.getDownloadBlockMethod = getDownloadBlockMethod =
              io.grpc.MethodDescriptor.<com.datanode.grpc.BlockInfo, com.datanode.grpc.BlockData>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.SERVER_STREAMING)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "downloadBlock"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.datanode.grpc.BlockInfo.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.datanode.grpc.BlockData.getDefaultInstance()))
              .setSchemaDescriptor(new DataNodeMethodDescriptorSupplier("downloadBlock"))
              .build();
        }
      }
    }
    return getDownloadBlockMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static DataNodeStub newStub(io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<DataNodeStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<DataNodeStub>() {
        @java.lang.Override
        public DataNodeStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new DataNodeStub(channel, callOptions);
        }
      };
    return DataNodeStub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static DataNodeBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<DataNodeBlockingStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<DataNodeBlockingStub>() {
        @java.lang.Override
        public DataNodeBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new DataNodeBlockingStub(channel, callOptions);
        }
      };
    return DataNodeBlockingStub.newStub(factory, channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static DataNodeFutureStub newFutureStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<DataNodeFutureStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<DataNodeFutureStub>() {
        @java.lang.Override
        public DataNodeFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new DataNodeFutureStub(channel, callOptions);
        }
      };
    return DataNodeFutureStub.newStub(factory, channel);
  }

  /**
   */
  public static abstract class DataNodeImplBase implements io.grpc.BindableService {

    /**
     */
    public io.grpc.stub.StreamObserver<com.datanode.grpc.BlockDataRequest> uploadBlock(
        io.grpc.stub.StreamObserver<com.datanode.grpc.BlockDataResponse> responseObserver) {
      return io.grpc.stub.ServerCalls.asyncUnimplementedStreamingCall(getUploadBlockMethod(), responseObserver);
    }

    /**
     */
    public void downloadBlock(com.datanode.grpc.BlockInfo request,
        io.grpc.stub.StreamObserver<com.datanode.grpc.BlockData> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getDownloadBlockMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getUploadBlockMethod(),
            io.grpc.stub.ServerCalls.asyncClientStreamingCall(
              new MethodHandlers<
                com.datanode.grpc.BlockDataRequest,
                com.datanode.grpc.BlockDataResponse>(
                  this, METHODID_UPLOAD_BLOCK)))
          .addMethod(
            getDownloadBlockMethod(),
            io.grpc.stub.ServerCalls.asyncServerStreamingCall(
              new MethodHandlers<
                com.datanode.grpc.BlockInfo,
                com.datanode.grpc.BlockData>(
                  this, METHODID_DOWNLOAD_BLOCK)))
          .build();
    }
  }

  /**
   */
  public static final class DataNodeStub extends io.grpc.stub.AbstractAsyncStub<DataNodeStub> {
    private DataNodeStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected DataNodeStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new DataNodeStub(channel, callOptions);
    }

    /**
     */
    public io.grpc.stub.StreamObserver<com.datanode.grpc.BlockDataRequest> uploadBlock(
        io.grpc.stub.StreamObserver<com.datanode.grpc.BlockDataResponse> responseObserver) {
      return io.grpc.stub.ClientCalls.asyncClientStreamingCall(
          getChannel().newCall(getUploadBlockMethod(), getCallOptions()), responseObserver);
    }

    /**
     */
    public void downloadBlock(com.datanode.grpc.BlockInfo request,
        io.grpc.stub.StreamObserver<com.datanode.grpc.BlockData> responseObserver) {
      io.grpc.stub.ClientCalls.asyncServerStreamingCall(
          getChannel().newCall(getDownloadBlockMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   */
  public static final class DataNodeBlockingStub extends io.grpc.stub.AbstractBlockingStub<DataNodeBlockingStub> {
    private DataNodeBlockingStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected DataNodeBlockingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new DataNodeBlockingStub(channel, callOptions);
    }

    /**
     */
    public java.util.Iterator<com.datanode.grpc.BlockData> downloadBlock(
        com.datanode.grpc.BlockInfo request) {
      return io.grpc.stub.ClientCalls.blockingServerStreamingCall(
          getChannel(), getDownloadBlockMethod(), getCallOptions(), request);
    }
  }

  /**
   */
  public static final class DataNodeFutureStub extends io.grpc.stub.AbstractFutureStub<DataNodeFutureStub> {
    private DataNodeFutureStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected DataNodeFutureStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new DataNodeFutureStub(channel, callOptions);
    }
  }

  private static final int METHODID_DOWNLOAD_BLOCK = 0;
  private static final int METHODID_UPLOAD_BLOCK = 1;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final DataNodeImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(DataNodeImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_DOWNLOAD_BLOCK:
          serviceImpl.downloadBlock((com.datanode.grpc.BlockInfo) request,
              (io.grpc.stub.StreamObserver<com.datanode.grpc.BlockData>) responseObserver);
          break;
        default:
          throw new AssertionError();
      }
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public io.grpc.stub.StreamObserver<Req> invoke(
        io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_UPLOAD_BLOCK:
          return (io.grpc.stub.StreamObserver<Req>) serviceImpl.uploadBlock(
              (io.grpc.stub.StreamObserver<com.datanode.grpc.BlockDataResponse>) responseObserver);
        default:
          throw new AssertionError();
      }
    }
  }

  private static abstract class DataNodeBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    DataNodeBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return com.datanode.grpc.Datanode.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("DataNode");
    }
  }

  private static final class DataNodeFileDescriptorSupplier
      extends DataNodeBaseDescriptorSupplier {
    DataNodeFileDescriptorSupplier() {}
  }

  private static final class DataNodeMethodDescriptorSupplier
      extends DataNodeBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    DataNodeMethodDescriptorSupplier(String methodName) {
      this.methodName = methodName;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.MethodDescriptor getMethodDescriptor() {
      return getServiceDescriptor().findMethodByName(methodName);
    }
  }

  private static volatile io.grpc.ServiceDescriptor serviceDescriptor;

  public static io.grpc.ServiceDescriptor getServiceDescriptor() {
    io.grpc.ServiceDescriptor result = serviceDescriptor;
    if (result == null) {
      synchronized (DataNodeGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new DataNodeFileDescriptorSupplier())
              .addMethod(getUploadBlockMethod())
              .addMethod(getDownloadBlockMethod())
              .build();
        }
      }
    }
    return result;
  }
}
