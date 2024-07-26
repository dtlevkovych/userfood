package org.dmle.userfood.domain;

import org.springframework.http.HttpStatus;

import java.beans.Transient;
import java.util.Objects;

public class ResponseEntity<T> {
    private HttpStatus statusCode;
    private String error_msg;
    private T data;

    public ResponseEntity() {

    }

    public static <T> ResponseEntity<T> successResponse(T data) {
        ResponseEntity<T> response = new ResponseEntity<>();
        response.setData(data);
        response.setStatusCode(HttpStatus.OK);
        response.setError_msg("");

        return response;
    }

    public static ResponseEntity<Object> errorResponse(String error_msg, HttpStatus statusCode) {
        ResponseEntity<Object> response = new ResponseEntity<>();
        response.setError_msg(error_msg);
        response.setStatusCode(statusCode);

        return response;
    }

    public boolean getStatus() {
        return Objects.equals(this.statusCode, HttpStatus.OK);
    }

    @Transient
    public HttpStatus getStatusCode() {
        return statusCode;
    }

    public int getCode() {
        return this.statusCode == null ? 500 : this.statusCode.value();
    }

    public void setStatusCode(HttpStatus statusCode) {
        this.statusCode = statusCode;
    }

    public String getError_msg() {
        return error_msg;
    }

    public void setError_msg(String error_msg) {
        this.error_msg = error_msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
