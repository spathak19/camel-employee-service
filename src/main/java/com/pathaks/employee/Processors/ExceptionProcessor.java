package com.pathaks.employee.Processors;

import com.pathaks.employee.Entities.Response;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.springframework.stereotype.Component;

@Component
public class ExceptionProcessor implements Processor {

    @Override
    public void process(Exchange exchange) throws Exception {

        Exception exception = exchange.getProperty(Exchange.EXCEPTION_CAUGHT, Exception.class);

        Response response = new Response();

        if (exception.getClass() == NullPointerException.class) {
            response = new Response("100",
                    "You are missing one or more values in your request body. The mandatory fields are 'empfirstname', 'empmiddlename', 'emplastname', 'empdepartment', 'empsalary' ");
        } else {
            response = new Response("100", exception.getMessage());
        }
        // exchange.getMessage().setHeader("Stacktrace", exception.getStackTrace());
        exchange.getMessage().setBody(response);

    }

}