package com.globits.da.validate;

import com.globits.da.dto.DistrictDto;

public class Response<T>  {

    private  T data;
    private  ResponseStatus status;

    public Response(T data, ResponseStatus responseStatus){
        this.data = data;
        this.status = responseStatus;
    }
    public Response(T data){
        this.data = data;
    }

    public T getData() {
        return data;
    }

    public ResponseStatus getStatus() {
        return status;
    }

    public void setData(T data) {
        this.data = data;
    }

    public void setStatus(ResponseStatus status) {
        this.status = status;
    }
}
