package com.pathaks.employee.Routes;

import com.pathaks.employee.Beans.ResponseBean;
import com.pathaks.employee.Processors.*;

import org.apache.camel.Exchange;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class DeleteEmployeeRoute extends RouteBuilder{

    @Override
    public void configure() throws Exception {

        onException(Exception.class)
        .process(new ExceptionProcessor())
        .handled(true)
        .to("log:com.pathaks.employee.deleteEmployee?showHeaders=true&showBody=true");
        

        from("direct:deleteEmployee")
        .log("============> Delete Employee by ID<============")
        .setBody(simple("delete from {{table.name}} where empid = ${header.id}"))
        .to("jdbc:dataSource")
        .choice()
            .when(simple("${header.CamelJdbcUpdateCount} != '0'"))
                .log("Deleted empid ${header.id} from database")
                .bean(ResponseBean.class, "response('SUCCESS', 'Deleted empid ${header.id} from database')")
                .to("log:com.pathaks.employee.deleteEmployee?showHeaders=true&showBody=true")
            .otherwise()
                .log("Could not find empid ${header.id} in database")
                .setHeader(Exchange.HTTP_RESPONSE_CODE, constant(400))
                .bean(ResponseBean.class, "response('ERROR', 'Could not find empid ${header.id} in database')")
                .to("log:com.pathaks.employee.deleteEmployee?showHeaders=true&showBody=true")
        .end()
        .to("log:com.pathaks.employee.deleteEmployee?showHeaders=true&showBody=true");
    }
    
}