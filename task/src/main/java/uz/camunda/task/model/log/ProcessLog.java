package uz.camunda.task.model.log;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uz.camunda.task.model.Auditable;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "process_logs")
public class ProcessLog extends Auditable<String> {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false, updatable = false)
    private UUID id;

    @Column(name = "process_instance_id")
    private String processInstanceId;

    @Column(name = "activity_id")
    private String activityId;

    @Column(name = "activity_name")
    private String activityName;

    @Column(name = "event_type")
    private String eventType;

    @Column(name = "message")
    private String message;

    @Column(name = "client_id")
    private String clientId;

    @Column(name = "timestamp")
    private LocalDateTime timestamp;

}
