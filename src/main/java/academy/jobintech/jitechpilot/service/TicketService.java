package academy.jobintech.jitechpilot.service;

import academy.jobintech.jitechpilot.dto.TicketDTO;
import academy.jobintech.jitechpilot.entity.Ticket;

import java.util.List;

/**
 * @author Yassine CHALH
 */
public interface TicketService {

    TicketDTO createTicket(Long sprintId,Long sectionId , TicketDTO ticketDTO);
    TicketDTO updateTicket(Long id, TicketDTO ticketDTO);
    void deleteTicket(Long id);
    TicketDTO getTicketById(Long id);
    List<TicketDTO> getAllTickets();
    Ticket getTicketByIdHelper(Long ticketId);

    List<TicketDTO> getTicketsBySection(Long sectionId);

    TicketDTO assignTicketToUser(Long ticketId,Long userId);

    TicketDTO removeUserFromTicket(Long ticketId,Long userId);

    TicketDTO updateSectionInTicket(Long ticketId,Long sectionId);
    void deleteAllTicketBySection(Long sectionId);
}
