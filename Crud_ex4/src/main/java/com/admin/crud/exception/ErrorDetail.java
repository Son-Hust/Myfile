package com.admin.crud.exception;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ErrorDetail {

    private String title;
    private int status;
    private String detail;
    private long timeStamp;
    private String developerMessage;
    private String path;
    private Map<String, List<ValidationError>> error =new HashMap<String, List<ValidationError>>();

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Map<String, List<ValidationError>> getError() {
        return error;
    }

    public void setError(Map<String, List<ValidationError>> error) {
        this.error = error;
    }

    public ErrorDetail() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public long getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(long timeStamp) {
        this.timeStamp = timeStamp;
    }

    public String getDeveloperMessage() {
        return developerMessage;
    }

    public void setDeveloperMessage(String developerMesage) {
        this.developerMessage = developerMesage;
    }
}
