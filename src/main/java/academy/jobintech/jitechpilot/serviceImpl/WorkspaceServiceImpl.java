package academy.jobintech.jitechpilot.serviceImpl;


import academy.jobintech.jitechpilot.dto.UserWorkspaceRoleDto;
import academy.jobintech.jitechpilot.dto.WorkspaceDTO;
import academy.jobintech.jitechpilot.entity.User;
import academy.jobintech.jitechpilot.entity.UserWorkspaceRole;
import academy.jobintech.jitechpilot.entity.Workspace;
import academy.jobintech.jitechpilot.enums.UserRole;
import academy.jobintech.jitechpilot.exception.NotFoundException;
import academy.jobintech.jitechpilot.mapper.WorkspaceDTOMapper;
import academy.jobintech.jitechpilot.repository.UserRepository;
import academy.jobintech.jitechpilot.repository.UserWorkspaceRoleRepository;
import academy.jobintech.jitechpilot.repository.WorkspaceRepository;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WorkspaceServiceImpl implements academy.jobintech.jitechpilot.service.WorkspaceService {

    private static final Logger log = LoggerFactory.getLogger(WorkspaceServiceImpl.class);


    private final WorkspaceRepository workspaceRepository;
    private final WorkspaceDTOMapper workspaceDTOMapper;
    private final UserRepository userRepository;
    private final UserWorkspaceRoleRepository userWorkspaceRoleRepository;

    @Autowired
    public WorkspaceServiceImpl(WorkspaceRepository workspaceRepository, WorkspaceDTOMapper workspaceDTOMapper, UserRepository userRepository, UserWorkspaceRoleRepository userWorkspaceRoleRepository) {
        this.workspaceRepository = workspaceRepository;
        this.workspaceDTOMapper = workspaceDTOMapper;
        this.userRepository = userRepository;
        this.userWorkspaceRoleRepository = userWorkspaceRoleRepository;
    }

    @Override
    public String getWorkspaceNameById(Long workspaceId) {
        Workspace workspace = workspaceRepository.findById(workspaceId).orElse(null);
        return workspace != null ? workspace.getName() : null;
    }

    @Override
    public List<WorkspaceDTO> getAllWorkspaces() {
        log.info("Fetching all workspaces");
        List<WorkspaceDTO> workspaceDTOs = workspaceDTOMapper.toDtos(workspaceRepository.findAll());
        log.info("Fetched {} workspaces", workspaceDTOs.size());
        return workspaceDTOs;
    }

    @Override
    public WorkspaceDTO getWorkspaceById(Long id) {
        log.info("Fetching workspace by id: {}", id);
        Workspace workspace = workspaceRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Workspace not found with id : " + id));
        log.info("Found workspace: {}", workspace.getName());
        return workspaceDTOMapper.toDto(workspace);
    }

    @Override
    public WorkspaceDTO createWorkspace(WorkspaceDTO workspaceDto) {
        log.info("Creating new workspace with name: {}", workspaceDto.getName());
        Workspace workspace = workspaceDTOMapper.toEntity(workspaceDto);
        workspace = workspaceRepository.save(workspace);
        log.info("Created workspace with id: {}", workspace.getWorkspaceId());
        return workspaceDTOMapper.toDto(workspace);
    }

    @Override
    public WorkspaceDTO updateWorkspace(Long id, WorkspaceDTO workspaceDto) {
        log.info("Updating workspace with id: {}", id);
        Workspace workspace = workspaceRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Workspace not found for this id :: " + id));
        workspace.setName(workspaceDto.getName());
        workspace.setDescription(workspaceDto.getDescription());
        workspace = workspaceRepository.save(workspace);
        log.info("Updated workspace with id: {}", id);
        return workspaceDTOMapper.toDto(workspace);
    }

    @Override
    public void deleteWorkspace(Long id) {
        log.info("Deleting workspace with id: {}", id);
        workspaceRepository.deleteById(id);
        log.info("Deleted workspace with id: {}", id);
    }

//
//
//    @Override
//    public boolean removeUserFromWorkspace(Long userId, Long workspaceId) {
//        try {
//            userRepository.findById(userId).orElseThrow(() -> new Exception("User not found"));
//            userWorkspaceRoleRepository.deleteByUserIdAndWorkspaceId(userId, workspaceId);
//
//            log.info("Removed user {} from workspace {}", userId, workspaceId);
//            return true;
//        } catch (Exception e) {
//            log.error("Error removing user from workspace: {}", e.getMessage());
//            return false;
//        }
//    }


    public Workspace getWorkspaceByIdHelper(Long id) {
        Workspace workspace = workspaceRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Workspace not found with id : " + id));
        return workspace;

    }


}