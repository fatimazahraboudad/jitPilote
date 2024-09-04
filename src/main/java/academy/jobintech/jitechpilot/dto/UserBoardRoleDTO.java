package academy.jobintech.jitechpilot.dto;

import academy.jobintech.jitechpilot.enums.UserRole;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class UserBoardRoleDTO {
    private Long userId;
    private Long boardId;
    private UserRole userRole;
}
