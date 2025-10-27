package uz.camunda.task.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.camunda.task.model.client.Customer;
import uz.camunda.task.model.enums.Status;
import uz.camunda.task.repository.client.CustomerRepository;

import java.util.Map;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class ExternalSystemService {

    private final LoggingService loggingService;
    private final CustomerRepository customerRepository;

    // Для реализации заглушки, когда внешняя система недоступна
    private final Random random = new Random();

    /**
     * Проверка статуса клиента во внешней системе
     *
     * @param clientId Идентификатор клиента
     * @return Статус: УСПЕХ или НЕУДАЧА
     */
    public String checkClientStatus(String clientId) {
        try {

            Customer customer = customerRepository.findByClientId(clientId);

            if (customer != null) {
                if (customer.getStatus().equals(Status.ACTIVE)) {
                    loggingService.logInfo("External system response successfully checked for clientId",
                            Map.of("clientId", clientId, "status", customer.getStatus()));
                    return "SUCCESS";
                } else if (customer.getStatus().equals(Status.INACTIVE) ||
                        customer.getStatus().equals(Status.DELETED) ||
                        customer.getStatus().equals(Status.BLOCKED)) {
                    loggingService.logError("External system response received",
                            Map.of("clientId", clientId, "statusCode", customer.getStatus()));
                    return "FAILED";
                }
            } else {
                loggingService.logToCreateCustomer("External system response need to create customer",
                        Map.of("clientId", clientId, "status", Status.PENDING));
                return "PENDING";
            }

        } catch (Exception e) {
            loggingService.logError("Error calling external system: " + e.getMessage(),
                    Map.of("clientId", clientId));

            // Fallback to stub implementation
            return getStubClientStatus(clientId);
        }
        return clientId;
    }

    /**
     * Реализация заглушки для внешней системы (для целей тестирования/демонстрации)
     */
    private String getStubClientStatus(String clientId) {
        // Простая логика: клиенты с нечетными номерами получают УСПЕХ, четные — НЕУДАЧУ
        try {
            // Извлечь номер из идентификатора клиента (например, CLIENT001 -> 1)
            String numberPart = clientId.replaceAll("[^0-9]", "");
            if (!numberPart.isEmpty()) {
                int clientNumber = Integer.parseInt(numberPart);
                String status = (clientNumber % 2 == 1) ? "SUCCESS" : "FAILED";

                loggingService.logInfo("Using stub implementation for client status",
                        Map.of("clientId", clientId, "stubStatus", status));

                return status;
            }
        } catch (NumberFormatException e) {
            // Если невозможно разобрать число, используйте случайное число
        }

        // Возврат к случайному статусу
        String status = random.nextBoolean() ? "SUCCESS" : "FAILED";
        loggingService.logInfo("Using random stub implementation for client status",
                Map.of("clientId", clientId, "randomStatus", status));

        return status;
    }

}