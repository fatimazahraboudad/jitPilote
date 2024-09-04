package academy.jobintech.jitechpilot.controller;

import academy.jobintech.jitechpilot.dto.TicketDTO;
import academy.jobintech.jitechpilot.service.TicketService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("api/v1/tickets")
@CrossOrigin(
        origins = {"*"},
        allowedHeaders = "*",
        methods = {RequestMethod.GET,RequestMethod.DELETE,RequestMethod.POST,RequestMethod.PUT}
)
public class TicketController {

    private final TicketService ticketService;

    public TicketController(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    @PostMapping("/{sectionId}/{sprintId}")
    public ResponseEntity<TicketDTO> createTicket(
            @PathVariable Long sectionId ,
            @PathVariable Long sprintId ,
            @Valid @RequestBody TicketDTO ticketDTO) {
        TicketDTO createdTicket = ticketService.createTicket(sprintId , sectionId,ticketDTO);
        return new ResponseEntity<>(createdTicket, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TicketDTO> getTicketById(@PathVariable Long id) {
        TicketDTO ticketDTO = ticketService.getTicketById(id);
        return ResponseEntity.ok(ticketDTO);
    }

    @GetMapping
    public ResponseEntity<List<TicketDTO>> getAllTickets() {
        List<TicketDTO> tickets = ticketService.getAllTickets();
        return ResponseEntity.ok(tickets);
    }

    @GetMapping("/section/{sectionId}")
        public ResponseEntity<List<TicketDTO>> getTicketsBySection(@PathVariable Long sectionId) {
        List<TicketDTO> tickets = ticketService.getTicketsBySection(sectionId);
        return ResponseEntity.ok(tickets);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateTicket(@PathVariable Long id,@Valid @RequestBody TicketDTO ticketDTO) {
        TicketDTO updatedTicket = ticketService.updateTicket(id, ticketDTO);
        return ResponseEntity.ok("Ticket updated successfully");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTicket(@PathVariable Long id) {
        ticketService.deleteTicket(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{ticketId}/assignuser/{userId}")
    public ResponseEntity<TicketDTO> assignTicketToUser(@PathVariable Long ticketId , @PathVariable Long userId) {
        return ResponseEntity.ok(ticketService.assignTicketToUser(ticketId,userId));
    }

    @PutMapping("/{ticketId}/unassignuser/{userId}")
    public ResponseEntity<TicketDTO> removeUserFromTicket(@PathVariable Long ticketId , @PathVariable Long userId) {
        return ResponseEntity.ok(ticketService.removeUserFromTicket(ticketId,userId));
    }

    @PutMapping("/{ticketId}/section/{sectionId}")
    public ResponseEntity<TicketDTO> updateTicketSection(@PathVariable Long ticketId , @PathVariable Long sectionId) {
        TicketDTO ticketDTO =ticketService.updateSectionInTicket(ticketId,sectionId);
        return new  ResponseEntity<>(ticketDTO,HttpStatus.OK);
    }
    @DeleteMapping("deleteAll/{id}")
    public ResponseEntity<Void> deleteAllTicket(@PathVariable Long id) {
        ticketService.deleteAllTicketBySection(id);
        return ResponseEntity.noContent().build();
    }
}
    