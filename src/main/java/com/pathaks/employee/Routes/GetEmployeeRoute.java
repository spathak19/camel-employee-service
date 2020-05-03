package com.pathaks.employee.Routes;

import com.pathaks.employee.Beans.ResponseBean;
import com.pathaks.employee.Processors.ExceptionProcessor;

import org.apache.camel.Exchange;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class GetEmployeeRoute extends RouteBuilder {

    @Override
    public void configure() throws Exception {

        onException(Exception.class)
        .process(new ExceptionProcessor())
        .to("log:com.pathaks.employee.getEmployee?showHeaders=true&showBody=true")
        .handled(true);

        from("direct:getEmployee")
        .log("============> Get Employee By ID <============")
        .setBody(simple("select * from tbl_employees where empid = ${header.id}"))
        .to("jdbc:dataSource?outputType=SelectOne")
        .choice()
            .when(body().isNotNull())
                .log("Found empid ${header.id} in database")
                .to("log:com.pathaks.employee.getEmployee?showHeaders=true&showBody=true")
            .otherwise()
                .log("Could not find empid ${header.id} in database")
                .setHeader(Exchange.HTTP_RESPONSE_CODE, constant(404))
                .bean(ResponseBean.class, "response('100', 'Could not find empid ${header.id} in database')")
                .to("log:com.pathaks.employee.getEmployee?showHeaders=true&showBody=true")
        .end()
        .to("log:com.pathaks.employee.getEmployee?showHeaders=true&showBody=true");
    }

}