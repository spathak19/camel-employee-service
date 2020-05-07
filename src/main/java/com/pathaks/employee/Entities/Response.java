package com.pathaks.employee.Entities;

import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name ="Response")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder={"responseCode", "responseBody"})
public class Response{

    public Response() {
    }


    public Response(String resCode, String resBody) {
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