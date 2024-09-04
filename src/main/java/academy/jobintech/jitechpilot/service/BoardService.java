package academy.jobintech.jitechpilot.service;

import academy.jobintech.jitechpilot.dto.BoardDTO;
import academy.jobintech.jitechpilot.dto.ResponseBoardPage;
import academy.jobintech.jitechpilot.dto.UserBoardRoleDTO;
import academy.jobintech.jitechpilot.entity.Section;
import academy.jobintech.jitechpilot.enums.UserRole;

import java.util.List;
import java.util.Set;

/**
 * @author Yassine CHALH
 */
public interface BoardService {
    BoardDTO createBoard(BoardDTO boardDTO);

    BoardDTO getBoardById(Long boardId);

    ResponseBoardPage getAllBoards(int pageNo, int pageSize, String sortBy, String sortDir);

    //TODO: Create project if not exists
    BoardDTO updateBoard(Long boardId, BoardDTO boardDTO);

    void deleteBoard(Long boardId);

    BoardDTO createBoardByUser(Long workspaceId,BoardDTO boardDTO);
    BoardDTO createBoardFromTemplate(Long workspaceId,BoardDTO boardDTO);

    List<BoardDTO> getBoardsByWorkspace(Long workspaceId);

    void createNsection(List<Section> sections);

    List<BoardDTO> getBoardsByIds(Set<Long> boardIds);

}
