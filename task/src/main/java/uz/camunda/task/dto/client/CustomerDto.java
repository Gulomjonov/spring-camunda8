package uz.camunda.task.dto.client;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@NoArgsConstructor
public class CustomerDto {

    private String clientId;
    private String firstName;
    private String lastName;
    private Map<String, Object> applicationData;

    public Map<String, Object> toProcessVariables() {
        return Map.of(
                "clientId", clientId,
                "firstName", firstName,
                "lastName", lastName,
                "applicationData", applicationData != null ? applicationData : Map.of(),
                "requestTime", java.time.LocalDateTime.now().toString()
        );
    }
}
