package academy.jobintech.jitechpilot.mapper;

import academy.jobintech.jitechpilot.dto.UserResponseDto;
import academy.jobintech.jitechpilot.entity.User;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class UserResponseEntityDTOMapper implements EntityDTOMapper<User, UserResponseDto> {

    private final ModelMapper modelMapper;

    public UserResponseEntityDTOMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public User toEntity(UserResponseDto dto) {
        User user=modelMapper.map(dto,User.class);
        return user;
    }

    @Override
    public UserResponseDto toDto(User entity) {
        UserResponseDto userResponseDto= modelMapper.map(entity, UserResponseDto.class);
        return userResponseDto;
    }
}
