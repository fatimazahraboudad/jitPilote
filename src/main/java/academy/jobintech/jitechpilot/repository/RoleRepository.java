package academy.jobintech.jitechpilot.repository;

import academy.jobintech.jitechpilot.entity.UserBoardRole;
import academy.jobintech.jitechpilot.entity.RoleBoardId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<UserBoardRole, RoleBoardId> {
}
