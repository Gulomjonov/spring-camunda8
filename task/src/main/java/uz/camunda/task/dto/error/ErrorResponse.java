package uz.camunda.task.dto.error;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class ErrorResponse {
    private LocalDateTime timestamp;
    private int status;
    private String error;
    private String message;
    private String path;
    private String errorCode;

    public ErrorResponse(int status, String error, String message, String path) {
        this.timestamp = LocalDateTime.now();
        this.status = status;
        this.error = error;
        this.message = message;
        this.path = path;
    }

    public static ErrorResponse badRequest(String message, String path) {
        return new ErrorResponse(400, "Bad Request", message, path);
    }

    public static ErrorResponse notFound(String message, String path) {
        return new ErrorResponse(404, "Not Found", message, path);
    }

    public static ErrorResponse internalError(String message, String path) {
        return new ErrorResponse(500, "Internal Server Error", message, path);
    }
}
