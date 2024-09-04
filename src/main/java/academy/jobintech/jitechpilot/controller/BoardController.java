package academy.jobintech.jitechpilot.controller;

import academy.jobintech.jitechpilot.dto.BoardDTO;
import academy.jobintech.jitechpilot.dto.ResponseBoardPage;
import academy.jobintech.jitechpilot.dto.UserBoardRoleDTO;
import academy.jobintech.jitechpilot.entity.User;
import academy.jobintech.jitechpilot.enums.UserRole;
import academy.jobintech.jitechpilot.serviceImpl.BoardServiceImpl;
import academy.jobintech.jitechpilot.serviceImpl.UserBoardRoleServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/boards")
@CrossOrigin(
        origins = {"*"},
        allowedHeaders = "*",
        methods = {RequestMethod.GET,RequestMethod.DELETE,RequestMethod.POST,RequestMethod.PUT}
)
public class BoardController {
    @Autowired
    private BoardServiceImpl boardService;
    @Autowired
    private UserBoardRoleServiceImpl userBoardRoleService;

    @PostMapping
    public ResponseEntity<BoardDTO> createBoard(
            @Valid @RequestBody BoardDTO boardDTO
    ){
        BoardDTO newBoard = boardService.createBoard(boardDTO);
        return new ResponseEntity<>(newBoard , HttpStatus.CREATED);
    }

    @GetMapping("/{boardId}")
    public ResponseEntity<BoardDTO> getBoardById(@PathVariable("boardId") Long boardId){
        BoardDTO boardDTOResponse = boardService.getBoardById(boardId);
        return new ResponseEntity<>(boardDTOResponse, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<ResponseBoardPage>  getAllBoards(
            @RequestParam(name = "pageNo" ,defaultValue = "0" ,required = false) int pageNo,
            @RequestParam(name = "pageSize" ,defaultValue = "20" ,required = false) int pageSize,
            @RequestParam(name = "sortBy" ,defaultValue = "boardId" ,required = false) String sortBy,
            @RequestParam(name = "sortDir" ,defaultValue = "asc" ,required = false) String sortDir
    ){
        return ResponseEntity.ok(boardService.getAllBoards(pageNo, pageSize , sortBy , sortDir));
    }

    @PutMapping("/{boardId}")
    public ResponseEntity<BoardDTO> updateBoard(
            @Valid @RequestBody BoardDTO boardDTO,
            @PathVariable("boardId") Long boardId){
        BoardDTO updatedBoard = boardService.updateBoard(boardId , boardDTO);
        return new ResponseEntity<>(updatedBoard , HttpStatus.OK);
    }

    @DeleteMapping("/{boardId}")
    public ResponseEntity<Void> deleteBoard(@PathVariable("boardId") Long boardId){
        userBoardRoleService.deleteUserBoardRoleByBoardId(boardId);
        boardService.deleteBoard(boardId);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{userId}/{workspaceId}/board")
    public ResponseEntity<BoardDTO> createBoardByUser(
            @PathVariable Long userId,
            @PathVariable Long workspaceId,
           @Valid @RequestBody BoardDTO boardDTO

    ){
        BoardDTO newBoard = boardService.createBoardByUser(workspaceId,boardDTO);
        UserBoardRoleDTO userBoardRoleDTO = new UserBoardRoleDTO(
                userId,
                newBoard.getBoardId(),
                UserRole.ADMIN
        );
        userBoardRoleService.assignBoardRoleToUser(userBoardRoleDTO);
        return new ResponseEntity<>(newBoard , HttpStatus.CREATED);
    }

    @GetMapping("/w/{workspaceId}")
    public ResponseEntity<List<BoardDTO>>  getBoardsByWorkspace(
            @PathVariable Long workspaceId
    ){
        return ResponseEntity.ok(boardService.getBoardsByWorkspace(workspaceId));
    }
    @PostMapping("/create/{userId}/{workspaceId}/template")
    public ResponseEntity<BoardDTO> createBoardByUserTemplate(
            @PathVariable Long userId,
            @PathVariable Long workspaceId,
            @Valid @RequestBody BoardDTO boardDTO

    ){
        BoardDTO newBoard = boardService.createBoardFromTemplate(workspaceId,boardDTO);
        UserBoardRoleDTO userBoardRoleDTO = new UserBoardRoleDTO(
                userId,
                newBoard.getBoardId(),
                UserRole.ADMIN
        );
        userBoardRoleService.assignBoardRoleToUser(userBoardRoleDTO);
        return new ResponseEntity<>(newBoard , HttpStatus.CREATED);
    }

    @GetMapping("/w/{workspaceId}/u/{userId}")
    public ResponseEntity<List<BoardDTO>>  getBoardsByWorkspaceAndUser(
            @PathVariable Long workspaceId,
            @PathVariable Long userId
    ){
        return ResponseEntity.ok(userBoardRoleService.getBoardsByUserForWorkspace(userId,workspaceId));
    }
}
