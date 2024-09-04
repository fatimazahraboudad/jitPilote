package academy.jobintech.jitechpilot.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.*;

import java.io.Serializable;

@ToString
@EqualsAndHashCode
@Embeddable
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class RoleBoardId implements Serializable {

    @Column(name="user_id")
    Long userId;
    @Column(name="board_id")
    Long boardId;
}
