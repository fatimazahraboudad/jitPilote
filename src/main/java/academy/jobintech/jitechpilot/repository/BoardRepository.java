package academy.jobintech.jitechpilot.repository;

import academy.jobintech.jitechpilot.entity.Board;
import academy.jobintech.jitechpilot.entity.User;
import academy.jobintech.jitechpilot.entity.Workspace;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface BoardRepository extends JpaRepository<Board, Long> {


    List<Board> findByworkspaceWorkspaceId(Long workspaceId);

}
