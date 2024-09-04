package academy.jobintech.jitechpilot.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity(name = "Sprint")
@Table(name = "sprint")
public class Sprint {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long sprintId;
    private int sprintNumber;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private int targetVelocity;
    @Transient
    private int achievedVelocity;

    @ManyToOne
    @JoinColumn(
            name="board_id_sprint",
            referencedColumnName = "boardId",
            foreignKey = @ForeignKey(
                    name = "sprint_board_FK"
            )
    )
    private Board board;
    @OneToMany(
            mappedBy = "sprint",
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            fetch = FetchType.LAZY
    )
    private List<Ticket> tickets = new ArrayList<>();

    @PostLoad
    @PostUpdate
    public void calculateVelocity() {
        if (!tickets.isEmpty()) {
            achievedVelocity = tickets.stream()
                    .mapToInt(Ticket::getComplexityPoints)
                    .sum();
        } else {
            achievedVelocity = 0;
        }
    }
}
