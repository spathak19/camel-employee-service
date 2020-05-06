package com.pathaks.employee.Beans;

import java.util.List;

import com.pathaks.employee.Entities.AllEmployeesResponse;
import com.pathaks.employee.Entities.Employee;
import com.pathaks.employee.Entities.EmployeeResponse;
import com.pathaks.employee.Entities.Response;

import org.springframework.context.annotation.Bean;


public class ResponseBean {

    @Bean(name ="response")
    /**
     * Processes the response to be sent to the client
     * 
     * @param resCode the response code, 0 for success and 100 for failure
     * @throws resBody the body to be sent with the response.
     */
    public Response response(String resCode, String resBody){
        
        System.out.println(
            resBody
        );
        return new Response(resCode, resBody);

    }
    @Bean(name ="responseEmployee")
    /**
     * Processes the response to be sent to the client
     * 
     * @param resCode the response code, 0 for success and 100 for failure
     * @throws resBody the body to be sent with the response.
     */
    public EmployeeResponse responseEmployee(String resCode, Employee resBody){
        
        System.out.println(
            resBody
        );
        return new EmployeeResponse(resCode, resBody);

    }
    @Bean(name ="responseEmployees")
    /**
     * Processes the response to be sent to the client
     * 
     * @param resCode the response code, 0 for success and 100 for failure
     * @throws resBody the body to be sent with the response.
     */
    public AllEmployeesResponse responseEmployees(String resCode, List<Employee> resBody){
        
        System.out.println(
            resBody
        );
        return new AllEmployeesResponse(resCode, resBody);

    }
    
}