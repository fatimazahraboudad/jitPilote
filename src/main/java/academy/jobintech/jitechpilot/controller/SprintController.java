package academy.jobintech.jitechpilot.controller;

import academy.jobintech.jitechpilot.dto.BoardDTO;
import academy.jobintech.jitechpilot.dto.SectionDTO;
import academy.jobintech.jitechpilot.dto.SprintDTO;
import academy.jobintech.jitechpilot.serviceImpl.SprintServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/sprints")
@CrossOrigin(
        origins = {"*"},
        allowedHeaders = "*",
        methods = {RequestMethod.GET,RequestMethod.DELETE,RequestMethod.POST,RequestMethod.PUT}
)
public class SprintController {
    @Autowired
    private SprintServiceImpl sprintService;

    @PostMapping("/board/{boardId}")
    private ResponseEntity<SprintDTO> createSprintWithInBoard(
            @PathVariable long boardId,
            @RequestBody SprintDTO sprintDTO) {
        SprintDTO createdSprint = sprintService.createSprint(boardId, sprintDTO);
        return new ResponseEntity<>(createdSprint, HttpStatus.CREATED);
    }

    @GetMapping("/{sprintId}")
    public ResponseEntity<SprintDTO> getBoardById(
            @PathVariable("sprintId") Long sprintId
    ){
        SprintDTO sprint = sprintService.getSprintById(sprintId);
        return new ResponseEntity<>(sprint, HttpStatus.OK);
    }

    @GetMapping("/board/{boardId}")
    public ResponseEntity<List<SprintDTO>>  getSprintsByBoard(
            @PathVariable long boardId
            ){
        List<SprintDTO> sprintsByBoard = sprintService.getSprintsByBoard(boardId);
        return new ResponseEntity<>(sprintsByBoard, HttpStatus.OK);
    }
}
