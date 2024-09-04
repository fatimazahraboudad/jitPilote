package academy.jobintech.jitechpilot.repository;

import academy.jobintech.jitechpilot.entity.Sprint;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SprintRepository extends JpaRepository<Sprint,Long> {

    List<Sprint> findByBoardBoardId(Long boardId);

}
