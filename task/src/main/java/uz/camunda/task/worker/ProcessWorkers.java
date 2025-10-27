package uz.camunda.task.worker;

import io.camunda.zeebe.client.api.response.ActivatedJob;
import io.camunda.zeebe.spring.client.annotation.JobWorker;
import io.camunda.zeebe.spring.client.annotation.ZeebeWorker;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import uz.camunda.task.service.ExternalSystemService;
import uz.camunda.task.service.LoggingService;

import java.util.Map;

@Component
@RequiredArgsConstructor
public class ProcessWorkers {

    private final LoggingService loggingService;
    private final ExternalSystemService externalSystemService;

    @JobWorker(type = "check-client-status")
    public Map<String, Object> checkClientStatus(ActivatedJob job) {
        Map<String, Object> variables = job.getVariablesAsMap();
        String clientId = (String) variables.get("clientId");

        loggingService.logActivity(job.getProcessInstanceKey(), "check-client-status",
                "Checking client status in external system", clientId);

        String clientStatus = externalSystemService.checkClientStatus(clientId);

        loggingService.logGatewayDecision(job.getProcessInstanceKey(), "status-gateway",
                "Client status: " + clientStatus, clientId);

        return Map.of("clientStatus", clientStatus);
    }

    @JobWorker(type = "create-client")
    public void createClient(ActivatedJob job) {
        Map<String, Object> variables = job.getVariablesAsMap();
        String clientId = (String) variables.get("clientId");

        loggingService.logProcessEndByNewClient(job.getProcessInstanceKey(), clientId);

        String clientStatus = externalSystemService.checkClientStatus(clientId);

        loggingService.logGatewayDecision(job.getProcessInstanceKey(), "status-gateway",
                "Client status: " + clientStatus, clientId);
    }

//    @JobWorker(type = "log-success")
//    public Map<String, Object> logSuccess(ActivatedJob job) {
//        Map<String, Object> variables = job.getVariablesAsMap();
//        String clientId = (String) variables.get("clientId");
//
//        loggingService.logProcessEnd(job.getProcessInstanceKey(), "SUCCESS", clientId);
//
//        return Map.of("finalStatus", "APPROVED");
//    }
//
//    @JobWorker(type = "log-failure")
//    public Map<String, Object> logFailure(ActivatedJob job) {
//        Map<String, Object> variables = job.getVariablesAsMap();
//        String clientId = (String) variables.get("clientId");
//
//        loggingService.logProcessEnd(job.getProcessInstanceKey(), "FAILED", clientId);
//
//        return Map.of("finalStatus", "REJECTED");
//    }
}
