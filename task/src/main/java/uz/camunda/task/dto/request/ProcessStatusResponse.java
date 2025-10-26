package uz.camunda.task.dto.request;

import lombok.Data;
import uz.camunda.task.model.log.ProcessLog;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Data
public class ProcessStatusResponse {
    private boolean success;
    private String processInstanceId;
    private String clientId;
    private String currentActivity;
    private String processStatus;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private Map<String, Object> variables;
    private List<ProcessLog> logs;
    private String errorMessage;

    public static ProcessStatusResponse create(String processInstanceId, String clientId,
                                               List<ProcessLog> logs, Map<String, Object> variables) {
        ProcessStatusResponse response = new ProcessStatusResponse();
        response.setSuccess(true);
        response.setProcessInstanceId(processInstanceId);
        response.setClientId(clientId);
        response.setLogs(logs);
        response.setVariables(variables);

        if (logs != null && !logs.isEmpty()) {
            ProcessLog lastLog = logs.get(logs.size() - 1);
            response.setCurrentActivity(lastLog.getActivityName());
            response.setStartTime(logs.get(0).getTimestamp());

            if ("PROCESS_END".equals(lastLog.getEventType())) {
                response.setEndTime(lastLog.getTimestamp());
                response.setProcessStatus("COMPLETED");
            } else {
                response.setProcessStatus("IN_PROGRESS");
            }
        }

        return response;
    }
}
