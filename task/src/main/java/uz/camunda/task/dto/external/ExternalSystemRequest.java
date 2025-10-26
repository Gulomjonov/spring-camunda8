package uz.camunda.task.dto.external;

import lombok.Data;
import java.time.LocalDateTime;
import java.util.Map;

@Data
public class ExternalSystemRequest {
    private String clientId;
    private String requestType = "CLIENT_STATUS_CHECK";
    private LocalDateTime requestTime;
    private Map<String, Object> additionalData;

    public ExternalSystemRequest() {
        this.requestTime = LocalDateTime.now();
    }

    public ExternalSystemRequest(String clientId) {
        this();
        this.clientId = clientId;
    }
}
