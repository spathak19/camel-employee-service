package com.pathaks.employee.Beans;


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
    public Response response(String resCode, Object resBody){
        
        System.out.println(
            resBody
        );
        return new Response(resCode, resBody);

    }
    
}