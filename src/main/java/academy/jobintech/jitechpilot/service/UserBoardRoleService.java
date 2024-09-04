package academy.jobintech.jitechpilot.service;

import academy.jobintech.jitechpilot.dto.BoardDTO;
import academy.jobintech.jitechpilot.dto.UserBoardRoleDTO;
import academy.jobintech.jitechpilot.entity.UserBoardRole;
import org.springframework.stereotype.Repository;

import java.util.List;


public interface UserBoardRoleService {
    UserBoardRoleDTO assignBoardRoleToUser(UserBoardRoleDTO userBoardRoleDTO);
    UserBoardRoleDTO updateBoardRoleToUser(UserBoardRoleDTO userBoardRoleDTO);
    void deleteUserBoardRoleByBoardId(Long board_id);
    List<BoardDTO> getBoardsByUserForWorkspace(Long userId, Long workspaceId);
}
