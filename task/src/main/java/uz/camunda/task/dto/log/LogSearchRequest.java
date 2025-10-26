package uz.camunda.task.dto.log;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class LogSearchRequest {
    private String processInstanceId;
    private String clientId;
    private String eventType;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private String activityName;
    private Integer page = 0;
    private Integer size = 50;

    public boolean hasDateFilter() {
        return startDate != null || endDate != null;
    }
}
