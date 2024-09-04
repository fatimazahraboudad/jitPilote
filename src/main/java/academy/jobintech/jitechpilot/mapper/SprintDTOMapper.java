package academy.jobintech.jitechpilot.mapper;
import academy.jobintech.jitechpilot.dto.SprintDTO;
import academy.jobintech.jitechpilot.entity.Sprint;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class SprintDTOMapper implements EntityDTOMapper<Sprint, SprintDTO>{
    private ModelMapper modelMapper = new ModelMapper();
    @Override
    public SprintDTO toDto(Sprint entity) {
        return modelMapper.map(entity, SprintDTO.class);
    }

    @Override
    public Sprint toEntity(SprintDTO dto) {
        return modelMapper.map(dto , Sprint.class);
    }
}
