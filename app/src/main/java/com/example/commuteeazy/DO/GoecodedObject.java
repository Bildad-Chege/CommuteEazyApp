package com.example.commuteeazy.DO;

public class GoecodedObject {

    ResultObject result;
    String status;

    public GoecodedObject() {
    }

    public GoecodedObject(ResultObject result, String status) {
        this.result = result;
        this.status = status;
    }

    public ResultObject getResult() {
        return result;
    }

    public void setResult(ResultObject result) {
        this.result = result;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
