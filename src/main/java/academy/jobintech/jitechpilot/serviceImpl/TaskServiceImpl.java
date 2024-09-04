package academy.jobintech.jitechpilot.serviceImpl;

import academy.jobintech.jitechpilot.dto.TaskDTO;
import academy.jobintech.jitechpilot.entity.Task;
import academy.jobintech.jitechpilot.entity.Ticket;
import academy.jobintech.jitechpilot.exception.NotFoundException;
import academy.jobintech.jitechpilot.mapper.TaskDTOMapper;
import academy.jobintech.jitechpilot.repository.TaskRepository;
import academy.jobintech.jitechpilot.service.TaskService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;
    private final TaskDTOMapper taskDTOMapper;
    private final TicketServiceImpl ticketService;

    public TaskServiceImpl(TaskRepository taskRepository, TaskDTOMapper taskDTOMapper , TicketServiceImpl ticketService) {
        this.taskRepository = taskRepository;
        this.taskDTOMapper = taskDTOMapper;
        this.ticketService = ticketService;
    }


    @Override
    public TaskDTO createTask(Long ticketId,TaskDTO taskDTO) {
        Task task = taskDTOMapper.toEntity(taskDTO);
        Ticket ticket = ticketService.getTicketByIdHelper(ticketId);
        task.setTicket(ticket);
        Task savedTask = taskRepository.save(task);
        return taskDTOMapper.toDto(savedTask);
    }


    @Override
    public TaskDTO updateTask(Long id, TaskDTO taskDTO) {
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Task not found on id: " + id));

        task.setTitle(taskDTO.getTitle());
        task.setDone(taskDTO.isDone());
        Task updatedTask = taskRepository.save(task);
        log.info("Task with id: {} has been updated successfully", id);
        return taskDTOMapper.toDto(updatedTask);
    }

    @Override
    public void deleteTask(Long id) {
        Task taskToDelete = getTaskByIdHelper(id);
        Ticket ticket = taskToDelete.getTicket();
        if (ticket != null){
            ticket.getTasks().remove(taskToDelete);
        }
        taskRepository.delete(taskToDelete);
        log.info("Task with id: {} has been deleted successfully", id);
    }


    @Override
    public TaskDTO getTaskById(Long id) {
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Task not found on :: " + id));
        log.info("Fetching task with id: {}...", id);
        return taskDTOMapper.toDto(task);
    }
    public Task getTaskByIdHelper(Long id) {
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Task not found on :: " + id));
        log.info("Fetching task with id: {}...", id);
        return task;
    }

    @Override
    public List<TaskDTO> getAllTasks() {
        log.info("Fetching all tasks...");
        return taskDTOMapper.toDtos(taskRepository.findAll());
    }

    @Override
    public List<TaskDTO> getTasksByTicketId(Long taskId) {
        List<Task> taskList = taskRepository.findByticketTicketId(taskId);
        return taskDTOMapper.toDtos(taskList);
    }


}
