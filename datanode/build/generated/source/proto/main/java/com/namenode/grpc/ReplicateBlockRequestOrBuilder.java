// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: namenode.proto

package com.namenode.grpc;

public interface ReplicateBlockRequestOrBuilder extends
    // @@protoc_insertion_point(interface_extends:ReplicateBlockRequest)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <code>string blockName = 1;</code>
   * @return The blockName.
   */
  java.lang.String getBlockName();
  /**
   * <code>string blockName = 1;</code>
   * @return The bytes for blockName.
   */
  com.google.protobuf.ByteString
      getBlockNameBytes();

  /**
   * <code>string dataNode = 2;</code>
   * @return The dataNode.
   */
  java.lang.String getDataNode();
  /**
   * <code>string dataNode = 2;</code>
   * @return The bytes for dataNode.
   */
  com.google.protobuf.ByteString
      getDataNodeBytes();
}
