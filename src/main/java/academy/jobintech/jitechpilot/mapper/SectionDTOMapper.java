package academy.jobintech.jitechpilot.mapper;

import academy.jobintech.jitechpilot.dto.SectionDTO;
import academy.jobintech.jitechpilot.entity.Section;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class SectionDTOMapper implements EntityDTOMapper<Section, SectionDTO> {
    private final ModelMapper modelMapper;

    public SectionDTOMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }
    @Override
    public SectionDTO toDto(Section entity) {
        SectionDTO sectionDTO=modelMapper.map(entity, SectionDTO.class);
        return sectionDTO;
    }
    @Override
    public Section toEntity(SectionDTO dto) {
        Section section=modelMapper.map(dto, Section.class);
        return section;
    }
}
