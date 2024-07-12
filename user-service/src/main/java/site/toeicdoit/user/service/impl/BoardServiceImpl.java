package site.toeicdoit.user.service.impl;

import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import site.toeicdoit.user.domain.dto.BoardDto;
import site.toeicdoit.user.domain.model.mysql.BoardModel;
import site.toeicdoit.user.domain.model.mysql.QBoardModel;
import site.toeicdoit.user.domain.vo.MessageStatus;
import site.toeicdoit.user.domain.vo.Messenger;
import site.toeicdoit.user.repository.mysql.BoardRepository;
import site.toeicdoit.user.service.BoardService;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService {

    private final JPAQueryFactory queryFactory;
    private final BoardRepository boardRepository;
    private final QBoardModel QBoard = QBoardModel.boardModel;

    @Transactional
    @Override
    public Messenger save(BoardDto dto) {
        log.info(">>> Board Service Save 진입: {}", dto);
        BoardModel result = boardRepository.save(dtoToEntity(dto));
        System.out.println((result instanceof BoardModel) ? "SUCCESS" : "FAILURE");
        return Messenger.builder()
                .message((result instanceof BoardModel) ? "SUCCESS" : "FAILURE")
                .build();
    }

    @Override
    public Messenger deleteById(Long id) {
        log.info(">>> Board Service Delete 진입: {}", id);
        if (boardRepository.existsById(id)) {
            boardRepository.deleteById(id);
            return Messenger.builder().message("SUCCESS").build();
        } else {
            return Messenger.builder().message("FAILURE").build();
        }

    }

    @Override
    public List<BoardDto> findAll() {
        return boardRepository.findAll().stream().map(i -> entityToDto(i)).toList();
    }

    @Override
    public Optional<BoardDto> findById(Long id) {
        log.info(">>> Board Service findById 진입: {}", id);
        return boardRepository.findById(id).map(this::entityToDto);
    }


    @Override
    public Boolean existsById(Long id) {
        return boardRepository.existsById(id);
    }


    @Transactional
    @Override
    public Messenger modify(BoardDto dto) {
        log.info(">>> Board Service Modify 진입: {}", dto);
        Long result = queryFactory.update(QBoard)
                .set(QBoard.title, dto.getTitle())
                .set(QBoard.content, dto.getContent())
                .set(QBoard.category, dto.getCategory())
                .where(QBoard.id.eq(dto.getId()))
                .execute();
        log.info(">>> Board modify 결과(Query DSL): {}", result);
        return Messenger.builder()
                .message(MessageStatus.SUCCESS.name())
                .build();
    }


    @Override
    public List<BoardModel> findByTypes(String type) {
        log.info(">>> board findByTypes 진입 : {}", type);
        List<BoardModel> result = queryFactory
                .select(QBoard)
                .from(QBoard)
                .where(QBoard.type.eq(type))
                .fetch();
        result.stream().forEach(i -> System.out.println(i));



//                .stream()
//                .peek(System.out::println)
//                .map(this::entityToDto)
//                .toList();

        log.info("result: {}", result.stream().map(i -> entityToDto(i)).toList());


        return result;
    }
}
