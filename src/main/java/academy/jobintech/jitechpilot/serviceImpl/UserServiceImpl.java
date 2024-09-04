package academy.jobintech.jitechpilot.serviceImpl;

import academy.jobintech.jitechpilot.dto.UserRequestDto;
import academy.jobintech.jitechpilot.dto.UserResponseDto;
import academy.jobintech.jitechpilot.entity.User;
import academy.jobintech.jitechpilot.exception.AlreadyExistsException;
import academy.jobintech.jitechpilot.exception.NotFoundException;
import academy.jobintech.jitechpilot.mapper.UserRequestEntityDTOMapper;
import academy.jobintech.jitechpilot.mapper.UserResponseEntityDTOMapper;
import academy.jobintech.jitechpilot.repository.UserRepository;
import academy.jobintech.jitechpilot.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

     static final Logger log = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private UserRequestEntityDTOMapper userRequestMapper;

    @Autowired
    private UserResponseEntityDTOMapper userResponseMapper;
    @Autowired
    private UserRepository userRepository;


    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public UserServiceImpl(BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    public UserResponseDto createUser(UserRequestDto userRequestDto) {
        userRepository.findUserByEmail(userRequestDto.getEmail())
                .ifPresent(existingUser -> {
                    throw new AlreadyExistsException("User already exists with this email");
                });
        User user =userRequestMapper.toEntity(userRequestDto);
        User newUser = userRepository.save(user);
        log.info("user created successfully name : {} ",newUser.getFirstName());
        return userResponseMapper.toDto(newUser);
    }

    @Override
    public UserResponseDto getUserById(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(()->new RuntimeException("user not found "));
        log.info("user found successfully id : {} ",userId);
        return userResponseMapper.toDto(user);
    }

    @Override
    public List<UserResponseDto> getAllUsers() {
        log.info("fetching all users");
        List<User> userList=userRepository.findAll();
        log.info("users found successfully");
        return userResponseMapper.toDtos(userList);
    }

    @Override
    public UserResponseDto  updateUser(Long userId, UserRequestDto userRequestDto) {
        User user = userRepository.findById(userId).orElseThrow(()->new NotFoundException("user not found with id : "+userId));

        user.setFirstName(userRequestDto.getFirstName());
        user.setLastName(userRequestDto.getLastName());
        user.setUserName(userRequestDto.getUserName());
        user.setEmail(userRequestDto.getEmail());
        user.setPassword(bCryptPasswordEncoder.encode(userRequestDto.getPassword()));

        User updateUser=userRepository.save(user);
        log.info("user updated successfully");
        return userResponseMapper.toDto(updateUser);
    }

    @Override
    public void deleteUser(Long userId) {
        getUserById(userId);
        userRepository.deleteById(userId);
        log.info("user with id : {} deleted successfully",userId);
    }

//    @Override
//    public void affecterUserToTeam(long userId, long teamId) {
//        User user = userRepository.findById(userId).orElseThrow(()->new NotFoundException("user not found with id : "+userId));
//        Team team =teamRepository.findById(teamId).orElseThrow(()->new NotFoundException("team not found with id : "+teamId));
//        log.info("affecting user to team");
//        user.setTeam(team);
//        userRepository.save(user);
//        log.info("user affected to team successfully");
//    }
//
//    @Override
//    public List<UserResponseDto> getUsersByTeam(Long teamId) {
//         List<User> userList =userRepository.findByteamTeamId(teamId);
//         log.info("Fetching all user in teams id {} ", teamId);
//         return userResponseMapper.toDtos(userList);
//
//    }

    @Override
    public User getUsertByIdHelper(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found on :: " + userId));
        return user;
    }

    @Override
    public UserResponseDto getUserByEmail(String emailUser) {
        User userInvite = userRepository.findUserByEmail(emailUser).orElse(null);
        return userResponseMapper.toDto(userInvite);
    }

    @Override
    public UserResponseDto inviteUserToWorkspace(String emailUser) {
        User user = userRepository.findUserByEmail(emailUser).orElse(null);
        return userResponseMapper.toDto(user);
    }
}
