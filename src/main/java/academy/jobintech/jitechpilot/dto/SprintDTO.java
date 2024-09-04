package academy.jobintech.jitechpilot.dto;

import academy.jobintech.jitechpilot.entity.Ticket;
import jakarta.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class SprintDTO {
    private Long sprintId;
    private int sprintNumber;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private int targetVelocity;
    private int achievedVelocity;
}
