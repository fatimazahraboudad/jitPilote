package academy.jobintech.jitechpilot.repository;


import academy.jobintech.jitechpilot.entity.Workspace;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WorkspaceRepository extends JpaRepository<Workspace, Long> {
}
