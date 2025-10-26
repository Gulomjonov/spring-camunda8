package uz.camunda.task.dto.client;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class ApplicationResponse {
    private boolean success;
    private String message;
    private String processInstanceId;
    private String clientId;
    private LocalDateTime timestamp;
    private String status;

    public static ApplicationResponse success(String message, String processInstanceId, String clientId) {
        ApplicationResponse response = new ApplicationResponse();
        response.setSuccess(true);
        response.setMessage(message);
        response.setProcessInstanceId(processInstanceId);
        response.setClientId(clientId);
        response.setTimestamp(LocalDateTime.now());
        response.setStatus("PROCESS_STARTED");
        return response;
    }

    public static ApplicationResponse error(String message) {
        ApplicationResponse response = new ApplicationResponse();
        response.setSuccess(false);
        response.setMessage(message);
        response.setTimestamp(LocalDateTime.now());
        response.setStatus("ERROR");
        return response;
    }
}
