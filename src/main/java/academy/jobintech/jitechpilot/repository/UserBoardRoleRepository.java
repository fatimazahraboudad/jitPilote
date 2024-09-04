package academy.jobintech.jitechpilot.repository;

import academy.jobintech.jitechpilot.entity.RoleBoardId;
import academy.jobintech.jitechpilot.entity.UserBoardRole;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface UserBoardRoleRepository extends JpaRepository<UserBoardRole , RoleBoardId> {
    List<UserBoardRole> findByboardBoardId(Long boardId);

    List<UserBoardRole> findByUserUserId(Long userId);
}
