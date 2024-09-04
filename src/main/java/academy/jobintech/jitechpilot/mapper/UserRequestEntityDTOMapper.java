package academy.jobintech.jitechpilot.mapper;

import academy.jobintech.jitechpilot.dto.UserRequestDto;
import academy.jobintech.jitechpilot.entity.User;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class UserRequestEntityDTOMapper implements EntityDTOMapper<User, UserRequestDto > {

    private final ModelMapper modelMapper;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public UserRequestEntityDTOMapper(ModelMapper modelMapper, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.modelMapper = modelMapper;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    public User toEntity(UserRequestDto dto) {
        User user = modelMapper.map(dto, User.class);
        user.setPassword(bCryptPasswordEncoder.encode(dto.getPassword()));
        return user;
    }

    @Override
    public UserRequestDto toDto(User entity) {
        UserRequestDto userRequestDto=modelMapper.map(entity, UserRequestDto.class);

        return userRequestDto;
    }
}
