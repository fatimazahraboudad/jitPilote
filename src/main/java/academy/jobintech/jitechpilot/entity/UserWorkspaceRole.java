package academy.jobintech.jitechpilot.entity;


import academy.jobintech.jitechpilot.enums.UserRole;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
public class UserWorkspaceRole {

    @EmbeddedId
    private RoleWorkspaceId roleWorkspaceId;


    @ManyToOne
    @MapsId("userId")
    @JoinColumn(
            name="user_id_workspace_role",
            referencedColumnName = "userId",
            foreignKey = @ForeignKey(
                    name = "user_id_workspace_role_fk"
            )
    )
    private User user;

    @ManyToOne
    @MapsId("workspaceId")
    @JoinColumn(
            name="workspace_id_workspace_role",
            referencedColumnName = "workspaceId",
            foreignKey = @ForeignKey(
                    name = "workspace_id_workspace_role_fk"
            )
    )
    private Workspace workspace;


    @Enumerated(EnumType.STRING)
    private UserRole user_role;
}
