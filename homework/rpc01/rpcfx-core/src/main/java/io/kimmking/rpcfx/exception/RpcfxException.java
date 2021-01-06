package io.kimmking.rpcfx.exception;

import lombok.Data;

@Data
public class RpcfxException {
    private int code;
    private String msg;
}
