package academy.jobintech.jitechpilot.dto;


import academy.jobintech.jitechpilot.entity.Board;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
public class WorkspaceDTO {

    private Long workspaceId;

    @NotEmpty(message = "workspace should not be empty")
    @Size(min = 2, max = 20, message = "workspace name should have be min 2 and max 20")
    private String name;

    @NotEmpty(message = "description should not be empty")
    @Size(min = 2, max = 50, message = "description should have be min 2 and max 50")
    private String description;

    private List<BoardDTO> boards = new ArrayList<>();


}
