package academy.jobintech.jitechpilot.service;

import academy.jobintech.jitechpilot.dto.SprintDTO;

import java.util.List;

public interface SprintService {

    SprintDTO createSprint(Long boardId , SprintDTO sprintDTO);
    SprintDTO getSprintById(Long sprintId);
    List<SprintDTO> getSprintsByBoard(Long boardId);

}
