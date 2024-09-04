package academy.jobintech.jitechpilot.serviceImpl;

import academy.jobintech.jitechpilot.dto.UserResponseDto;
import academy.jobintech.jitechpilot.dto.UserWorkspaceRoleDto;
import academy.jobintech.jitechpilot.entity.*;
import academy.jobintech.jitechpilot.mapper.UserResponseEntityDTOMapper;
import academy.jobintech.jitechpilot.mapper.UserWorkspaceRoleDTOMapper;
import academy.jobintech.jitechpilot.repository.UserRepository;
import academy.jobintech.jitechpilot.repository.UserWorkspaceRoleRepository;
import academy.jobintech.jitechpilot.service.UserWorkspaceRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserWorkspaceRoleServiceImpl implements UserWorkspaceRoleService {

    @Autowired
    private UserWorkspaceRoleRepository userWorkspaceRoleRepository;
    @Autowired
    private UserServiceImpl userService;
    @Autowired
    private UserResponseEntityDTOMapper userResponseEntityDTOMapper;
    @Autowired
    private WorkspaceServiceImpl workspaceService;
    @Autowired
    private UserWorkspaceRoleDTOMapper userWorkspaceRoleDTOMapper;

    @Override
    public UserWorkspaceRoleDto assignWorkspaceRoleToUser(UserWorkspaceRoleDto userWorkspaceRoleDto) {
        User user = userService.getUsertByIdHelper(userWorkspaceRoleDto.getUserId());
        Workspace workspace = workspaceService.getWorkspaceByIdHelper(userWorkspaceRoleDto.getWorkspaceId());
        RoleWorkspaceId roleWorkspaceId = new RoleWorkspaceId(userWorkspaceRoleDto.getUserId(),userWorkspaceRoleDto.getWorkspaceId());
        UserWorkspaceRole userWorkspaceRole = new UserWorkspaceRole(
                roleWorkspaceId,
                user,
                workspace,
                userWorkspaceRoleDto.getUserRole()
        );
        UserWorkspaceRole saveUserWorkspaceRole = userWorkspaceRoleRepository.save(userWorkspaceRole);

        return userWorkspaceRoleDTOMapper.toDto(saveUserWorkspaceRole);
    }

    @Override
    public void deleteUserWorkspaceRoleByWorkspaceId(Long workspaceId) {
        List<UserWorkspaceRole> userWorkspaceRoles = userWorkspaceRoleRepository.findByworkspaceWorkspaceId(workspaceId);
        userWorkspaceRoleRepository.deleteAll(userWorkspaceRoles);
    }

    @Override
    public void deleteUserWorkspaceRoleByUserId(Long userId) {
        List<UserWorkspaceRole> userWorkspaceRoles = userWorkspaceRoleRepository.findByuserUserId(userId);
        userWorkspaceRoleRepository.deleteAll(userWorkspaceRoles);
    }


    public List<UserResponseDto> getUsersByWorkspaceId(Long workspaceId) {
        List<UserWorkspaceRole> userWorkspaces = userWorkspaceRoleRepository.findByworkspaceWorkspaceId(workspaceId);
        return userResponseEntityDTOMapper.toDtos(userWorkspaces.stream().map(UserWorkspaceRole::getUser).collect(Collectors.toList()));
    }


}
