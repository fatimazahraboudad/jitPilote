package academy.jobintech.jitechpilot.entity;

import jakarta.persistence.*;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Setter
@Getter
@Entity(name = "Task")
@Table(name = "task")
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long taskId;

    private String title;

    private boolean isDone;

    @ManyToOne
    @JoinColumn(
            name = "ticket_id_task",
            referencedColumnName = "ticketId",
            foreignKey = @ForeignKey(
                    name = "ticket_id_task_FK"
            )
    )
    private Ticket ticket;
}
