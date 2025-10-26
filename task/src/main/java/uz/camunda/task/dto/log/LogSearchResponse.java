package uz.camunda.task.dto.log;

import lombok.Data;
import uz.camunda.task.model.log.ProcessLog;

import java.util.List;

@Data
public class LogSearchResponse {
    private boolean success;
    private List<ProcessLog> logs;
    private int totalCount;
    private int page;
    private int size;
    private int totalPages;

    public static LogSearchResponse create(List<ProcessLog> logs, int totalCount, int page, int size) {
        LogSearchResponse response = new LogSearchResponse();
        response.setSuccess(true);
        response.setLogs(logs);
        response.setTotalCount(totalCount);
        response.setPage(page);
        response.setSize(size);
        response.setTotalPages((int) Math.ceil((double) totalCount / size));
        return response;
    }
}