
 package academy.jobintech.jitechpilot.dto;
 import academy.jobintech.jitechpilot.entity.Ticket;
 import jakarta.validation.constraints.NotEmpty;
 import jakarta.validation.constraints.Size;
 import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class SectionDTO {

   private Long sectionId;

   @NotEmpty(message = "section title should not be empty")
   @Size(min = 2, max = 20, message = "title should have be min 2 and max 20")
   private String sectionTitle;


   private List<TicketDTO> tickets = new ArrayList<>();
 }
