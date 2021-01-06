package io.kimmking.rpcfx.exception;

public class RpcfxException {
    private Exception exception;
    private Boolean status;

    public Exception getException() {
        return exception;
    }

    public void setException(Exception exception) {
        this.exception = exception;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public RpcfxException(Exception exception, Boolean status) {
        this.exception = exception;
        this.status = status;
    }
}
