package com.pathaks.employee.Entities;

public class Response {

    public Response() {
        super();
    }


    public Response(String resCode, Object resBody) {
        super();

        this.responseCode = resCode;
        this.responseBody = resBody;
    }

    private String responseCode;
    private Object responseBody;

    public String getResponseCode() {
        return this.responseCode;
    }

    public void setResponseCode(String responseCode) {
        this.responseCode = responseCode;
    }

    public Object getResponseBody() {
        return this.responseBody;
    }

    public void setResponseBody(Object responseBody) {
        this.responseBody = responseBody;
    }
    
}