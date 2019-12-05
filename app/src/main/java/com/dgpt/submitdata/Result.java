package com.dgpt.submitdata;

import java.util.List;

public class Result {
    private String status;
    private String message;
    private List<UserInfo> data;

    public Result() {
    }

    public Result(String status, String message, List<UserInfo> data) {
        this.status = status;
        this.message = message;
        this.data = data;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<UserInfo> getData() {
        return data;
    }

    public void setData(List<UserInfo> data) {
        this.data = data;
    }
}
