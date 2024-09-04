package academy.jobintech.jitechpilot.serviceImpl;

import academy.jobintech.jitechpilot.dto.TicketDTO;
import academy.jobintech.jitechpilot.entity.Section;
import academy.jobintech.jitechpilot.entity.Sprint;
import academy.jobintech.jitechpilot.entity.Ticket;
import academy.jobintech.jitechpilot.entity.User;
import academy.jobintech.jitechpilot.exception.NotFoundException;
import academy.jobintech.jitechpilot.mapper.TicketDTOMapper;
import academy.jobintech.jitechpilot.repository.SectionRepository;
import academy.jobintech.jitechpilot.repository.TicketRepository;
import academy.jobintech.jitechpilot.repository.UserRepository;
import academy.jobintech.jitechpilot.service.TicketService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Yassine CHALH
 */
@Slf4j
@Service
public class TicketServiceImpl implements TicketService {// TODO: Logs and Exceptions

    private final TicketRepository ticketRepository;
    private final TicketDTOMapper ticketDTOMapper;
    private final SectionServiceImpl sectionService;
    private final SprintServiceImpl sprintService;
    @Autowired
    private UserServiceImpl userService;
    @Autowired
    private SectionRepository sectionRepository;


    public TicketServiceImpl(TicketRepository ticketRepository, TicketDTOMapper ticketDTOMapper , SectionServiceImpl sectionService , SprintServiceImpl sprintService) {
        this.ticketRepository = ticketRepository;
        this.ticketDTOMapper = ticketDTOMapper;
        this.sectionService = sectionService;
        this.sprintService = sprintService;
    }

    @Override
    public TicketDTO createTicket(Long sprintId , Long sectionId ,TicketDTO ticketDTO) {
        Ticket ticket = ticketDTOMapper.toEntity(ticketDTO);
        Section section = sectionService.getSectionByIdHelper(sectionId);
        Sprint sprint = sprintService.getSprintByIdHelper(sprintId);
        ticket.setSection(section);
        ticket.setSprint(sprint);
        Ticket savedTicket = ticketRepository.save(ticket);
        log.info("Ticket created successfully title : {} ", savedTicket.getTitle());
        return ticketDTOMapper.toDto(savedTicket);
    }

    @Override
    public TicketDTO updateTicket(Long id, TicketDTO ticketDto) {
        Ticket ticket = ticketRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Ticket not found on :: " + id));

        ticket.setTitle(ticketDto.getTitle());
        ticket.setDescription(ticketDto.getDescription());
        ticket.setPriority(ticketDto.getPriority());
        ticket.setDescriptionContent(ticketDto.getDescriptionContent());
        ticket.setEndDate(ticketDto.getEndDate());
        Ticket updatedTicket = ticketRepository.save(ticket);
        log.info("Ticket updated successfully title : {} ", updatedTicket.getTitle());

        return ticketDTOMapper.toDto(updatedTicket);
    }

    @Override
    public void deleteTicket(Long id) {
        ticketRepository.deleteById(id);
    }

    @Override
    public TicketDTO getTicketById(Long id) {
        Ticket ticket = ticketRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Ticket not found on :: " + id));
        log.info("Ticket found successfully id : {} ", id);
        return ticketDTOMapper.toDto(ticket);
    }

    @Override
    public List<TicketDTO> getAllTickets() {
        log.info("fetching all tickets");
        return ticketDTOMapper.toDtos(ticketRepository.findAll());
    }

    public Ticket getTicketByIdHelper(Long ticketId) {
        Ticket ticket = ticketRepository.findById(ticketId)
                .orElseThrow(() -> new RuntimeException("Ticket not found on :: " + ticketId));
        return ticket;
    }

    @Override
    public List<TicketDTO> getTicketsBySection(Long sectionId) {
        List<Ticket> ticketList = ticketRepository.findBysectionSectionId(sectionId);
        return ticketDTOMapper.toDtos(ticketList);
    }

    @Override
    public TicketDTO assignTicketToUser(Long ticketId,Long userId) {
        User user = userService.getUsertByIdHelper(userId);
        Ticket ticket = getTicketByIdHelper(ticketId);
        ticket.getUsers().add(user);
        user.getTickets().add(ticket);
        Ticket ticket1= ticketRepository.save(ticket);
        return ticketDTOMapper.toDto(ticket1);
    }

    @Override
    public TicketDTO removeUserFromTicket(Long ticketId,Long userId) {
        User user = userService.getUsertByIdHelper(userId);
        Ticket ticket = getTicketByIdHelper(ticketId);
        ticket.getUsers().remove(user);
        user.getTickets().remove(ticket);
        Ticket ticket1= ticketRepository.save(ticket);
        return ticketDTOMapper.toDto(ticket1);
    }

    @Override
    public TicketDTO updateSectionInTicket(Long ticketId, Long sectionId) {
        Ticket ticket = getTicketByIdHelper(ticketId);
        Section section=sectionService.getSectionByIdHelper(sectionId);
        ticket.setSection(section);
        section.getTickets().add(ticket);
        ticketRepository.save(ticket);
        sectionRepository.save(section);
        return ticketDTOMapper.toDto(ticket);

    }

    @Override
    public void deleteAllTicketBySection(Long sectionId) {
        ticketRepository.deleteAll(ticketDTOMapper.toEntities(getTicketsBySection(sectionId)));
    }


}
