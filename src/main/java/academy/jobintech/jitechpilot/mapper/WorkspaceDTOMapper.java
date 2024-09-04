package academy.jobintech.jitechpilot.mapper;

import academy.jobintech.jitechpilot.dto.BoardDTO;
import academy.jobintech.jitechpilot.dto.WorkspaceDTO;
import academy.jobintech.jitechpilot.entity.Board;
import academy.jobintech.jitechpilot.entity.Workspace;
import org.hibernate.jdbc.Work;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;


@Component
@Scope(value = "singleton")
public class WorkspaceDTOMapper implements EntityDTOMapper<Workspace, WorkspaceDTO>{

    private ModelMapper modelMapper = new ModelMapper();
    @Override
    public Workspace toEntity(WorkspaceDTO workspaceDTO) {
        return modelMapper.map(workspaceDTO , Workspace.class);
    }

    @Override
    public WorkspaceDTO toDto(Workspace workspace) {
        return modelMapper.map(workspace, WorkspaceDTO.class);
    }
}
