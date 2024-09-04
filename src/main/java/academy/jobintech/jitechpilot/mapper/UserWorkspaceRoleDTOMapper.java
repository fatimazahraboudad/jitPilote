package academy.jobintech.jitechpilot.mapper;

import academy.jobintech.jitechpilot.dto.UserWorkspaceRoleDto;
import academy.jobintech.jitechpilot.entity.UserWorkspaceRole;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;


@Component
public class UserWorkspaceRoleDTOMapper implements  EntityDTOMapper<UserWorkspaceRole, UserWorkspaceRoleDto>{

    private final ModelMapper modelMapper;

    public UserWorkspaceRoleDTOMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public UserWorkspaceRoleDto toDto(UserWorkspaceRole entity) {
        UserWorkspaceRoleDto userWorkspaceRole=modelMapper.map(entity,UserWorkspaceRoleDto.class);
        return userWorkspaceRole;
    }

    @Override
    public UserWorkspaceRole toEntity(UserWorkspaceRoleDto dto) {
        UserWorkspaceRole userWorkspaceRole=modelMapper.map(dto,UserWorkspaceRole.class);
        return userWorkspaceRole;
    }
}
