package com.silly.flowable.controller;

import org.flowable.engine.RuntimeService;
import org.flowable.engine.runtime.ProcessInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * REST Controller to trigger the BPMN process.
 */
@RestController
@RequestMapping("/api/process")
public class ProcessController {

    @Autowired
    private RuntimeService runtimeService;

    /**
     * Starts the simple process.
     * 
     * Usage: POST http://localhost:8080/api/process/start
     * Optional body: { "inputData": "your custom message" }
     */
    @PostMapping("/start")
    public Map<String, Object> startProcess(@RequestBody(required = false) Map<String, Object> requestBody) {

        // Prepare process variables
        Map<String, Object> variables = new HashMap<>();
        if (requestBody != null && requestBody.containsKey("inputData")) {
            variables.put("inputData", requestBody.get("inputData"));
        }

        // Start the process
        ProcessInstance processInstance = runtimeService.startProcessInstanceByKey("simpleProcess", variables);

        // Prepare response
        Map<String, Object> response = new HashMap<>();
        response.put("message", "Process started successfully!");
        response.put("processInstanceId", processInstance.getId());
        response.put("processDefinitionId", processInstance.getProcessDefinitionId());
        response.put("isEnded", processInstance.isEnded());

        return response;
    }

    /**
     * Simple health check endpoint.
     */
    @GetMapping("/health")
    public Map<String, String> health() {
        Map<String, String> response = new HashMap<>();
        response.put("status", "UP");
        response.put("message", "Flowable POC is running!");
        return response;
    }
}
