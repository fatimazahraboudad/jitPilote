package academy.jobintech.jitechpilot.mapper;

import academy.jobintech.jitechpilot.dto.BoardDTO;
import academy.jobintech.jitechpilot.entity.Board;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class BoardDTOMapper implements EntityDTOMapper<Board, BoardDTO> {
    private ModelMapper modelMapper = new ModelMapper();
    @Override
    public Board toEntity(BoardDTO dto) {
        return modelMapper.map(dto , Board.class);
    }

    @Override
    public BoardDTO toDto(Board board) {
        return modelMapper.map(board, BoardDTO.class);
    }
}
