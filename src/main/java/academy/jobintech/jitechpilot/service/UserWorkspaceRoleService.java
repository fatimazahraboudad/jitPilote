package academy.jobintech.jitechpilot.service;

import academy.jobintech.jitechpilot.dto.UserBoardRoleDTO;
import academy.jobintech.jitechpilot.dto.UserResponseDto;
import academy.jobintech.jitechpilot.dto.UserWorkspaceRoleDto;
import academy.jobintech.jitechpilot.entity.User;
import academy.jobintech.jitechpilot.entity.UserWorkspaceRole;

import java.util.List;

public interface UserWorkspaceRoleService {


    UserWorkspaceRoleDto assignWorkspaceRoleToUser(UserWorkspaceRoleDto userWorkspaceRoleDto);
    void deleteUserWorkspaceRoleByWorkspaceId(Long workspaceId);
    void deleteUserWorkspaceRoleByUserId(Long userId);

    List<UserResponseDto> getUsersByWorkspaceId(Long workspaceId);

}
