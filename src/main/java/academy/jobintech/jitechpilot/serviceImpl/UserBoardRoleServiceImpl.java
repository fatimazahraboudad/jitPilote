package academy.jobintech.jitechpilot.serviceImpl;

import academy.jobintech.jitechpilot.dto.BoardDTO;
import academy.jobintech.jitechpilot.dto.UserBoardRoleDTO;
import academy.jobintech.jitechpilot.entity.Board;
import academy.jobintech.jitechpilot.entity.RoleBoardId;
import academy.jobintech.jitechpilot.entity.User;
import academy.jobintech.jitechpilot.entity.UserBoardRole;
import academy.jobintech.jitechpilot.enums.UserRole;
import academy.jobintech.jitechpilot.exception.NotFoundException;
import academy.jobintech.jitechpilot.mapper.UserBoardRoleDTOMapper;
import academy.jobintech.jitechpilot.repository.UserBoardRoleRepository;
import academy.jobintech.jitechpilot.service.UserBoardRoleService;
import org.hibernate.query.sql.internal.ParameterRecognizerImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserBoardRoleServiceImpl implements UserBoardRoleService {
    @Autowired
    private UserBoardRoleRepository userBoardRoleRepository;
    @Autowired
    private UserServiceImpl userService;
    @Autowired
    private BoardServiceImpl boardService;
    @Autowired
    private UserBoardRoleDTOMapper userBoardRoleDTOMapper;
    @Override
    public UserBoardRoleDTO assignBoardRoleToUser(UserBoardRoleDTO userBoardRoleDTO) {
        User user = userService.getUsertByIdHelper(userBoardRoleDTO.getUserId());
        Board board = boardService.getBoardByIdHelper(userBoardRoleDTO.getBoardId());
        RoleBoardId roleBoardId = new RoleBoardId(userBoardRoleDTO.getUserId(),userBoardRoleDTO.getBoardId());
        UserBoardRole userBoardRole = new UserBoardRole(
                roleBoardId,
                user,
                board,
                userBoardRoleDTO.getUserRole()
        );
        UserBoardRole saveUserBoardRole = userBoardRoleRepository.save(userBoardRole);

        return userBoardRoleDTOMapper.toDto(saveUserBoardRole);
    }
    public UserBoardRole getUserBoardRole(Long userId , Long boardId) {
        User user = userService.getUsertByIdHelper(userId);
        Board board = boardService.getBoardByIdHelper(boardId);
        RoleBoardId roleBoardId = new RoleBoardId(userId , boardId);
        UserBoardRole userBoardRole = userBoardRoleRepository.findById(roleBoardId)
                .orElseThrow(() -> new NotFoundException("UserBoardRole not found on id: " + roleBoardId));
        return userBoardRole;
    }

    @Override
    public UserBoardRoleDTO updateBoardRoleToUser(UserBoardRoleDTO userBoardRoleDTO) {
        UserBoardRole userBoardRole = getUserBoardRole(userBoardRoleDTO.getUserId() , userBoardRoleDTO.getBoardId());
        userBoardRole.setUser_role(userBoardRoleDTO.getUserRole());
        UserBoardRole savedUserBoardRole = userBoardRoleRepository.save(userBoardRole);
        return userBoardRoleDTOMapper.toDto(savedUserBoardRole);
    }

    @Override
    public void deleteUserBoardRoleByBoardId(Long board_id) {
        List<UserBoardRole> userBoardRoles = userBoardRoleRepository.findByboardBoardId(board_id);
        userBoardRoleRepository.deleteAll(userBoardRoles);
    }

    @Override
    public List<BoardDTO> getBoardsByUserForWorkspace(Long userId, Long workspaceId) {
        List<UserBoardRole> userBoardRoles = userBoardRoleRepository.findByUserUserId(userId);
        Set<Long> boardIds1 = userBoardRoles.stream().map(ubr -> ubr.getBoard().getBoardId()).collect(Collectors.toSet());
        List<BoardDTO> boardListByWorkspace = boardService.getBoardsByWorkspace(workspaceId);
        Set<Long> boardIds2 = boardListByWorkspace.stream().map(b->b.getBoardId()).collect(Collectors.toSet());
        List<BoardDTO> boardList = boardService.getBoardsByIds(getMutualIds(boardIds1 , boardIds2));

        return boardList;
    }
    public static Set<Long> getMutualIds(Set<Long> set1, Set<Long> set2) {
        Set<Long> mutualIds = new HashSet<>();

        // Iterate through the smaller set for efficiency
        Set<Long> smallerSet = set1.size() < set2.size() ? set1 : set2;
        Set<Long> largerSet = smallerSet == set1 ? set2 : set1;

        for (Long element : smallerSet) {
            if (largerSet.contains(element)) {
                mutualIds.add(element);
            }
        }

        return mutualIds;
    }

}
