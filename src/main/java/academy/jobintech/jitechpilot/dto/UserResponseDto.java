package academy.jobintech.jitechpilot.dto;

import academy.jobintech.jitechpilot.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserResponseDto {
    private long userId;
    private String firstName;
    private String lastName;
    private String userName;
    private String email;
    private LocalDateTime createdAt;
}
