package com.pathaks.employee.Routes;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class ValidateJsonRoute extends RouteBuilder{

    @Override
    public void configure() throws Exception {

        from("direct:validateJson")
        .to("json-validator:classpath:EmployeeJsonSchema.json");

    }
    
}