package academy.jobintech.jitechpilot.service;

import academy.jobintech.jitechpilot.dto.UserRequestDto;
import academy.jobintech.jitechpilot.dto.UserResponseDto;
import academy.jobintech.jitechpilot.entity.Ticket;
import academy.jobintech.jitechpilot.entity.User;

import java.util.List;

public interface UserService {

    UserResponseDto createUser(UserRequestDto userRequestDto);
    UserResponseDto getUserById(Long userId);
    List<UserResponseDto> getAllUsers();
    UserResponseDto updateUser(Long userId, UserRequestDto userRequestDto);
    void deleteUser(Long userId);

//    void affecterUserToTeam(long userId, long teamId);
//
//    List<UserResponseDto> getUsersByTeam (Long teamId);

    User getUsertByIdHelper(Long userId);

    UserResponseDto getUserByEmail(String emailUser);

    UserResponseDto inviteUserToWorkspace(String emailUser);


}
