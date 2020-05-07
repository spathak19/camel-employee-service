package com.pathaks.employee.Entities;

import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "Response")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder={"responseCode", "responseBody"})
public class EmployeeResponse{

    public EmployeeResponse() {
        super();
    }


    public EmployeeResponse(String resCode, Employee resBody) {
        super();

        this.responseCode = resCode;
        this.responseBody = resBody;
    }

    @XmlElement(name="ResponseCode")
    public String responseCode;

    @XmlElement(name="ResponseBody")
    public Employee responseBody;

    public Employee getResponseBody() {
        return this.responseBody;
    }

    public void setResponseBody(Employee responseBody) {
        this.responseBody = responseBody;
    }

    public String getResponseCode() {
        return this.responseCode;
    }

    public void setResponseCode(String resBody) {
        this.responseCode = resBody;
    }
}