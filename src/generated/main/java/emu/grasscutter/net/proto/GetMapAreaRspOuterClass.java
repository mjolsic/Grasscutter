// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: GetMapAreaRsp.proto

package emu.grasscutter.net.proto;

public final class GetMapAreaRspOuterClass {
  private GetMapAreaRspOuterClass() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistryLite registry) {
  }

  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
    registerAllExtensions(
        (com.google.protobuf.ExtensionRegistryLite) registry);
  }
  public interface GetMapAreaRspOrBuilder extends
      // @@protoc_insertion_point(interface_extends:GetMapAreaRsp)
      com.google.protobuf.MessageOrBuilder {

    /**
     * <code>int32 retcode = 14;</code>
     * @return The retcode.
     */
    int getRetcode();

    /**
     * <code>repeated .MapAreaInfo map_area_info_list = 9;</code>
     */
    java.util.List<emu.grasscutter.net.proto.MapAreaInfoOuterClass.MapAreaInfo> 
        getMapAreaInfoListList();
    /**
     * <code>repeated .MapAreaInfo map_area_info_list = 9;</code>
     */
    emu.grasscutter.net.proto.MapAreaInfoOuterClass.MapAreaInfo getMapAreaInfoList(int index);
    /**
     * <code>repeated .MapAreaInfo map_area_info_list = 9;</code>
     */
    int getMapAreaInfoListCount();
    /**
     * <code>repeated .MapAreaInfo map_area_info_list = 9;</code>
     */
    java.util.List<? extends emu.grasscutter.net.proto.MapAreaInfoOuterClass.MapAreaInfoOrBuilder> 
        getMapAreaInfoListOrBuilderList();
    /**
     * <code>repeated .MapAreaInfo map_area_info_list = 9;</code>
     */
    emu.grasscutter.net.proto.MapAreaInfoOuterClass.MapAreaInfoOrBuilder getMapAreaInfoListOrBuilder(
        int index);
  }
  /**
   * <pre>
   * CmdId: 3328
   * EnetChannelId: 0
   * EnetIsReliable: true
   * </pre>
   *
   * Protobuf type {@code GetMapAreaRsp}
   */
  public static final class GetMapAreaRsp extends
      com.google.protobuf.GeneratedMessageV3 implements
      // @@protoc_insertion_point(message_implements:GetMapAreaRsp)
      GetMapAreaRspOrBuilder {
  private static final long serialVersionUID = 0L;
    // Use GetMapAreaRsp.newBuilder() to construct.
    private GetMapAreaRsp(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
      super(builder);
    }
    private GetMapAreaRsp() {
      mapAreaInfoList_ = java.util.Collections.emptyList();
    }

    @java.lang.Override
    @SuppressWarnings({"unused"})
    protected java.lang.Object newInstance(
        UnusedPrivateParameter unused) {
      return new GetMapAreaRsp();
    }

    @java.lang.Override
    public final com.google.protobuf.UnknownFieldSet
    getUnknownFields() {
      return this.unknownFields;
    }
    private GetMapAreaRsp(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      this();
      if (extensionRegistry == null) {
        throw new java.lang.NullPointerException();
      }
      int mutable_bitField0_ = 0;
      com.google.protobuf.UnknownFieldSet.Builder unknownFields =
          com.google.protobuf.UnknownFieldSet.newBuilder();
      try {
        boolean done = false;
        while (!done) {
          int tag = input.readTag();
          switch (tag) {
            case 0:
              done = true;
              break;
            case 74: {
              if (!((mutable_bitField0_ & 0x00000001) != 0)) {
                mapAreaInfoList_ = new java.util.ArrayList<emu.grasscutter.net.proto.MapAreaInfoOuterClass.MapAreaInfo>();
                mutable_bitField0_ |= 0x00000001;
              }
              mapAreaInfoList_.add(
                  input.readMessage(emu.grasscutter.net.proto.MapAreaInfoOuterClass.MapAreaInfo.parser(), extensionRegistry));
              break;
            }
            case 112: {

              retcode_ = input.readInt32();
              break;
            }
            default: {
              if (!parseUnknownField(
                  input, unknownFields, extensionRegistry, tag)) {
                done = true;
              }
              break;
            }
          }
        }
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        throw e.setUnfinishedMessage(this);
      } catch (com.google.protobuf.UninitializedMessageException e) {
        throw e.asInvalidProtocolBufferException().setUnfinishedMessage(this);
      } catch (java.io.IOException e) {
        throw new com.google.protobuf.InvalidProtocolBufferException(
            e).setUnfinishedMessage(this);
      } finally {
        if (((mutable_bitField0_ & 0x00000001) != 0)) {
          mapAreaInfoList_ = java.util.Collections.unmodifiableList(mapAreaInfoList_);
        }
        this.unknownFields = unknownFields.build();
        makeExtensionsImmutable();
      }
    }
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return emu.grasscutter.net.proto.GetMapAreaRspOuterClass.internal_static_GetMapAreaRsp_descriptor;
    }

    @java.lang.Override
    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return emu.grasscutter.net.proto.GetMapAreaRspOuterClass.internal_static_GetMapAreaRsp_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              emu.grasscutter.net.proto.GetMapAreaRspOuterClass.GetMapAreaRsp.class, emu.grasscutter.net.proto.GetMapAreaRspOuterClass.GetMapAreaRsp.Builder.class);
    }

    public static final int RETCODE_FIELD_NUMBER = 14;
    private int retcode_;
    /**
     * <code>int32 retcode = 14;</code>
     * @return The retcode.
     */
    @java.lang.Override
    public int getRetcode() {
      return retcode_;
    }

    public static final int MAP_AREA_INFO_LIST_FIELD_NUMBER = 9;
    private java.util.List<emu.grasscutter.net.proto.MapAreaInfoOuterClass.MapAreaInfo> mapAreaInfoList_;
    /**
     * <code>repeated .MapAreaInfo map_area_info_list = 9;</code>
     */
    @java.lang.Override
    public java.util.List<emu.grasscutter.net.proto.MapAreaInfoOuterClass.MapAreaInfo> getMapAreaInfoListList() {
      return mapAreaInfoList_;
    }
    /**
     * <code>repeated .MapAreaInfo map_area_info_list = 9;</code>
     */
    @java.lang.Override
    public java.util.List<? extends emu.grasscutter.net.proto.MapAreaInfoOuterClass.MapAreaInfoOrBuilder> 
        getMapAreaInfoListOrBuilderList() {
      return mapAreaInfoList_;
    }
    /**
     * <code>repeated .MapAreaInfo map_area_info_list = 9;</code>
     */
    @java.lang.Override
    public int getMapAreaInfoListCount() {
      return mapAreaInfoList_.size();
    }
    /**
     * <code>repeated .MapAreaInfo map_area_info_list = 9;</code>
     */
    @java.lang.Override
    public emu.grasscutter.net.proto.MapAreaInfoOuterClass.MapAreaInfo getMapAreaInfoList(int index) {
      return mapAreaInfoList_.get(index);
    }
    /**
     * <code>repeated .MapAreaInfo map_area_info_list = 9;</code>
     */
    @java.lang.Override
    public emu.grasscutter.net.proto.MapAreaInfoOuterClass.MapAreaInfoOrBuilder getMapAreaInfoListOrBuilder(
        int index) {
      return mapAreaInfoList_.get(index);
    }

    private byte memoizedIsInitialized = -1;
    @java.lang.Override
    public final boolean isInitialized() {
      byte isInitialized = memoizedIsInitialized;
      if (isInitialized == 1) return true;
      if (isInitialized == 0) return false;

      memoizedIsInitialized = 1;
      return true;
    }

    @java.lang.Override
    public void writeTo(com.google.protobuf.CodedOutputStream output)
                        throws java.io.IOException {
      for (int i = 0; i < mapAreaInfoList_.size(); i++) {
        output.writeMessage(9, mapAreaInfoList_.get(i));
      }
      if (retcode_ != 0) {
        output.writeInt32(14, retcode_);
      }
      unknownFields.writeTo(output);
    }

    @java.lang.Override
    public int getSerializedSize() {
      int size = memoizedSize;
      if (size != -1) return size;

      size = 0;
      for (int i = 0; i < mapAreaInfoList_.size(); i++) {
        size += com.google.protobuf.CodedOutputStream
          .computeMessageSize(9, mapAreaInfoList_.get(i));
      }
      if (retcode_ != 0) {
        size += com.google.protobuf.CodedOutputStream
          .computeInt32Size(14, retcode_);
      }
      size += unknownFields.getSerializedSize();
      memoizedSize = size;
      return size;
    }

    @java.lang.Override
    public boolean equals(final java.lang.Object obj) {
      if (obj == this) {
       return true;
      }
      if (!(obj instanceof emu.grasscutter.net.proto.GetMapAreaRspOuterClass.GetMapAreaRsp)) {
        return super.equals(obj);
      }
      emu.grasscutter.net.proto.GetMapAreaRspOuterClass.GetMapAreaRsp other = (emu.grasscutter.net.proto.GetMapAreaRspOuterClass.GetMapAreaRsp) obj;

      if (getRetcode()
          != other.getRetcode()) return false;
      if (!getMapAreaInfoListList()
          .equals(other.getMapAreaInfoListList())) return false;
      if (!unknownFields.equals(other.unknownFields)) return false;
      return true;
    }

    @java.lang.Override
    public int hashCode() {
      if (memoizedHashCode != 0) {
        return memoizedHashCode;
      }
      int hash = 41;
      hash = (19 * hash) + getDescriptor().hashCode();
      hash = (37 * hash) + RETCODE_FIELD_NUMBER;
      hash = (53 * hash) + getRetcode();
      if (getMapAreaInfoListCount() > 0) {
        hash = (37 * hash) + MAP_AREA_INFO_LIST_FIELD_NUMBER;
        hash = (53 * hash) + getMapAreaInfoListList().hashCode();
      }
      hash = (29 * hash) + unknownFields.hashCode();
      memoizedHashCode = hash;
      return hash;
    }

    public static emu.grasscutter.net.proto.GetMapAreaRspOuterClass.GetMapAreaRsp parseFrom(
        java.nio.ByteBuffer data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static emu.grasscutter.net.proto.GetMapAreaRspOuterClass.GetMapAreaRsp parseFrom(
        java.nio.ByteBuffer data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static emu.grasscutter.net.proto.GetMapAreaRspOuterClass.GetMapAreaRsp parseFrom(
        com.google.protobuf.ByteString data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static emu.grasscutter.net.proto.GetMapAreaRspOuterClass.GetMapAreaRsp parseFrom(
        com.google.protobuf.ByteString data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static emu.grasscutter.net.proto.GetMapAreaRspOuterClass.GetMapAreaRsp parseFrom(byte[] data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static emu.grasscutter.net.proto.GetMapAreaRspOuterClass.GetMapAreaRsp parseFrom(
        byte[] data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static emu.grasscutter.net.proto.GetMapAreaRspOuterClass.GetMapAreaRsp parseFrom(java.io.InputStream input)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseWithIOException(PARSER, input);
    }
    public static emu.grasscutter.net.proto.GetMapAreaRspOuterClass.GetMapAreaRsp parseFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseWithIOException(PARSER, input, extensionRegistry);
    }
    public static emu.grasscutter.net.proto.GetMapAreaRspOuterClass.GetMapAreaRsp parseDelimitedFrom(java.io.InputStream input)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseDelimitedWithIOException(PARSER, input);
    }
    public static emu.grasscutter.net.proto.GetMapAreaRspOuterClass.GetMapAreaRsp parseDelimitedFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
    }
    public static emu.grasscutter.net.proto.GetMapAreaRspOuterClass.GetMapAreaRsp parseFrom(
        com.google.protobuf.CodedInputStream input)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseWithIOException(PARSER, input);
    }
    public static emu.grasscutter.net.proto.GetMapAreaRspOuterClass.GetMapAreaRsp parseFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseWithIOException(PARSER, input, extensionRegistry);
    }

    @java.lang.Override
    public Builder newBuilderForType() { return newBuilder(); }
    public static Builder newBuilder() {
      return DEFAULT_INSTANCE.toBuilder();
    }
    public static Builder newBuilder(emu.grasscutter.net.proto.GetMapAreaRspOuterClass.GetMapAreaRsp prototype) {
      return DEFAULT_INSTANCE.toBuilder().mergeFrom(prototype);
    }
    @java.lang.Override
    public Builder toBuilder() {
      return this == DEFAULT_INSTANCE
          ? new Builder() : new Builder().mergeFrom(this);
    }

    @java.lang.Override
    protected Builder newBuilderForType(
        com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
      Builder builder = new Builder(parent);
      return builder;
    }
    /**
     * <pre>
     * CmdId: 3328
     * EnetChannelId: 0
     * EnetIsReliable: true
     * </pre>
     *
     * Protobuf type {@code GetMapAreaRsp}
     */
    public static final class Builder extends
        com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
        // @@protoc_insertion_point(builder_implements:GetMapAreaRsp)
        emu.grasscutter.net.proto.GetMapAreaRspOuterClass.GetMapAreaRspOrBuilder {
      public static final com.google.protobuf.Descriptors.Descriptor
          getDescriptor() {
        return emu.grasscutter.net.proto.GetMapAreaRspOuterClass.internal_static_GetMapAreaRsp_descriptor;
      }

      @java.lang.Override
      protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
          internalGetFieldAccessorTable() {
        return emu.grasscutter.net.proto.GetMapAreaRspOuterClass.internal_static_GetMapAreaRsp_fieldAccessorTable
            .ensureFieldAccessorsInitialized(
                emu.grasscutter.net.proto.GetMapAreaRspOuterClass.GetMapAreaRsp.class, emu.grasscutter.net.proto.GetMapAreaRspOuterClass.GetMapAreaRsp.Builder.class);
      }

      // Construct using emu.grasscutter.net.proto.GetMapAreaRspOuterClass.GetMapAreaRsp.newBuilder()
      private Builder() {
        maybeForceBuilderInitialization();
      }

      private Builder(
          com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
        super(parent);
        maybeForceBuilderInitialization();
      }
      private void maybeForceBuilderInitialization() {
        if (com.google.protobuf.GeneratedMessageV3
                .alwaysUseFieldBuilders) {
          getMapAreaInfoListFieldBuilder();
        }
      }
      @java.lang.Override
      public Builder clear() {
        super.clear();
        retcode_ = 0;

        if (mapAreaInfoListBuilder_ == null) {
          mapAreaInfoList_ = java.util.Collections.emptyList();
          bitField0_ = (bitField0_ & ~0x00000001);
        } else {
          mapAreaInfoListBuilder_.clear();
        }
        return this;
      }

      @java.lang.Override
      public com.google.protobuf.Descriptors.Descriptor
          getDescriptorForType() {
        return emu.grasscutter.net.proto.GetMapAreaRspOuterClass.internal_static_GetMapAreaRsp_descriptor;
      }

      @java.lang.Override
      public emu.grasscutter.net.proto.GetMapAreaRspOuterClass.GetMapAreaRsp getDefaultInstanceForType() {
        return emu.grasscutter.net.proto.GetMapAreaRspOuterClass.GetMapAreaRsp.getDefaultInstance();
      }

      @java.lang.Override
      public emu.grasscutter.net.proto.GetMapAreaRspOuterClass.GetMapAreaRsp build() {
        emu.grasscutter.net.proto.GetMapAreaRspOuterClass.GetMapAreaRsp result = buildPartial();
        if (!result.isInitialized()) {
          throw newUninitializedMessageException(result);
        }
        return result;
      }

      @java.lang.Override
      public emu.grasscutter.net.proto.GetMapAreaRspOuterClass.GetMapAreaRsp buildPartial() {
        emu.grasscutter.net.proto.GetMapAreaRspOuterClass.GetMapAreaRsp result = new emu.grasscutter.net.proto.GetMapAreaRspOuterClass.GetMapAreaRsp(this);
        int from_bitField0_ = bitField0_;
        result.retcode_ = retcode_;
        if (mapAreaInfoListBuilder_ == null) {
          if (((bitField0_ & 0x00000001) != 0)) {
            mapAreaInfoList_ = java.util.Collections.unmodifiableList(mapAreaInfoList_);
            bitField0_ = (bitField0_ & ~0x00000001);
          }
          result.mapAreaInfoList_ = mapAreaInfoList_;
        } else {
          result.mapAreaInfoList_ = mapAreaInfoListBuilder_.build();
        }
        onBuilt();
        return result;
      }

      @java.lang.Override
      public Builder clone() {
        return super.clone();
      }
      @java.lang.Override
      public Builder setField(
          com.google.protobuf.Descriptors.FieldDescriptor field,
          java.lang.Object value) {
        return super.setField(field, value);
      }
      @java.lang.Override
      public Builder clearField(
          com.google.protobuf.Descriptors.FieldDescriptor field) {
        return super.clearField(field);
      }
      @java.lang.Override
      public Builder clearOneof(
          com.google.protobuf.Descriptors.OneofDescriptor oneof) {
        return super.clearOneof(oneof);
      }
      @java.lang.Override
      public Builder setRepeatedField(
          com.google.protobuf.Descriptors.FieldDescriptor field,
          int index, java.lang.Object value) {
        return super.setRepeatedField(field, index, value);
      }
      @java.lang.Override
      public Builder addRepeatedField(
          com.google.protobuf.Descriptors.FieldDescriptor field,
          java.lang.Object value) {
        return super.addRepeatedField(field, value);
      }
      @java.lang.Override
      public Builder mergeFrom(com.google.protobuf.Message other) {
        if (other instanceof emu.grasscutter.net.proto.GetMapAreaRspOuterClass.GetMapAreaRsp) {
          return mergeFrom((emu.grasscutter.net.proto.GetMapAreaRspOuterClass.GetMapAreaRsp)other);
        } else {
          super.mergeFrom(other);
          return this;
        }
      }

      public Builder mergeFrom(emu.grasscutter.net.proto.GetMapAreaRspOuterClass.GetMapAreaRsp other) {
        if (other == emu.grasscutter.net.proto.GetMapAreaRspOuterClass.GetMapAreaRsp.getDefaultInstance()) return this;
        if (other.getRetcode() != 0) {
          setRetcode(other.getRetcode());
        }
        if (mapAreaInfoListBuilder_ == null) {
          if (!other.mapAreaInfoList_.isEmpty()) {
            if (mapAreaInfoList_.isEmpty()) {
              mapAreaInfoList_ = other.mapAreaInfoList_;
              bitField0_ = (bitField0_ & ~0x00000001);
            } else {
              ensureMapAreaInfoListIsMutable();
              mapAreaInfoList_.addAll(other.mapAreaInfoList_);
            }
            onChanged();
          }
        } else {
          if (!other.mapAreaInfoList_.isEmpty()) {
            if (mapAreaInfoListBuilder_.isEmpty()) {
              mapAreaInfoListBuilder_.dispose();
              mapAreaInfoListBuilder_ = null;
              mapAreaInfoList_ = other.mapAreaInfoList_;
              bitField0_ = (bitField0_ & ~0x00000001);
              mapAreaInfoListBuilder_ = 
                com.google.protobuf.GeneratedMessageV3.alwaysUseFieldBuilders ?
                   getMapAreaInfoListFieldBuilder() : null;
            } else {
              mapAreaInfoListBuilder_.addAllMessages(other.mapAreaInfoList_);
            }
          }
        }
        this.mergeUnknownFields(other.unknownFields);
        onChanged();
        return this;
      }

      @java.lang.Override
      public final boolean isInitialized() {
        return true;
      }

      @java.lang.Override
      public Builder mergeFrom(
          com.google.protobuf.CodedInputStream input,
          com.google.protobuf.ExtensionRegistryLite extensionRegistry)
          throws java.io.IOException {
        emu.grasscutter.net.proto.GetMapAreaRspOuterClass.GetMapAreaRsp parsedMessage = null;
        try {
          parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
        } catch (com.google.protobuf.InvalidProtocolBufferException e) {
          parsedMessage = (emu.grasscutter.net.proto.GetMapAreaRspOuterClass.GetMapAreaRsp) e.getUnfinishedMessage();
          throw e.unwrapIOException();
        } finally {
          if (parsedMessage != null) {
            mergeFrom(parsedMessage);
          }
        }
        return this;
      }
      private int bitField0_;

      private int retcode_ ;
      /**
       * <code>int32 retcode = 14;</code>
       * @return The retcode.
       */
      @java.lang.Override
      public int getRetcode() {
        return retcode_;
      }
      /**
       * <code>int32 retcode = 14;</code>
       * @param value The retcode to set.
       * @return This builder for chaining.
       */
      public Builder setRetcode(int value) {
        
        retcode_ = value;
        onChanged();
        return this;
      }
      /**
       * <code>int32 retcode = 14;</code>
       * @return This builder for chaining.
       */
      public Builder clearRetcode() {
        
        retcode_ = 0;
        onChanged();
        return this;
      }

      private java.util.List<emu.grasscutter.net.proto.MapAreaInfoOuterClass.MapAreaInfo> mapAreaInfoList_ =
        java.util.Collections.emptyList();
      private void ensureMapAreaInfoListIsMutable() {
        if (!((bitField0_ & 0x00000001) != 0)) {
          mapAreaInfoList_ = new java.util.ArrayList<emu.grasscutter.net.proto.MapAreaInfoOuterClass.MapAreaInfo>(mapAreaInfoList_);
          bitField0_ |= 0x00000001;
         }
      }

      private com.google.protobuf.RepeatedFieldBuilderV3<
          emu.grasscutter.net.proto.MapAreaInfoOuterClass.MapAreaInfo, emu.grasscutter.net.proto.MapAreaInfoOuterClass.MapAreaInfo.Builder, emu.grasscutter.net.proto.MapAreaInfoOuterClass.MapAreaInfoOrBuilder> mapAreaInfoListBuilder_;

      /**
       * <code>repeated .MapAreaInfo map_area_info_list = 9;</code>
       */
      public java.util.List<emu.grasscutter.net.proto.MapAreaInfoOuterClass.MapAreaInfo> getMapAreaInfoListList() {
        if (mapAreaInfoListBuilder_ == null) {
          return java.util.Collections.unmodifiableList(mapAreaInfoList_);
        } else {
          return mapAreaInfoListBuilder_.getMessageList();
        }
      }
      /**
       * <code>repeated .MapAreaInfo map_area_info_list = 9;</code>
       */
      public int getMapAreaInfoListCount() {
        if (mapAreaInfoListBuilder_ == null) {
          return mapAreaInfoList_.size();
        } else {
          return mapAreaInfoListBuilder_.getCount();
        }
      }
      /**
       * <code>repeated .MapAreaInfo map_area_info_list = 9;</code>
       */
      public emu.grasscutter.net.proto.MapAreaInfoOuterClass.MapAreaInfo getMapAreaInfoList(int index) {
        if (mapAreaInfoListBuilder_ == null) {
          return mapAreaInfoList_.get(index);
        } else {
          return mapAreaInfoListBuilder_.getMessage(index);
        }
      }
      /**
       * <code>repeated .MapAreaInfo map_area_info_list = 9;</code>
       */
      public Builder setMapAreaInfoList(
          int index, emu.grasscutter.net.proto.MapAreaInfoOuterClass.MapAreaInfo value) {
        if (mapAreaInfoListBuilder_ == null) {
          if (value == null) {
            throw new NullPointerException();
          }
          ensureMapAreaInfoListIsMutable();
          mapAreaInfoList_.set(index, value);
          onChanged();
        } else {
          mapAreaInfoListBuilder_.setMessage(index, value);
        }
        return this;
      }
      /**
       * <code>repeated .MapAreaInfo map_area_info_list = 9;</code>
       */
      public Builder setMapAreaInfoList(
          int index, emu.grasscutter.net.proto.MapAreaInfoOuterClass.MapAreaInfo.Builder builderForValue) {
        if (mapAreaInfoListBuilder_ == null) {
          ensureMapAreaInfoListIsMutable();
          mapAreaInfoList_.set(index, builderForValue.build());
          onChanged();
        } else {
          mapAreaInfoListBuilder_.setMessage(index, builderForValue.build());
        }
        return this;
      }
      /**
       * <code>repeated .MapAreaInfo map_area_info_list = 9;</code>
       */
      public Builder addMapAreaInfoList(emu.grasscutter.net.proto.MapAreaInfoOuterClass.MapAreaInfo value) {
        if (mapAreaInfoListBuilder_ == null) {
          if (value == null) {
            throw new NullPointerException();
          }
          ensureMapAreaInfoListIsMutable();
          mapAreaInfoList_.add(value);
          onChanged();
        } else {
          mapAreaInfoListBuilder_.addMessage(value);
        }
        return this;
      }
      /**
       * <code>repeated .MapAreaInfo map_area_info_list = 9;</code>
       */
      public Builder addMapAreaInfoList(
          int index, emu.grasscutter.net.proto.MapAreaInfoOuterClass.MapAreaInfo value) {
        if (mapAreaInfoListBuilder_ == null) {
          if (value == null) {
            throw new NullPointerException();
          }
          ensureMapAreaInfoListIsMutable();
          mapAreaInfoList_.add(index, value);
          onChanged();
        } else {
          mapAreaInfoListBuilder_.addMessage(index, value);
        }
        return this;
      }
      /**
       * <code>repeated .MapAreaInfo map_area_info_list = 9;</code>
       */
      public Builder addMapAreaInfoList(
          emu.grasscutter.net.proto.MapAreaInfoOuterClass.MapAreaInfo.Builder builderForValue) {
        if (mapAreaInfoListBuilder_ == null) {
          ensureMapAreaInfoListIsMutable();
          mapAreaInfoList_.add(builderForValue.build());
          onChanged();
        } else {
          mapAreaInfoListBuilder_.addMessage(builderForValue.build());
        }
        return this;
      }
      /**
       * <code>repeated .MapAreaInfo map_area_info_list = 9;</code>
       */
      public Builder addMapAreaInfoList(
          int index, emu.grasscutter.net.proto.MapAreaInfoOuterClass.MapAreaInfo.Builder builderForValue) {
        if (mapAreaInfoListBuilder_ == null) {
          ensureMapAreaInfoListIsMutable();
          mapAreaInfoList_.add(index, builderForValue.build());
          onChanged();
        } else {
          mapAreaInfoListBuilder_.addMessage(index, builderForValue.build());
        }
        return this;
      }
      /**
       * <code>repeated .MapAreaInfo map_area_info_list = 9;</code>
       */
      public Builder addAllMapAreaInfoList(
          java.lang.Iterable<? extends emu.grasscutter.net.proto.MapAreaInfoOuterClass.MapAreaInfo> values) {
        if (mapAreaInfoListBuilder_ == null) {
          ensureMapAreaInfoListIsMutable();
          com.google.protobuf.AbstractMessageLite.Builder.addAll(
              values, mapAreaInfoList_);
          onChanged();
        } else {
          mapAreaInfoListBuilder_.addAllMessages(values);
        }
        return this;
      }
      /**
       * <code>repeated .MapAreaInfo map_area_info_list = 9;</code>
       */
      public Builder clearMapAreaInfoList() {
        if (mapAreaInfoListBuilder_ == null) {
          mapAreaInfoList_ = java.util.Collections.emptyList();
          bitField0_ = (bitField0_ & ~0x00000001);
          onChanged();
        } else {
          mapAreaInfoListBuilder_.clear();
        }
        return this;
      }
      /**
       * <code>repeated .MapAreaInfo map_area_info_list = 9;</code>
       */
      public Builder removeMapAreaInfoList(int index) {
        if (mapAreaInfoListBuilder_ == null) {
          ensureMapAreaInfoListIsMutable();
          mapAreaInfoList_.remove(index);
          onChanged();
        } else {
          mapAreaInfoListBuilder_.remove(index);
        }
        return this;
      }
      /**
       * <code>repeated .MapAreaInfo map_area_info_list = 9;</code>
       */
      public emu.grasscutter.net.proto.MapAreaInfoOuterClass.MapAreaInfo.Builder getMapAreaInfoListBuilder(
          int index) {
        return getMapAreaInfoListFieldBuilder().getBuilder(index);
      }
      /**
       * <code>repeated .MapAreaInfo map_area_info_list = 9;</code>
       */
      public emu.grasscutter.net.proto.MapAreaInfoOuterClass.MapAreaInfoOrBuilder getMapAreaInfoListOrBuilder(
          int index) {
        if (mapAreaInfoListBuilder_ == null) {
          return mapAreaInfoList_.get(index);  } else {
          return mapAreaInfoListBuilder_.getMessageOrBuilder(index);
        }
      }
      /**
       * <code>repeated .MapAreaInfo map_area_info_list = 9;</code>
       */
      public java.util.List<? extends emu.grasscutter.net.proto.MapAreaInfoOuterClass.MapAreaInfoOrBuilder> 
           getMapAreaInfoListOrBuilderList() {
        if (mapAreaInfoListBuilder_ != null) {
          return mapAreaInfoListBuilder_.getMessageOrBuilderList();
        } else {
          return java.util.Collections.unmodifiableList(mapAreaInfoList_);
        }
      }
      /**
       * <code>repeated .MapAreaInfo map_area_info_list = 9;</code>
       */
      public emu.grasscutter.net.proto.MapAreaInfoOuterClass.MapAreaInfo.Builder addMapAreaInfoListBuilder() {
        return getMapAreaInfoListFieldBuilder().addBuilder(
            emu.grasscutter.net.proto.MapAreaInfoOuterClass.MapAreaInfo.getDefaultInstance());
      }
      /**
       * <code>repeated .MapAreaInfo map_area_info_list = 9;</code>
       */
      public emu.grasscutter.net.proto.MapAreaInfoOuterClass.MapAreaInfo.Builder addMapAreaInfoListBuilder(
          int index) {
        return getMapAreaInfoListFieldBuilder().addBuilder(
            index, emu.grasscutter.net.proto.MapAreaInfoOuterClass.MapAreaInfo.getDefaultInstance());
      }
      /**
       * <code>repeated .MapAreaInfo map_area_info_list = 9;</code>
       */
      public java.util.List<emu.grasscutter.net.proto.MapAreaInfoOuterClass.MapAreaInfo.Builder> 
           getMapAreaInfoListBuilderList() {
        return getMapAreaInfoListFieldBuilder().getBuilderList();
      }
      private com.google.protobuf.RepeatedFieldBuilderV3<
          emu.grasscutter.net.proto.MapAreaInfoOuterClass.MapAreaInfo, emu.grasscutter.net.proto.MapAreaInfoOuterClass.MapAreaInfo.Builder, emu.grasscutter.net.proto.MapAreaInfoOuterClass.MapAreaInfoOrBuilder> 
          getMapAreaInfoListFieldBuilder() {
        if (mapAreaInfoListBuilder_ == null) {
          mapAreaInfoListBuilder_ = new com.google.protobuf.RepeatedFieldBuilderV3<
              emu.grasscutter.net.proto.MapAreaInfoOuterClass.MapAreaInfo, emu.grasscutter.net.proto.MapAreaInfoOuterClass.MapAreaInfo.Builder, emu.grasscutter.net.proto.MapAreaInfoOuterClass.MapAreaInfoOrBuilder>(
                  mapAreaInfoList_,
                  ((bitField0_ & 0x00000001) != 0),
                  getParentForChildren(),
                  isClean());
          mapAreaInfoList_ = null;
        }
        return mapAreaInfoListBuilder_;
      }
      @java.lang.Override
      public final Builder setUnknownFields(
          final com.google.protobuf.UnknownFieldSet unknownFields) {
        return super.setUnknownFields(unknownFields);
      }

      @java.lang.Override
      public final Builder mergeUnknownFields(
          final com.google.protobuf.UnknownFieldSet unknownFields) {
        return super.mergeUnknownFields(unknownFields);
      }


      // @@protoc_insertion_point(builder_scope:GetMapAreaRsp)
    }

    // @@protoc_insertion_point(class_scope:GetMapAreaRsp)
    private static final emu.grasscutter.net.proto.GetMapAreaRspOuterClass.GetMapAreaRsp DEFAULT_INSTANCE;
    static {
      DEFAULT_INSTANCE = new emu.grasscutter.net.proto.GetMapAreaRspOuterClass.GetMapAreaRsp();
    }

    public static emu.grasscutter.net.proto.GetMapAreaRspOuterClass.GetMapAreaRsp getDefaultInstance() {
      return DEFAULT_INSTANCE;
    }

    private static final com.google.protobuf.Parser<GetMapAreaRsp>
        PARSER = new com.google.protobuf.AbstractParser<GetMapAreaRsp>() {
      @java.lang.Override
      public GetMapAreaRsp parsePartialFrom(
          com.google.protobuf.CodedInputStream input,
          com.google.protobuf.ExtensionRegistryLite extensionRegistry)
          throws com.google.protobuf.InvalidProtocolBufferException {
        return new GetMapAreaRsp(input, extensionRegistry);
      }
    };

    public static com.google.protobuf.Parser<GetMapAreaRsp> parser() {
      return PARSER;
    }

    @java.lang.Override
    public com.google.protobuf.Parser<GetMapAreaRsp> getParserForType() {
      return PARSER;
    }

    @java.lang.Override
    public emu.grasscutter.net.proto.GetMapAreaRspOuterClass.GetMapAreaRsp getDefaultInstanceForType() {
      return DEFAULT_INSTANCE;
    }

  }

  private static final com.google.protobuf.Descriptors.Descriptor
    internal_static_GetMapAreaRsp_descriptor;
  private static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_GetMapAreaRsp_fieldAccessorTable;

  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static  com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    java.lang.String[] descriptorData = {
      "\n\023GetMapAreaRsp.proto\032\021MapAreaInfo.proto" +
      "\"J\n\rGetMapAreaRsp\022\017\n\007retcode\030\016 \001(\005\022(\n\022ma" +
      "p_area_info_list\030\t \003(\0132\014.MapAreaInfoB\026\n\024" +
      "emu.grasscutter.net.protob\006proto3"
    };
    descriptor = com.google.protobuf.Descriptors.FileDescriptor
      .internalBuildGeneratedFileFrom(descriptorData,
        new com.google.protobuf.Descriptors.FileDescriptor[] {
          emu.grasscutter.net.proto.MapAreaInfoOuterClass.getDescriptor(),
        });
    internal_static_GetMapAreaRsp_descriptor =
      getDescriptor().getMessageTypes().get(0);
    internal_static_GetMapAreaRsp_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_GetMapAreaRsp_descriptor,
        new java.lang.String[] { "Retcode", "MapAreaInfoList", });
    emu.grasscutter.net.proto.MapAreaInfoOuterClass.getDescriptor();
  }

  // @@protoc_insertion_point(outer_class_scope)
}
