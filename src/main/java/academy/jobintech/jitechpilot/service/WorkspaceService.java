package academy.jobintech.jitechpilot.service;

import academy.jobintech.jitechpilot.dto.WorkspaceDTO;
import academy.jobintech.jitechpilot.entity.User;
import jakarta.transaction.Transactional;

import java.util.List;


public interface WorkspaceService {
    public String getWorkspaceNameById(Long workspaceId);
    List<WorkspaceDTO> getAllWorkspaces();

    WorkspaceDTO getWorkspaceById(Long id);

    WorkspaceDTO createWorkspace(WorkspaceDTO workspaceDto);

    WorkspaceDTO updateWorkspace(Long id, WorkspaceDTO workspaceDto);

    void deleteWorkspace(Long id);


//    @Transactional
//    boolean removeUserFromWorkspace(Long userId, Long workspaceId);
}
