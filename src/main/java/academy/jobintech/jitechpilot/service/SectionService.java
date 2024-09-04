package academy.jobintech.jitechpilot.service;

import academy.jobintech.jitechpilot.dto.SectionDTO;
import academy.jobintech.jitechpilot.dto.UserRequestDto;
import academy.jobintech.jitechpilot.dto.UserResponseDto;
import academy.jobintech.jitechpilot.entity.Section;
import academy.jobintech.jitechpilot.entity.Ticket;

import java.util.List;

public interface SectionService {
    SectionDTO createSection(long boardId, SectionDTO sectionDTO);
    SectionDTO getSectionById(Long sectionId);
    List<SectionDTO> getAllSection();
    List<SectionDTO> getSectionByBoards(Long boardId);
    SectionDTO updateSection(Long sectionId, SectionDTO sectionDTO);
    void deleteSection(Long sectionId);
    void assignSectionToBoard(long sectionId, long boardId);
    Section getSectionByIdHelper(Long sectionId);


}
