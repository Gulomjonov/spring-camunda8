package uz.camunda.task.dto.request;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class RequestDto {

    private String clientId;
    private String processInstanceId;
}
