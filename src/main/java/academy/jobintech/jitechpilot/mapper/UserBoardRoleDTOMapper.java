package academy.jobintech.jitechpilot.mapper;

import academy.jobintech.jitechpilot.dto.UserBoardRoleDTO;
import academy.jobintech.jitechpilot.entity.UserBoardRole;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope(value = "singleton")
public class UserBoardRoleDTOMapper implements EntityDTOMapper<UserBoardRole, UserBoardRoleDTO>{
    private ModelMapper modelMapper = new ModelMapper();
    @Override
    public UserBoardRoleDTO toDto(UserBoardRole entity) {
        return modelMapper.map(entity , UserBoardRoleDTO.class);
    }

    @Override
    public UserBoardRole toEntity(UserBoardRoleDTO dto) {
        return modelMapper.map(dto , UserBoardRole.class);
    }
}
