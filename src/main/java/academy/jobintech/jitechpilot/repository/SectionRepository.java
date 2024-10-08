package academy.jobintech.jitechpilot.repository;

import academy.jobintech.jitechpilot.entity.Section;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface SectionRepository extends JpaRepository<Section, Long> {


    //Optional<Section> findSectionBySectionTitle(String title);

    List<Section> findByboardBoardId(Long boardId);

}
