package academy.jobintech.jitechpilot.repository;

import academy.jobintech.jitechpilot.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findUserByEmail(String email);

    @Query("SELECT DISTINCT u FROM User u JOIN u.userBoardRoles ubr JOIN ubr.board b WHERE b.workspace.workspaceId = :workspaceId")
    List<User> findUsersByWorkspaceId(@Param("workspaceId") Long workspaceId);

//    List<User> findByteamTeamId(Long teamId);
}
