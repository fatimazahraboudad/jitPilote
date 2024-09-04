package academy.jobintech.jitechpilot.mapper;

import academy.jobintech.jitechpilot.dto.TaskDTO;
import academy.jobintech.jitechpilot.entity.Task;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * @author Yassine CHALH
 */
@Component
@Scope(value = "singleton")
public class TaskDTOMapper implements EntityDTOMapper<Task, TaskDTO>{

    private final ModelMapper modelMapper;

    public TaskDTOMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public TaskDTO toDto(Task task) {

        TaskDTO dto = modelMapper.map(task, TaskDTO.class);
//        new TaskDTO();
//
//        dto.setTaskId(task.getTaskId());
//        dto.setTitle(task.getTitle());
//        dto.setDescription(task.getDescription());
//        dto.setStatus(task.getStatus());
//        dto.setDeadline(task.getDeadline());
//        //dto.setTicketId(task.getTicket().getTicketId());

        return dto;
    }

    @Override
    public Task toEntity(TaskDTO dto) {

        Task task =modelMapper.map(dto, Task.class);
//        new Task();
//
//        task.setTitle(dto.getTitle());
//        task.setDescription(dto.getDescription());
//        task.setStatus(dto.getStatus());
//        task.setDeadline(dto.getDeadline());

        return task;
    }
}
