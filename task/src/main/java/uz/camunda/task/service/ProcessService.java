package uz.camunda.task.service;

import io.camunda.zeebe.client.ZeebeClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.camunda.task.dto.request.RequestDto;

@Service
@RequiredArgsConstructor
public class ProcessService {

    private final ZeebeClient zeebeClient;
    private final LoggingService loggingService;

    public String startProcess(RequestDto request) {
        var result = zeebeClient.newCreateInstanceCommand()
                .bpmnProcessId("application-process")
                .latestVersion()
                .variables(request)
                .send()
                .join();

        loggingService.logProcessStart(result.getProcessInstanceKey(), request.getClientId());
        return "Process started with instance key: " + result.getProcessInstanceKey();
    }

    public String startCreateClient(String clientId) {
        var result = zeebeClient.newCreateInstanceCommand()
                .bpmnProcessId("end-event")
                .latestVersion()
                .variables(clientId)
                .send()
                .join();

        loggingService.logProcessEndByNewClient(result.getProcessInstanceKey(), clientId);

        return String.valueOf(result.getProcessInstanceKey());
    }
}