package academy.jobintech.jitechpilot.mapper;

import academy.jobintech.jitechpilot.dto.TicketDTO;
import academy.jobintech.jitechpilot.entity.Ticket;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * @author Yassine CHALH
 */
@Component
@Scope(value = "singleton")
public class TicketDTOMapper implements EntityDTOMapper<Ticket, TicketDTO> {


    private final ModelMapper modelMapper;

    public TicketDTOMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public TicketDTO toDto(Ticket ticket) {
        TicketDTO dto =modelMapper.map(ticket, TicketDTO.class);

//                = new TicketDTO();
//
//        dto.setTicketId(ticket.getTicketId());
//        dto.setTitle(ticket.getTitle());
//        dto.setDescription(ticket.getDescription());
//        dto.setPriority(ticket.getPriority());
//        dto.setStatus(ticket.getStatus());
//        dto.setTasks(taskDTOMapper.toDtos(ticket.getTasks()));
//       //dto.setProjectId(ticket.getProject().getProjectId());
//        // TODO: setUser Assigned to ...

        return dto;
    }

    @Override
    public Ticket toEntity(TicketDTO dto) {
        Ticket ticket =modelMapper.map(dto, Ticket.class);
//        new Ticket();
//
//        ticket.setTitle(dto.getTitle());
//        ticket.setDescription(dto.getDescription());
//        ticket.setPriority(dto.getPriority());
//        ticket.setStatus(dto.getStatus());
//        //ticket.setProject(dto.getProjectId());
//        // TODO: setting project and user enities

        return ticket;
    }
}
