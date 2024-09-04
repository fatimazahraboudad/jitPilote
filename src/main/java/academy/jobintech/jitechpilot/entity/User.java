package academy.jobintech.jitechpilot.entity;

import academy.jobintech.jitechpilot.enums.UserRole;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Setter
@Getter
@Entity(name = "User")
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false)
    private Long userId;

    private String firstName;

    private String lastName;

    private String userName;

    private String email;

    private String password;

    @Enumerated(EnumType.STRING)
    private UserRole role;

    @OneToMany(mappedBy = "user")
    List<UserBoardRole> userBoardRoles = new ArrayList<>();


    @OneToMany(mappedBy = "user")
    List<UserWorkspaceRole> userWorkspaceRoles = new ArrayList<>();

    @ManyToMany
    @JoinTable(
            name = "user_ticket_association",
            joinColumns = @JoinColumn(name = "userId"),
            inverseJoinColumns = @JoinColumn(name = "ticketId"))
    private List<Ticket> tickets = new ArrayList<>();


    @Column(name = "createdAt", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
    }


}
