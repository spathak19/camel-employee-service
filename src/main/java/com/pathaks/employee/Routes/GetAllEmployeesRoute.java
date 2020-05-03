package com.pathaks.employee.Routes;

import com.pathaks.employee.Beans.ResponseBean;
import com.pathaks.employee.Processors.ExceptionProcessor;

import org.apache.camel.Exchange;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class GetAllEmployeesRoute extends RouteBuilder{

    @Override
    public void configure() throws Exception {

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
                .to("log:com.pathaks.employee.getAllEmployees?showHeaders=true&showBody=true")
            .otherwise()
                .log("No records found in database")
                .setHeader(Exchange.HTTP_RESPONSE_CODE, constant(400))
                .bean(ResponseBean.class, "response('100', 'No records found in database')")
                .to("log:com.pathaks.employee.getAllEmployees?showHeaders=true&showBody=true")
        .end()
        .to("log:com.pathaks.employee.getAllEmployees?showHeaders=true&showBody=true");
    }
}
