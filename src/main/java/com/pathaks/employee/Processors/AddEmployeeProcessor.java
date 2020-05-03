package com.pathaks.employee.Processors;

import com.pathaks.employee.Entities.Employee;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;

public class AddEmployeeProcessor implements Processor {

    @Override
    public void process(Exchange exchange) throws Exception {

        Employee employee = exchange.getIn().getBody(Employee.class);
        String table_name = exchange.getContext().resolvePropertyPlaceholders("{{table.name}}");
        Object empMiddleNameObject = employee.getEmpMiddleName() == null ? "" : employee.getEmpMiddleName();

        StringBuilder insertQuery = new StringBuilder();
        insertQuery.append("insert into " + table_name +" (empfirstname, empmiddlename, emplastname, empdepartment, empsalary) values ('");
        insertQuery.append(employee.getEmpFirstName() + "', '");
        insertQuery.append(empMiddleNameObject + "', '");
        insertQuery.append(employee.getEmpLastName() + "', '");
        insertQuery.append(employee.getEmpDepartment() + "', ");
        insertQuery.append(employee.getEmpSalary() + ")");
        System.out.println(insertQuery.toString());
        exchange.getMessage().setHeader("CamelRetrieveGeneratedKeys", true);
        exchange.getMessage().setBody(insertQuery);
    }
}