package com.pathaks.employee.Entities;

import java.math.BigDecimal;
import java.util.Date;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

@XmlRootElement(name = "Employee")
@XmlAccessorType(XmlAccessType.FIELD)
@JsonRootName(value = "Employee")
public class Employee{

    @JsonProperty(value = "empid", required = false)
    @XmlElement(name = "empid", required = false)
    private Integer empId;

    @JsonProperty(value = "empfirstname", required = true)
    @XmlElement(name = "empfirstname", required = true)
    private String empFirstName;
    
    @JsonProperty(value = "empmiddlename", required = false)
    @XmlElement(name = "empmiddlename", required = false)
    private String empMiddleName;
    
    @JsonProperty(value = "emplastname", required = true)
    @XmlElement(name = "emplastname", required = true)
    private String empLastName;

    @JsonProperty(value = "empsalary", required = true)
    @XmlElement(name = "empsalary", required = true)
    private BigDecimal empSalary;
    
    @JsonProperty(value = "empdepartment", required = true)
    @XmlElement(name = "empdepartment", required = true)
    private String empDepartment;
    
    @JsonProperty(value = "emphiredate", required = false)
    @XmlElement(name = "emphiredate", required = false)
    private Date empHireDate;
    
    public Integer getEmpId() {
        return this.empId;
    }

    public void setEmpId(Integer empId) {
        this.empId = empId;
    }
        
    public String getEmpFirstName() {
        return this.empFirstName;
    }

    public void setEmpFirstName(String empFirstName) {
        this.empFirstName = empFirstName;
    }

    public String getEmpMiddleName() {
        return this.empMiddleName;
    }

    public void setEmpMiddleName(String empMiddleName) {
        this.empMiddleName = empMiddleName;
    }

    public String getEmpLastName() {
        return this.empLastName;
    }

    public void setEmpLastName(String empLastName) {
        this.empLastName = empLastName;
    }

    public BigDecimal getEmpSalary() {
        return this.empSalary;
    }

    public void setEmpSalary(BigDecimal empSalary) {
        this.empSalary = empSalary;
    }

    public String getEmpDepartment() {
        return this.empDepartment;
    }

    public void setEmpDepartment(String empDepartment) {
        this.empDepartment = empDepartment;
    }

    public Date getEmpHireDate() {
        return this.empHireDate;
    }

    public void setEmpHireDate(Date empHireDate) {
        this.empHireDate = empHireDate;
    }

    @Override
    public String toString() {



        Object empIdObject = getEmpId();
        Object empMiddleNameObject = getEmpMiddleName();
        Object empHireDateObject = getEmpHireDate();


        StringBuilder empStringBuilder = new StringBuilder();
        empStringBuilder.append("Employee Details: ");
        empStringBuilder.append("[ ");
        empStringBuilder.append(empIdObject == null ? " , ": empIdObject.toString() + " ,");
        empStringBuilder.append(getEmpFirstName().toString() + " , ");
        empStringBuilder.append(empMiddleNameObject == null ? " , " : empMiddleNameObject.toString() + " ,");
        empStringBuilder.append(getEmpLastName().toString() + " , ");
        empStringBuilder.append(getEmpSalary().toString() + " , ");
        empStringBuilder.append(getEmpDepartment().toString() + " , ");
        empStringBuilder.append(empHireDateObject == null ? " " : empHireDateObject.toString() + "]");

        return empStringBuilder.toString();
    }

}
