package uz.camunda.task.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import uz.camunda.task.dto.external.ExternalSystemResponse;
import uz.camunda.task.model.client.Customer;
import uz.camunda.task.model.enums.Status;
import uz.camunda.task.repository.client.CustomerRepository;

import java.util.Map;
import java.util.HashMap;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class ExternalSystemService {

    private final RestTemplate restTemplate;
    private final LoggingService loggingService;
    private final CustomerRepository customerRepository;

    @Value("${external.system.url:http://localhost:4000/api/check-status}")
    private String externalSystemUrl;

    // For stub implementation when external system is not available
    private final Random random = new Random();

    /**
     * Check client status in external system
     * @param clientId Client identifier
     * @return Status: SUCCESS or FAILED
     */
    public String checkClientStatus(String clientId) {
        try {

            Customer customer = customerRepository.findByClientId(clientId);

            if (customer.getStatus().equals(Status.ACTIVE)) {
                loggingService.logInfo("External system response received",
                        Map.of("clientId", clientId, "status", customer.getStatus()));
                return customer.getStatus().toString();
            } else {
                loggingService.logError("External system returned error",
                        Map.of("clientId", clientId, "statusCode", customer.getStatus()));
                return "FAILED";
            }

        } catch (Exception e) {
            loggingService.logError("Error calling external system: " + e.getMessage(),
                    Map.of("clientId", clientId));

            // Fallback to stub implementation
            return getStubClientStatus(clientId);
        }
    }

    /**
     * Stub implementation for external system (for testing/demo purposes)
     */
    private String getStubClientStatus(String clientId) {
        // Simple logic: clients with odd numbers get SUCCESS, even get FAILED
        try {
            // Extract number from client ID (e.g., CLIENT001 -> 1)
            String numberPart = clientId.replaceAll("[^0-9]", "");
            if (!numberPart.isEmpty()) {
                int clientNumber = Integer.parseInt(numberPart);
                String status = (clientNumber % 2 == 1) ? "SUCCESS" : "FAILED";

                loggingService.logInfo("Using stub implementation for client status",
                        Map.of("clientId", clientId, "stubStatus", status));

                return status;
            }
        } catch (NumberFormatException e) {
            // If cannot parse number, use random
        }

        // Fallback to random status
        String status = random.nextBoolean() ? "SUCCESS" : "FAILED";
        loggingService.logInfo("Using random stub implementation for client status",
                Map.of("clientId", clientId, "randomStatus", status));

        return status;
    }

}