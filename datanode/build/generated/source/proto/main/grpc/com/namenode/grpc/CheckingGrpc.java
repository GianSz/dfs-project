package com.namenode.grpc;

import static io.grpc.MethodDescriptor.generateFullMethodName;

/**
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.43.1)",
    comments = "Source: namenode.proto")
@io.grpc.stub.annotations.GrpcGenerated
public final class CheckingGrpc {

  private CheckingGrpc() {}

  public static final String SERVICE_NAME = "Checking";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<com.namenode.grpc.Empty,
      com.namenode.grpc.HealthCheckResponse> getCheckHealthMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "checkHealth",
      requestType = com.namenode.grpc.Empty.class,
      responseType = com.namenode.grpc.HealthCheckResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.namenode.grpc.Empty,
      com.namenode.grpc.HealthCheckResponse> getCheckHealthMethod() {
    io.grpc.MethodDescriptor<com.namenode.grpc.Empty, com.namenode.grpc.HealthCheckResponse> getCheckHealthMethod;
    if ((getCheckHealthMethod = CheckingGrpc.getCheckHealthMethod) == null) {
      synchronized (CheckingGrpc.class) {
        if ((getCheckHealthMethod = CheckingGrpc.getCheckHealthMethod) == null) {
          CheckingGrpc.getCheckHealthMethod = getCheckHealthMethod =
              io.grpc.MethodDescriptor.<com.namenode.grpc.Empty, com.namenode.grpc.HealthCheckResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "checkHealth"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.namenode.grpc.Empty.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.namenode.grpc.HealthCheckResponse.getDefaultInstance()))
              .setSchemaDescriptor(new CheckingMethodDescriptorSupplier("checkHealth"))
              .build();
        }
      }
    }
    return getCheckHealthMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.namenode.grpc.ReplicateBlockRequest,
      com.namenode.grpc.Empty> getReplicateBlockMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "replicateBlock",
      requestType = com.namenode.grpc.ReplicateBlockRequest.class,
      responseType = com.namenode.grpc.Empty.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.namenode.grpc.ReplicateBlockRequest,
      com.namenode.grpc.Empty> getReplicateBlockMethod() {
    io.grpc.MethodDescriptor<com.namenode.grpc.ReplicateBlockRequest, com.namenode.grpc.Empty> getReplicateBlockMethod;
    if ((getReplicateBlockMethod = CheckingGrpc.getReplicateBlockMethod) == null) {
      synchronized (CheckingGrpc.class) {
        if ((getReplicateBlockMethod = CheckingGrpc.getReplicateBlockMethod) == null) {
          CheckingGrpc.getReplicateBlockMethod = getReplicateBlockMethod =
              io.grpc.MethodDescriptor.<com.namenode.grpc.ReplicateBlockRequest, com.namenode.grpc.Empty>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "replicateBlock"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.namenode.grpc.ReplicateBlockRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.namenode.grpc.Empty.getDefaultInstance()))
              .setSchemaDescriptor(new CheckingMethodDescriptorSupplier("replicateBlock"))
              .build();
        }
      }
    }
    return getReplicateBlockMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static CheckingStub newStub(io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<CheckingStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<CheckingStub>() {
        @java.lang.Override
        public CheckingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new CheckingStub(channel, callOptions);
        }
      };
    return CheckingStub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static CheckingBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<CheckingBlockingStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<CheckingBlockingStub>() {
        @java.lang.Override
        public CheckingBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new CheckingBlockingStub(channel, callOptions);
        }
      };
    return CheckingBlockingStub.newStub(factory, channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static CheckingFutureStub newFutureStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<CheckingFutureStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<CheckingFutureStub>() {
        @java.lang.Override
        public CheckingFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new CheckingFutureStub(channel, callOptions);
        }
      };
    return CheckingFutureStub.newStub(factory, channel);
  }

  /**
   */
  public static abstract class CheckingImplBase implements io.grpc.BindableService {

    /**
     */
    public void checkHealth(com.namenode.grpc.Empty request,
        io.grpc.stub.StreamObserver<com.namenode.grpc.HealthCheckResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getCheckHealthMethod(), responseObserver);
    }

    /**
     */
    public void replicateBlock(com.namenode.grpc.ReplicateBlockRequest request,
        io.grpc.stub.StreamObserver<com.namenode.grpc.Empty> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getReplicateBlockMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getCheckHealthMethod(),
            io.grpc.stub.ServerCalls.asyncUnaryCall(
              new MethodHandlers<
                com.namenode.grpc.Empty,
                com.namenode.grpc.HealthCheckResponse>(
                  this, METHODID_CHECK_HEALTH)))
          .addMethod(
            getReplicateBlockMethod(),
            io.grpc.stub.ServerCalls.asyncUnaryCall(
              new MethodHandlers<
                com.namenode.grpc.ReplicateBlockRequest,
                com.namenode.grpc.Empty>(
                  this, METHODID_REPLICATE_BLOCK)))
          .build();
    }
  }

  /**
   */
  public static final class CheckingStub extends io.grpc.stub.AbstractAsyncStub<CheckingStub> {
    private CheckingStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected CheckingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new CheckingStub(channel, callOptions);
    }

    /**
     */
    public void checkHealth(com.namenode.grpc.Empty request,
        io.grpc.stub.StreamObserver<com.namenode.grpc.HealthCheckResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getCheckHealthMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void replicateBlock(com.namenode.grpc.ReplicateBlockRequest request,
        io.grpc.stub.StreamObserver<com.namenode.grpc.Empty> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getReplicateBlockMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   */
  public static final class CheckingBlockingStub extends io.grpc.stub.AbstractBlockingStub<CheckingBlockingStub> {
    private CheckingBlockingStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected CheckingBlockingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new CheckingBlockingStub(channel, callOptions);
    }

    /**
     */
    public com.namenode.grpc.HealthCheckResponse checkHealth(com.namenode.grpc.Empty request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getCheckHealthMethod(), getCallOptions(), request);
    }

    /**
     */
    public com.namenode.grpc.Empty replicateBlock(com.namenode.grpc.ReplicateBlockRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getReplicateBlockMethod(), getCallOptions(), request);
    }
  }

  /**
   */
  public static final class CheckingFutureStub extends io.grpc.stub.AbstractFutureStub<CheckingFutureStub> {
    private CheckingFutureStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected CheckingFutureStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new CheckingFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.namenode.grpc.HealthCheckResponse> checkHealth(
        com.namenode.grpc.Empty request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getCheckHealthMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.namenode.grpc.Empty> replicateBlock(
        com.namenode.grpc.ReplicateBlockRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getReplicateBlockMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_CHECK_HEALTH = 0;
  private static final int METHODID_REPLICATE_BLOCK = 1;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final CheckingImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(CheckingImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_CHECK_HEALTH:
          serviceImpl.checkHealth((com.namenode.grpc.Empty) request,
              (io.grpc.stub.StreamObserver<com.namenode.grpc.HealthCheckResponse>) responseObserver);
          break;
        case METHODID_REPLICATE_BLOCK:
          serviceImpl.replicateBlock((com.namenode.grpc.ReplicateBlockRequest) request,
              (io.grpc.stub.StreamObserver<com.namenode.grpc.Empty>) responseObserver);
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
        default:
          throw new AssertionError();
      }
    }
  }

  private static abstract class CheckingBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    CheckingBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return com.namenode.grpc.Namenode.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("Checking");
    }
  }

  private static final class CheckingFileDescriptorSupplier
      extends CheckingBaseDescriptorSupplier {
    CheckingFileDescriptorSupplier() {}
  }

  private static final class CheckingMethodDescriptorSupplier
      extends CheckingBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    CheckingMethodDescriptorSupplier(String methodName) {
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
      synchronized (CheckingGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new CheckingFileDescriptorSupplier())
              .addMethod(getCheckHealthMethod())
              .addMethod(getReplicateBlockMethod())
              .build();
        }
      }
    }
    return result;
  }
}
