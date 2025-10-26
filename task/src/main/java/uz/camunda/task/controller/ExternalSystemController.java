package uz.camunda.task.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Random;

@RestController
@RequestMapping()
public class ExternalSystemController {

    private final Random random = new Random();

    /**
     * Stub endpoint for external system status check
     */
    @PostMapping("/external/check-status")
    public ResponseEntity<Map<String, Object>> checkClientStatus(
            @RequestBody Map<String, Object> request) {

        String clientId = (String) request.get("clientId");

        // Simulate external system behavior
        boolean isSuccess = random.nextDouble() > 0.3; // 70% success rate

        String status = isSuccess ? "SUCCESS" : "FAILED";
        String message = isSuccess ? "Client status verified successfully"
                : "Client verification failed";

        // Simulate processing delay
        try {
            Thread.sleep(random.nextInt(1000) + 500); // 500-1500ms delay
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        return ResponseEntity.ok(Map.of(
                "status", status,
                "message", message,
                "clientId", clientId,
                "timestamp", java.time.LocalDateTime.now().toString()
        ));
    }

    /**
     * Health check for external system
     */
    @GetMapping("/external/health")
    public ResponseEntity<Map<String, String>> healthCheck() {
        return ResponseEntity.ok(Map.of(
                "status", "UP",
                "service", "External System Stub",
                "timestamp", java.time.LocalDateTime.now().toString()
        ));
    }
}