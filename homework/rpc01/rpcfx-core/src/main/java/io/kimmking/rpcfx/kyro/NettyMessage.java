package io.kimmking.rpcfx.kyro;

import lombok.Data;

@Data
public class NettyMessage {
    private String serviceClass;
    private String method;
    private Object[] params;

    private String response;
}
