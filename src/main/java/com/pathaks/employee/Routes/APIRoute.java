package com.pathaks.employee.Routes;

import com.pathaks.employee.Entities.Employee;
import com.pathaks.employee.Processors.ExceptionProcessor;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.rest.RestBindingMode;
import org.springframework.stereotype.Component;

@Component
public class APIRoute extends RouteBuilder{

    @Override
    public void configure() throws Exception {

        restConfiguration().component("servlet")
        .host("localhost").bindingMode(RestBindingMode.json).clientRequestValidation(true).enableCORS(true);


        onException(Exception.class)
         .process(new ExceptionProcessor())
         .handled(true);

        rest("/employee")
            .get("/")
                .produces("application/json")
                .to("direct:getAllEmployees")
            .get("/{id}")
                .produces("application/json") 
                .to("direct:getEmployee")
            .post("/")
                .consumes("application/json")
                .type(Employee.class)
                .to("direct:addEmployee")
            .put("/{id}")
                .consumes("application/json")
                .type(Employee.class)
                .to("direct:updateEmployee")
            .delete("/{id}")
                .produces("application/json") 
                .to("direct:deleteEmployee");

    }

}