package academy.jobintech.jitechpilot.repository;

import academy.jobintech.jitechpilot.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author Yassine CHALH
 */
public interface TaskRepository extends JpaRepository<Task, Long> {

    List<Task> findByticketTicketId(Long ticketId);

}
