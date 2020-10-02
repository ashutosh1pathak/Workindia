package com.workindia.com.todo;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController

/**
 * Didn't get time\for implementing mysql Database.
 */
public class AgentController {


    List<Agent> agents = new ArrayList<>();
    Map<Integer,List<Todo>> agentVStodos = new HashMap<>();

    @PostMapping(value = "/app/agent")
    public RegisterResponse registerAgent( @RequestBody  Agent agent)
    {
        boolean isRegistered = agents.add(agent);
        if(isRegistered)
        {
            RegisterResponse registerResponse = new RegisterResponse();
            registerResponse.setStatus_code(HttpStatus.OK.value());
            registerResponse.setStatus("account created");
            return registerResponse;
        }else
        {
            RegisterResponse registerResponse = new RegisterResponse();
            registerResponse.setStatus_code(HttpStatus.INTERNAL_SERVER_ERROR.value());
            registerResponse.setStatus("account can't be created");
            return registerResponse;
        }
    }







    @PostMapping(value = "/app/agent/auth")
    public AuthResponse validateAgent( @RequestBody  Agent agent)
    {
       for(  Agent a :  agents)
       {
           if(a.equals(agent))
           {
                AuthResponse authSuccessResponse = new AuthResponse();
                authSuccessResponse.setAgent_id(a.getAgent_id());
                authSuccessResponse.setStatus("success");
                authSuccessResponse.setStatus_code(HttpStatus.OK.value());

                return authSuccessResponse;
            }
       }

        AuthResponse authFailureResponse = new AuthResponse();

        authFailureResponse.setStatus("failure");
        authFailureResponse.setStatus_code(HttpStatus.UNAUTHORIZED.value());
        return authFailureResponse;
    }






    @PostMapping(value = "/app/sites")
    public RegisterResponse addTodo( @RequestParam Integer agentId   , @RequestBody Todo todo )
    {
        List<Todo> todos = agentVStodos.get(agentId);
        if(todos == null)
        {
            List<Todo> td = new ArrayList<>();
            td.add(todo);
            agentVStodos.put(agentId,td);
        }else
            {
                todos.add(todo);
            }


        RegisterResponse addTodoResponse = new RegisterResponse();
        addTodoResponse.setStatus("success");
        addTodoResponse.setStatus_code(HttpStatus.OK.value());
        return addTodoResponse;
    }


    @GetMapping(value = "/app/sites/list")
    public List<Todo> getTodo(@RequestParam Integer agentId )
    {
        return agentVStodos.get(agentId);
    }





}
