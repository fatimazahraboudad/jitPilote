package academy.jobintech.jitechpilot.service;

import academy.jobintech.jitechpilot.dto.TaskDTO;

import java.util.List;

/**
 * @author Yassine CHALH
 */
public interface TaskService {
    //TODO:
    TaskDTO createTask(Long ticketId ,TaskDTO taskDTO);

    TaskDTO updateTask(Long id, TaskDTO taskDTO);

    void deleteTask(Long id);

    TaskDTO getTaskById(Long id);

    List<TaskDTO> getAllTasks();
    List<TaskDTO> getTasksByTicketId(Long taskId);

}
