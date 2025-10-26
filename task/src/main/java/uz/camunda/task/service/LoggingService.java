package uz.camunda.task.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uz.camunda.task.model.log.ProcessLog;
import uz.camunda.task.repository.processlog.ProcessLogRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Service
@Transactional
@RequiredArgsConstructor
public class LoggingService {

    private final ProcessLogRepository processLogRepository;

    /**
     * Log process start event
     */
    public void logProcessStart(Long processInstanceKey, String clientId) {
        ProcessLog log = new ProcessLog();
        log.setProcessInstanceId(String.valueOf(processInstanceKey));
        log.setActivityId("start-event");
        log.setActivityName("Process Started");
        log.setEventType("PROCESS_START");
        log.setMessage("Application process started for client: " + clientId);
        log.setClientId(clientId);
        log.setTimestamp(LocalDateTime.now());

        processLogRepository.save(log);
    }

    /**
     * Log activity execution
     */
    public void logActivity(Long processInstanceKey, String activityId,
                            String activityName, String clientId) {
        ProcessLog log = new ProcessLog();
        log.setProcessInstanceId(String.valueOf(processInstanceKey));
        log.setActivityId(activityId);
        log.setActivityName(activityName);
        log.setEventType("ACTIVITY_EXECUTION");
        log.setMessage("Executing activity: " + activityName);
        log.setClientId(clientId);
        log.setTimestamp(LocalDateTime.now());

        processLogRepository.save(log);
    }

    /**
     * Log gateway decision
     */
    public void logGatewayDecision(Long processInstanceKey, String gatewayId,
                                   String decision, String clientId) {
        ProcessLog log = new ProcessLog();
        log.setProcessInstanceId(String.valueOf(processInstanceKey));
        log.setActivityId(gatewayId);
        log.setActivityName("Gateway Decision");
        log.setEventType("GATEWAY_DECISION");
        log.setMessage("Gateway decision: " + decision);
        log.setClientId(clientId);
        log.setTimestamp(LocalDateTime.now());

        processLogRepository.save(log);
    }

    /**
     * Log process end event
     */
    public void logProcessEnd(Long processInstanceKey, String status, String clientId) {
        ProcessLog log = new ProcessLog();
        log.setProcessInstanceId(String.valueOf(processInstanceKey));
        log.setActivityId("end-event");
        log.setActivityName("Process Completed");
        log.setEventType("PROCESS_END");
        log.setMessage("Process completed with status: " + status);
        log.setClientId(clientId);
        log.setTimestamp(LocalDateTime.now());

        processLogRepository.save(log);
    }

    /**
     * Log error event
     */
    public void logError(String errorMessage, Map<String, Object> context) {
        ProcessLog log = new ProcessLog();
        log.setProcessInstanceId(context.containsKey("processInstanceId") ?
                String.valueOf(context.get("processInstanceId")) : "N/A");
        log.setActivityId("error-handler");
        log.setActivityName("Error Occurred");
        log.setEventType("ERROR");
        log.setMessage("Error: " + errorMessage + " | Context: " + context);
        log.setClientId(context.containsKey("clientId") ?
                String.valueOf(context.get("clientId")) : "N/A");
        log.setTimestamp(LocalDateTime.now());

        processLogRepository.save(log);
    }

    /**
     * Log informational message
     */
    public void logInfo(String message, Map<String, Object> context) {
        ProcessLog log = new ProcessLog();
        log.setProcessInstanceId(context.containsKey("processInstanceId") ?
                String.valueOf(context.get("processInstanceId")) : "N/A");
        log.setActivityId("info-logger");
        log.setActivityName("Information");
        log.setEventType("INFO");
        log.setMessage("Info: " + message + " | Context: " + context);
        log.setClientId(context.containsKey("clientId") ?
                String.valueOf(context.get("clientId")) : "N/A");
        log.setTimestamp(LocalDateTime.now());

        processLogRepository.save(log);
    }

    /**
     * Get logs by process instance ID
     */
    public List<ProcessLog> getLogsByProcessInstanceId(String processInstanceId) {
        return processLogRepository.findByProcessInstanceIdOrderByTimestampAsc(processInstanceId);
    }

    /**
     * Get logs by client ID
     */
    public List<ProcessLog> getLogsByClientId(String clientId) {
        return processLogRepository.findByClientIdOrderByTimestampAsc(clientId);
    }

    public List<ProcessLog> getAllLogs() {
        return null;
    }
}
