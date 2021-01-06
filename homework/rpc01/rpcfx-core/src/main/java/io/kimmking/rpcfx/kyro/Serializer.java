package io.kimmking.rpcfx.kyro;

public interface Serializer {
    byte[] serialize(Object obj);
    <T> T deserialize(byte[] bytes, Object obj);
}
