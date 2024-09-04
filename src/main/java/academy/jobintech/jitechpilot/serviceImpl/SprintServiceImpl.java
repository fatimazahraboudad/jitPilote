package academy.jobintech.jitechpilot.serviceImpl;

import academy.jobintech.jitechpilot.dto.SprintDTO;
import academy.jobintech.jitechpilot.entity.Board;
import academy.jobintech.jitechpilot.entity.Sprint;
import academy.jobintech.jitechpilot.exception.NotFoundException;
import academy.jobintech.jitechpilot.mapper.SprintDTOMapper;
import academy.jobintech.jitechpilot.repository.SprintRepository;
import academy.jobintech.jitechpilot.service.SprintService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Slf4j
@Service
public class SprintServiceImpl implements SprintService {
    private final SprintRepository sprintRepository;
    private final SprintDTOMapper sprintMapper;
    private final BoardServiceImpl boardService;
    @Autowired
    public SprintServiceImpl(SprintRepository sprintRepository , SprintDTOMapper sprintMapper , BoardServiceImpl boardService) {
        this.sprintRepository = sprintRepository;
        this.sprintMapper = sprintMapper;
        this.boardService = boardService;
    }


    @Override
    public SprintDTO createSprint(Long boardId, SprintDTO sprintDTO) {
        Board board = boardService.getBoardByIdHelper(boardId);
        Sprint sprint = sprintMapper.toEntity(sprintDTO);
        sprint.setBoard(board);
        Sprint createdSprint = sprintRepository.save(sprint);
        log.info("Sprint created successfully");
        return sprintMapper.toDto(createdSprint);
    }

    @Override
    public SprintDTO getSprintById(Long sprintId) {
        Sprint sprint = getSprintByIdHelper(sprintId);
        log.info("Fetching board by id: {} ", sprintId);
        return sprintMapper.toDto(sprint);
    }
    public Sprint getSprintByIdHelper(Long sprintId) {
        Sprint sprint = sprintRepository.findById(sprintId)
                .orElseThrow(() -> new NotFoundException("Sprint not found on :: " + sprintId));
        return sprint;
    }
    @Override
    public List<SprintDTO> getSprintsByBoard(Long boardId) {
        List<Sprint> sprintsByBoard = sprintRepository.findByBoardBoardId(boardId);
        return sprintMapper.toDtos(sprintsByBoard);
    }
}
