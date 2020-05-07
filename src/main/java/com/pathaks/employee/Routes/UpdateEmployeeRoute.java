package com.pathaks.employee.Routes;

import com.pathaks.employee.Beans.JsonValidatorBean;
import com.pathaks.employee.Beans.ResponseBean;
import com.pathaks.employee.Entities.Employee;
import com.pathaks.employee.Processors.ExceptionProcessor;
import com.pathaks.employee.Processors.UpdateEmployeeProcessor;

import org.apache.camel.Exchange;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.dataformat.JsonLibrary;
import org.springframework.stereotype.Component;

@Component
public class UpdateEmployeeRoute extends RouteBuilder{

	@Override
	public void configure() throws Exception {

		onException(Exception.class)
         .process(new ExceptionProcessor())
		 .handled(true)
		 .to("log:com.pathaks.employee.updateEmployee?showHeaders=true");

		from("direct:updateEmployee")
		.bean(JsonValidatorBean.class, "validateEmployee(${body})")
		.process(new UpdateEmployeeProcessor())
		.log("Sending query")
        .to("log:com.pathaks.employee.updateEmployee?showHeaders=true&showBody=true")
		.to("jdbc:dataSource")
		.choice()
			.when(simple("${header.CamelJdbcUpdateCount} != '0'"))
				.log("update done")
				.setBody(simple("${header.CamelGeneratedKeysRows[0]}"))
				.marshal().json(JsonLibrary.Jackson)
				.unmarshal().json(JsonLibrary.Jackson, Employee.class)
				.bean(ResponseBean.class, "responseEmployee('SUCCESS', ${body})")
				.setHeader(Exchange.HTTP_RESPONSE_CODE, constant(200))
				.to("log:com.pathaks.employee.updateEmployee?showHeaders=true&showBody=true")
			.otherwise()
				.log("Failed to insert ${header.request} into {{table.name}}")
				.setHeader(Exchange.HTTP_RESPONSE_CODE, constant(404))
				.bean(ResponseBean.class, "response('ERROR', 'Failed to update record with empid ${header.id} in {{table.name}}')")
				.to("log:com.pathaks.employee.updateEmployee?showHeaders=true&showBody=true")
		.end()
		.choice()
            .when()
                .simple("${header.accept} == 'application/xml'")
                .setHeader(Exchange.CONTENT_TYPE, constant("application/xml"))
            .otherwise()
                .setHeader(Exchange.CONTENT_TYPE, constant("application/json"))
        .end()
        .to("log:com.pathaks.employee.updateEmployee?showHeaders=true");
	}
}