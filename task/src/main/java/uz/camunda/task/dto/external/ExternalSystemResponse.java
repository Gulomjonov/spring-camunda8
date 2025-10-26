package uz.camunda.task.dto.external;

import lombok.Data;
import java.time.LocalDateTime;
import java.util.Map;

@Data
public class ExternalSystemResponse {
    private String status; // SUCCESS or FAILED
    private String message;
    private String clientId;
    private LocalDateTime responseTime;
    private String errorCode;
    private Map<String, Object> additionalData;

    public ExternalSystemResponse() {
        this.responseTime = LocalDateTime.now();
    }

    public static ExternalSystemResponse success(String clientId) {
        ExternalSystemResponse response = new ExternalSystemResponse();
        response.setStatus("SUCCESS");
        response.setMessage("Client status verified successfully");
        response.setClientId(clientId);
        return response;
    }

    public static ExternalSystemResponse failed(String clientId, String reason) {
        ExternalSystemResponse response = new ExternalSystemResponse();
        response.setStatus("FAILED");
        response.setMessage("Client verification failed: " + reason);
        response.setClientId(clientId);
        return response;
    }
}