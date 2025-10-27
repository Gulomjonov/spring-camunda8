package uz.camunda.task.repository.processlog;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.camunda.task.model.log.ProcessLog;

import java.util.List;
import java.util.UUID;

@Repository
public interface ProcessLogRepository extends JpaRepository<ProcessLog, UUID> {

    List<ProcessLog> findByProcessInstanceIdOrderByTimestampAsc(String processInstanceId);

    List<ProcessLog> findByClientIdOrderByTimestampAsc(String clientId);

}
