package uz.camunda.task.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.camunda.task.dto.request.RequestDto;
import uz.camunda.task.model.log.ProcessLog;
import uz.camunda.task.service.LoggingService;
import uz.camunda.task.service.ProcessService;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping()
@RequiredArgsConstructor
public class ProcessController {

    private final ProcessService processService;
    private final LoggingService loggingService;


    /**
     * Начать новый процесс подачи заявки
     */
    @PostMapping("/application/start")
    public ResponseEntity<Map<String, Object>> startApplicationProcess(
            @RequestBody RequestDto request) {

        try {
            String processInstanceId = processService.startProcess(request);

            return ResponseEntity.ok(Map.of(
                    "success", true,
                    "message", "Application process started successfully",
                    "processInstanceId", processInstanceId,
                    "clientId", request.getClientId()
            ));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of(
                    "success", false,
                    "message", "Failed to start application process: " + e.getMessage()
            ));
        }
    }

    /**
     * Получить журналы процессов по идентификатору экземпляра процесса
     */
    @GetMapping("/logs/process/{processInstanceId}")
    public ResponseEntity<List<ProcessLog>> getProcessLogs(
            @PathVariable String processInstanceId) {

        List<ProcessLog> logs = loggingService.getLogsByProcessInstanceId(processInstanceId);
        return ResponseEntity.ok(logs);
    }

    /**
     * Получить журналы процессов по идентификатору клиента
     */
    @GetMapping("/logs/client/{clientId}")
    public ResponseEntity<List<ProcessLog>> getClientLogs(
            @PathVariable String clientId) {

        List<ProcessLog> logs = loggingService.getLogsByClientId(clientId);
        return ResponseEntity.ok(logs);
    }

    /**
     * Получить все журналы процессов
     */
    @GetMapping("/logs")
    public ResponseEntity<List<ProcessLog>> getAllLogs() {
        List<ProcessLog> logs = loggingService.getAllLogs();
        return ResponseEntity.ok(logs);
    }
}