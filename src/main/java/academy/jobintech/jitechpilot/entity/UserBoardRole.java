package academy.jobintech.jitechpilot.entity;


import academy.jobintech.jitechpilot.enums.UserRole;
import jakarta.persistence.*;
import lombok.*;


@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
public class UserBoardRole {
    @EmbeddedId
    private RoleBoardId roleId;

    @ManyToOne
    @MapsId("userId")
    @JoinColumn(
            name="user_id",
            foreignKey = @ForeignKey(
                    name = "role-board_user_id_fk"
            )
    )
    private User user;

    @ManyToOne
    @MapsId("boardId")
    @JoinColumn(
            name="board_id",
            foreignKey = @ForeignKey(
                    name = "role_board_id_fk"
            )
    )
    private Board board;

    @Enumerated(EnumType.STRING)
    private UserRole user_role;

}
