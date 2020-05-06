package com.pathaks.employee.Routes;

import com.pathaks.employee.Beans.JsonValidatorBean;
import com.pathaks.employee.Beans.ResponseBean;
import com.pathaks.employee.Entities.Employee;
import com.pathaks.employee.Processors.AddEmployeeProcessor;
import com.pathaks.employee.Processors.ExceptionProcessor;

import org.apache.camel.Exchange;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.dataformat.JsonLibrary;
import org.springframework.stereotype.Component;

@Component
public class AddEmployeeRoute extends RouteBuilder {

    @Override
    public void configure() throws Exception {

        onException(Exception.class)
         .process(new ExceptionProcessor())
         .handled(true)
         .to("log:com.pathaks.employee.addEmployee?showHeaders=true&showBody=true");

        from("direct:addEmployee")
        .bean(JsonValidatorBean.class, "validateEmployee(${body})")
        .process(new AddEmployeeProcessor())
        .log("Sending query")
        .to("log:com.pathaks.employee.addEmployee?showHeaders=true&showBody=true")
        .to("jdbc:dataSource")
        .choice()
            .when(simple("${header.CamelJdbcUpdateCount} != '0'"))
                .log("insert done")
                .setBody(simple("${header.CamelGeneratedKeysRows[0]}"))
				.marshal().json(JsonLibrary.Jackson)
				.unmarshal().json(JsonLibrary.Jackson, Employee.class)
				.bean(ResponseBean.class, "responseEmployee('SUCCESS', ${body})")
				.setHeader(Exchange.HTTP_RESPONSE_CODE, constant(201))
                .to("log:com.pathaks.employee.addEmployee?showHeaders=true&showBody=true")
            .otherwise()
                .log("Failed to insert ${header.request} into {{table.name}}")
                .setHeader(Exchange.HTTP_RESPONSE_CODE, constant(400))
                .bean(ResponseBean.class, "response('ERROR', 'Failed to insert record in {{table.name}}')")
                .to("log:com.pathaks.employee.addEmployee?showHeaders=true&showBody=true")
        .end()
        .to("log:com.pathaks.employee.addEmployee?showHeaders=true&showBody=true");
    }
}