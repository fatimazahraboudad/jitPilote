package academy.jobintech.jitechpilot.repository;

import academy.jobintech.jitechpilot.entity.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author Yassine CHALH
 */
public interface TicketRepository extends JpaRepository<Ticket, Long> {
    List<Ticket> findBysectionSectionId(Long sectionId);
}
