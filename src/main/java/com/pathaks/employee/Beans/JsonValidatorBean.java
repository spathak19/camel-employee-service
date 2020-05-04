package com.pathaks.employee.Beans;

import com.pathaks.employee.Entities.Employee;

import org.springframework.context.annotation.Bean;

public class JsonValidatorBean {

    @Bean(name = "validateEmployee")
    public void validateEmployee(Employee body) throws Exception {

        System.out.println("In validator Bean");

        boolean firstNameExists = body.getEmpFirstName() != null && body.getEmpFirstName().length() > 0;
        boolean lastNameExists = body.getEmpLastName() != null && body.getEmpLastName().length() > 0;
        boolean departmentExists = body.getEmpDepartment() != null && body.getEmpDepartment().length() > 0;
        boolean salaryExists = body.getEmpSalary() != null;


        System.out.println("firstNameExists: "+ firstNameExists);
        System.out.println("lastNameExists: "+ lastNameExists);
        System.out.println("departmentExists: "+ departmentExists);
        System.out.println("salaryExists: "+ salaryExists);

        if(!(firstNameExists && lastNameExists && departmentExists && salaryExists)){
            throw new Exception("Input Body is not valid");
        }

    }

}
