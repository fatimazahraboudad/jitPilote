package academy.jobintech.jitechpilot.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "Workspace")
@Table(name = "workspace")
public class Workspace {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long workspaceId;
    private String name;
    private String description;

    @OneToMany(
            mappedBy = "workspace",
            orphanRemoval = true,
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY
    )
    private List<Board> boards = new ArrayList<>();

    @OneToMany(
            mappedBy = "workspace",
            orphanRemoval = true,
            cascade = CascadeType.ALL
    )
    List<UserWorkspaceRole> userWorkspaceRole = new ArrayList<>();

}