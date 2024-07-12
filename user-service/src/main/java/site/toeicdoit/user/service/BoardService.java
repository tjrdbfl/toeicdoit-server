package site.toeicdoit.user.service;

import site.toeicdoit.user.domain.dto.BoardDto;
import site.toeicdoit.user.domain.model.mysql.BoardModel;
import site.toeicdoit.user.domain.model.mysql.UserModel;

import java.util.List;


public interface BoardService extends CommandService<BoardDto>, QueryService<BoardDto> {

    default BoardModel dtoToEntity(BoardDto dto) {
        return BoardModel.builder()
                .title(dto.getTitle())
                .content(dto.getContent())
                .userId(UserModel.builder().id(dto.getUserId()).build())
                .type(dto.getType())
                .category(dto.getCategory())
                .build();
    }

    default BoardDto entityToDto(BoardModel entity) {
        return BoardDto.builder()
                .id(entity.getId())
                .title(entity.getTitle())
                .content(entity.getContent())
                .userId(entity.getUserId().getId())
                .type(entity.getType())
                .category(entity.getCategory())
                .replyIds(entity.getReplyIds())
                .createdAt(entity.getCreatedAt().toString())
                .updatedAt(entity.getUpdatedAt().toString())
                .build();
    }

    List<BoardModel> findByTypes(String type);
}
