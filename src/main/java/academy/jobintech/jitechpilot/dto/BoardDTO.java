package academy.jobintech.jitechpilot.dto;

import academy.jobintech.jitechpilot.entity.Section;
import academy.jobintech.jitechpilot.enums.AccessLevel;
import academy.jobintech.jitechpilot.enums.TemplateType;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class BoardDTO {
    private Long boardId;

    @NotEmpty(message = "board name should not be empty")
    @Size(min = 2, max = 20, message = "board name should have be min 2 and max 20")
    private String boardName;

    @NotEmpty(message = "description should not be empty")
    @Size(min = 2, max = 50, message = "description should have be min 2 and max 50")
    private String description;

    private LocalDateTime start_date;
    private boolean fav;
    private AccessLevel accessLevel;
    private List<SectionDTO> sections = new ArrayList<>();
    private List<SprintDTO> sprints = new ArrayList<>();
    private TemplateType templateType;



}
