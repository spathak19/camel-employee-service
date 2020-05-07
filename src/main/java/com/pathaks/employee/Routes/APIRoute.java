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
        .host("localhost").bindingMode(RestBindingMode.json_xml).dataFormatProperty("prettyPrint", "true")
        .clientRequestValidation(true).enableCORS(true);


        onException(Exception.class)
         .process(new ExceptionProcessor())
         .handled(true);


        rest("/employee")
            .get("/")
                .to("direct:getAllEmployees")
            .get("/{id}")
                .to("direct:getEmployee")
            .post("/")
                .type(Employee.class)
                .to("direct:addEmployee")
            .put("/{id}")
                .type(Employee.class)
                .to("direct:updateEmployee")
            .delete("/{id}")
                .to("direct:deleteEmployee");

    }

}