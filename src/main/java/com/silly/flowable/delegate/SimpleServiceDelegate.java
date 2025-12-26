package com.silly.flowable.delegate;

import org.flowable.engine.delegate.DelegateExecution;
import org.flowable.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Component;

/**
 * Simple Java Delegate that gets executed when the Service Task runs.
 * This demonstrates how Flowable triggers a service from the BPMN process.
 */
@Component("simpleServiceDelegate")
public class SimpleServiceDelegate implements JavaDelegate {

    @Override
    public void execute(DelegateExecution execution) {
        System.out.println("============================================");
        System.out.println("🚀 SERVICE TRIGGERED FROM FLOWABLE!");
        System.out.println("============================================");
        System.out.println("Process Instance ID: " + execution.getProcessInstanceId());
        System.out.println("Process Definition ID: " + execution.getProcessDefinitionId());
        System.out.println("Current Activity ID: " + execution.getCurrentActivityId());
        System.out.println("Execution ID: " + execution.getId());

        // You can access process variables like this:
        Object inputData = execution.getVariable("inputData");
        if (inputData != null) {
            System.out.println("Input Data received: " + inputData);
        }

        // Set an output variable
        execution.setVariable("serviceResult", "Service executed successfully at " + java.time.LocalDateTime.now());

        System.out.println("============================================");
        System.out.println("✅ Service Task Completed!");
        System.out.println("============================================");
    }
}
