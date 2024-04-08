package com.namenode.grpc;

import static io.grpc.MethodDescriptor.generateFullMethodName;

/**
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.43.1)",
    comments = "Source: namenode.proto")
@io.grpc.stub.annotations.GrpcGenerated
public final class LoggingGrpc {

  private LoggingGrpc() {}

  public static final String SERVICE_NAME = "Logging";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<com.namenode.grpc.Empty,
      com.namenode.grpc.Message> getLoginMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "login",
      requestType = com.namenode.grpc.Empty.class,
      responseType = com.namenode.grpc.Message.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.namenode.grpc.Empty,
      com.namenode.grpc.Message> getLoginMethod() {
    io.grpc.MethodDescriptor<com.namenode.grpc.Empty, com.namenode.grpc.Message> getLoginMethod;
    if ((getLoginMethod = LoggingGrpc.getLoginMethod) == null) {
      synchronized (LoggingGrpc.class) {
        if ((getLoginMethod = LoggingGrpc.getLoginMethod) == null) {
          LoggingGrpc.getLoginMethod = getLoginMethod =
              io.grpc.MethodDescriptor.<com.namenode.grpc.Empty, com.namenode.grpc.Message>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "login"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.namenode.grpc.Empty.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.namenode.grpc.Message.getDefaultInstance()))
              .setSchemaDescriptor(new LoggingMethodDescriptorSupplier("login"))
              .build();
        }
      }
    }
    return getLoginMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static LoggingStub newStub(io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<LoggingStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<LoggingStub>() {
        @java.lang.Override
        public LoggingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new LoggingStub(channel, callOptions);
        }
      };
    return LoggingStub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static LoggingBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<LoggingBlockingStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<LoggingBlockingStub>() {
        @java.lang.Override
        public LoggingBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new LoggingBlockingStub(channel, callOptions);
        }
      };
    return LoggingBlockingStub.newStub(factory, channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static LoggingFutureStub newFutureStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<LoggingFutureStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<LoggingFutureStub>() {
        @java.lang.Override
        public LoggingFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new LoggingFutureStub(channel, callOptions);
        }
      };
    return LoggingFutureStub.newStub(factory, channel);
  }

  /**
   */
  public static abstract class LoggingImplBase implements io.grpc.BindableService {

    /**
     */
    public void login(com.namenode.grpc.Empty request,
        io.grpc.stub.StreamObserver<com.namenode.grpc.Message> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getLoginMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getLoginMethod(),
            io.grpc.stub.ServerCalls.asyncUnaryCall(
              new MethodHandlers<
                com.namenode.grpc.Empty,
                com.namenode.grpc.Message>(
                  this, METHODID_LOGIN)))
          .build();
    }
  }

  /**
   */
  public static final class LoggingStub extends io.grpc.stub.AbstractAsyncStub<LoggingStub> {
    private LoggingStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected LoggingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new LoggingStub(channel, callOptions);
    }

    /**
     */
    public void login(com.namenode.grpc.Empty request,
        io.grpc.stub.StreamObserver<com.namenode.grpc.Message> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getLoginMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   */
  public static final class LoggingBlockingStub extends io.grpc.stub.AbstractBlockingStub<LoggingBlockingStub> {
    private LoggingBlockingStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected LoggingBlockingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new LoggingBlockingStub(channel, callOptions);
    }

    /**
     */
    public com.namenode.grpc.Message login(com.namenode.grpc.Empty request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getLoginMethod(), getCallOptions(), request);
    }
  }

  /**
   */
  public static final class LoggingFutureStub extends io.grpc.stub.AbstractFutureStub<LoggingFutureStub> {
    private LoggingFutureStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected LoggingFutureStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new LoggingFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.namenode.grpc.Message> login(
        com.namenode.grpc.Empty request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getLoginMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_LOGIN = 0;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final LoggingImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(LoggingImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_LOGIN:
          serviceImpl.login((com.namenode.grpc.Empty) request,
              (io.grpc.stub.StreamObserver<com.namenode.grpc.Message>) responseObserver);
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

  private static abstract class LoggingBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    LoggingBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return com.namenode.grpc.Namenode.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("Logging");
    }
  }

  private static final class LoggingFileDescriptorSupplier
      extends LoggingBaseDescriptorSupplier {
    LoggingFileDescriptorSupplier() {}
  }

  private static final class LoggingMethodDescriptorSupplier
      extends LoggingBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    LoggingMethodDescriptorSupplier(String methodName) {
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
      synchronized (LoggingGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new LoggingFileDescriptorSupplier())
              .addMethod(getLoginMethod())
              .build();
        }
      }
    }
    return result;
  }
}
