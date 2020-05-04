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
            exchange.getMessage().setHeader(Exchange.HTTP_RESPONSE_CODE, "400");
            response = new Response("ERROR",
                    "You are missing one or more values in your request body. The mandatory fields are 'empfirstname', 'empmiddlename', 'emplastname', 'empdepartment', 'empsalary' ");
        }
        else if (exception.getClass() == NumberFormatException.class) {
            exchange.getMessage().setHeader(Exchange.HTTP_RESPONSE_CODE, "400");
            response = new Response("ERROR",
                    "You have provided a numeric value in either 'empfirstname', 'empmiddlename', 'emplastname', 'empdepartment'");
        } else {
            response = new Response("ERROR", exception.getMessage());
        }
        exchange.getMessage().setBody(response);

    }

}
