package com.github.rluisb.voting.model;

public class CommandResponse {

    private Integer code;
    private String message;
    private String result;

    public CommandResponse() {
    }

    public CommandResponse(Integer code, String message, String result) {
        this.code = code;
        this.message = message;
        this.result = result;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    @Override
    public String toString() {
        return "CommandResponse{" +
                "code=" + code +
                ", message='" + message + '\'' +
                ", result='" + result + '\'' +
                '}';
    }
}
