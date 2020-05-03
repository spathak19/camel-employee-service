package com.pathaks.employee.Processors;

import com.pathaks.employee.Entities.Employee;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.springframework.stereotype.Component;

@Component
public class UpdateEmployeeProcessor implements Processor {

    @Override
    public void process(Exchange exchange) throws Exception {

        Employee employee = exchange.getIn().getBody(Employee.class);
        String id = exchange.getIn().getHeader("id").toString();
        String table_name = exchange.getContext().resolvePropertyPlaceholders("{{table.name}}");
        Object empMiddleNameObject = employee.getEmpMiddleName() == null ? "" : employee.getEmpMiddleName();
        StringBuilder updateQuery = new StringBuilder();
        updateQuery.append("update " + table_name + " set ");
        updateQuery.append("empfirstname = '" + employee.getEmpFirstName());
        updateQuery.append("', empmiddlename = '" + empMiddleNameObject);
        updateQuery.append("', emplastname = '" + employee.getEmpLastName());
        updateQuery.append("', empdepartment = '" + employee.getEmpDepartment());
        updateQuery.append("', empsalary = " + employee.getEmpSalary());
        updateQuery.append(" where empid = " + id);
        exchange.getMessage().setHeader("CamelRetrieveGeneratedKeys", true);
        exchange.getMessage().setBody(updateQuery);
    }

}