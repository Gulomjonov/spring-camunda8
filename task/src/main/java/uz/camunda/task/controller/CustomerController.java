package uz.camunda.task.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.camunda.task.dto.client.CustomerDto;
import uz.camunda.task.dto.request.RequestDto;
import uz.camunda.task.service.CustomerService;

import java.util.Map;

@RestController
@RequestMapping()
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

    @PostMapping("/create/customer")
    public ResponseEntity<Map<String, Object>> startApplicationProcess(
            @RequestBody CustomerDto customerDto) {

        try {

            String str = customerService.createCustomer(customerDto);

            return ResponseEntity.ok(Map.of(
                    "success", true,
                    "message", "Client created successfully",
                    "processInstanceId", str,
                    "clientId", customerDto.getClientId()
            ));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of(
                    "success", false,
                    "message", "Failed to create client: " + e.getMessage()
            ));
        }
    }
}
