package com.pathaks.employee.Routes;

import com.pathaks.employee.Beans.ResponseBean;
import com.pathaks.employee.Entities.Employee;
import com.pathaks.employee.Processors.ExceptionProcessor;

import org.apache.camel.Exchange;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.jackson.JacksonDataFormat;
import org.apache.camel.component.jackson.ListJacksonDataFormat;
import org.apache.camel.model.dataformat.JsonLibrary;
import org.springframework.stereotype.Component;

@Component
public class GetAllEmployeesRoute extends RouteBuilder{

    @Override
    public void configure() throws Exception {
        JacksonDataFormat format = new ListJacksonDataFormat(Employee.class);

        onException(Exception.class)
        .process(new ExceptionProcessor())
        .handled(true)
        .to("log:com.pathaks.employee.getAllEmployees?showHeaders=true&showBody=true");

        from("direct:getAllEmployees")
        .log("============> Get All Employees <============")
        .setBody(simple("select * from {{table.name}}"))
        .to("jdbc:dataSource")
        .choice()
            .when(body().isNotNull())
                .log("Listing all employees in database")
                .marshal().json(JsonLibrary.Jackson)
                .unmarshal(format)
                .bean(ResponseBean.class, "responseEmployees('SUCCESS', ${body})")
                .to("log:com.pathaks.employee.getAllEmployees?showHeaders=true&showBody=true")
            .otherwise()
                .log("No records found in database")
                .setHeader(Exchange.HTTP_RESPONSE_CODE, constant(400))
                .bean(ResponseBean.class, "response('ERROR', 'No records found in database')")
                .to("log:com.pathaks.employee.getAllEmployees?showHeaders=true&showBody=true")
        .end()
        .choice()
            .when()
                .simple("${header.accept} == 'application/xml'")
                .setHeader(Exchange.CONTENT_TYPE, constant("application/xml"))
            .otherwise()
                .setHeader(Exchange.CONTENT_TYPE, constant("application/json"))
        .end()
        .to("log:com.pathaks.employee.getAllEmployees?showHeaders=true&showBody=true");
    }
}
