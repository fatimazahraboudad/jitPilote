package academy.jobintech.jitechpilot.serviceImpl;

import academy.jobintech.jitechpilot.dto.SectionDTO;
import academy.jobintech.jitechpilot.entity.Board;
import academy.jobintech.jitechpilot.entity.Section;
import academy.jobintech.jitechpilot.exception.AlreadyExistsException;
import academy.jobintech.jitechpilot.exception.NotFoundException;
import academy.jobintech.jitechpilot.mapper.SectionDTOMapper;
import academy.jobintech.jitechpilot.repository.SectionRepository;
import academy.jobintech.jitechpilot.service.SectionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class SectionServiceImpl implements SectionService {

    @Autowired
    private SectionDTOMapper sectionDTOMapper;

    @Autowired
    private SectionRepository sectionRepository;

    @Autowired
    private BoardServiceImpl boardService;
    @Override
    public SectionDTO createSection(long boardId,SectionDTO sectionDTO) {

        sectionRepository.findByboardBoardId(boardId)
                .stream()
                .filter(existingSection -> existingSection.getSectionTitle().equals(sectionDTO.getSectionTitle()))
                .findAny()
                .ifPresent(existingSectionTitle -> {
                    throw new AlreadyExistsException("Section already exists with this title in this board");
                });
        Board board=boardService.getBoardByIdHelper(boardId);
        Section section=sectionDTOMapper.toEntity(sectionDTO);
        section.setBoard(board);
        Section newSection=sectionRepository.save(section);
        log.info("section created successfully title : {} ",newSection.getSectionTitle());
        return sectionDTOMapper.toDto(newSection);
    }

    @Override
    public SectionDTO getSectionById(Long sectionId) {
        Section section = sectionRepository.findById(sectionId)
                .orElseThrow(() -> new NotFoundException("section not found on :: " + sectionId));
        log.info("Fetching section with id: {}...", sectionId);
        return sectionDTOMapper.toDto(section);
    }

    @Override
    public List<SectionDTO> getAllSection() {
        log.info("Fetching all Sections");
        List<Section> listSection=sectionRepository.findAll();
        return sectionDTOMapper.toDtos(listSection);
    }

    @Override
    public List<SectionDTO> getSectionByBoards(Long boardId) {
        List<Section> sectionList =sectionRepository.findByboardBoardId(boardId);
        log.info("Fetching all Sections in board id {} ", boardId);
        return sectionDTOMapper.toDtos(sectionList);
    }

    @Override
    public SectionDTO updateSection(Long sectionId, SectionDTO sectionDTO) {
        Section section = sectionRepository.findById(sectionId).orElseThrow(()->new NotFoundException("section not found with id : "+sectionId));

        section.setSectionTitle(sectionDTO.getSectionTitle());

        Section updateSection=sectionRepository.save(section);
        log.info("section updated successfully");
        return sectionDTOMapper.toDto(updateSection);
    }

    @Override
    public void deleteSection(Long sectionId) {
        Section section= sectionDTOMapper.toEntity(getSectionById(sectionId));
        sectionRepository.delete(section);
        log.info("section with id : {} deleted successfully",sectionId);
    }

    @Override
    public void assignSectionToBoard(long sectionId, long boardId) {

        Section section = sectionRepository.findById(sectionId)
                .orElseThrow(() -> new NotFoundException("section not found on :: " + sectionId));
        Board board=boardService.getBoardByIdHelper(boardId);
        section.setBoard(board);
        sectionRepository.save(section);
        log.info("section assign to board successfully");
    }

    @Override
    public Section getSectionByIdHelper(Long sectionId) {
        Section section = sectionRepository.findById(sectionId).orElseThrow(()->new NotFoundException("section not found with id : "+sectionId));
        return section;
    }
}
