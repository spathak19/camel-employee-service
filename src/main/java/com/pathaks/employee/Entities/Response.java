package com.pathaks.employee.Entities;

public class Response {

    public Response() {
        super();
    }


    public Response(String resCode, String resBody) {
        super();

        this.responseCode = resCode;
        this.responseBody = resBody;
    }

    private String responseCode;
    private String responseBody;

    public String getResponseCode() {
        return this.responseCode;
    }

    public void setResponseCode(String responseCode) {
        this.responseCode = responseCode;
    }

    public String getResponseBody() {
        return this.responseBody;
    }

    public void setResponseBody(String responseBody) {
        this.responseBody = responseBody;
    }
    
}