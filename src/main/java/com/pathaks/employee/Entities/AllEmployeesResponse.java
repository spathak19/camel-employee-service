package com.pathaks.employee.Entities;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "Response")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder={"responseCode", "responseBody"})
public class AllEmployeesResponse {

    public AllEmployeesResponse() {
        super();
    }


    public AllEmployeesResponse(String resCode, List<Employee> resBody) {
        super();

        this.responseCode = resCode;
        this.responseBody = resBody;
    }

    @XmlElement(name="ResponseCode")
    private String responseCode;

    @XmlElementWrapper(name="ResponseBody")
	@XmlElement(name="Employee")
    private List<Employee> responseBody;

    public String getResponseCode() {
        return this.responseCode;
    }

    public void setResponseCode(String responseCode) {
        this.responseCode = responseCode;
    }

    public List<Employee> getResponseBody() {
        return this.responseBody;
    }

    public void setResponseBody(List<Employee> resBody) {
        this.responseBody = resBody;
    }
    
}