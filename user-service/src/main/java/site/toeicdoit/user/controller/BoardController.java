package site.toeicdoit.user.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import site.toeicdoit.user.domain.dto.BoardDto;
import site.toeicdoit.user.domain.model.mysql.BoardModel;
import site.toeicdoit.user.domain.vo.Messenger;
import site.toeicdoit.user.service.BoardService;

import java.util.List;
import java.util.Optional;


@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping(path = "/api/board")
@Slf4j
@RestController
@RequiredArgsConstructor
public class BoardController {

    private final BoardService service;

    @PostMapping("/save")
    public ResponseEntity<Messenger> save(@RequestBody BoardDto dto) {
        log.info(">>save board con 진입: {}", dto);
        return ResponseEntity.ok(service.save(dto));
    }

    @PutMapping("/modify")
    public ResponseEntity<Messenger> modify(@RequestBody BoardDto dto) {
        log.info(">>> modify board con 진입: {}", dto);
        return ResponseEntity.ok(service.modify(dto));
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Messenger> deleteById(@RequestParam("id") Long id) {
        log.info(">>> delete board con 진입: {}", id);
        return ResponseEntity.ok(service.deleteById(id));
    }

    @GetMapping("/detail")
    public ResponseEntity<Optional<BoardDto>> findById(@RequestParam("id") Long id) {
        log.info(">>> find board con 진입: {}", id);
        return ResponseEntity.ok(service.findById(id));
    }

    @GetMapping("/exists")
    public ResponseEntity<Boolean> existsById(@RequestParam("id") Long id) {
        log.info(">>> exists board con 진입: {}", id);
        return ResponseEntity.ok(service.existsById(id));
    }

    @GetMapping("/find-all")
    public ResponseEntity<List<BoardDto>> findAll() {
        log.info(">>> exists findAll con 진입");
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/find-by-types")
    public ResponseEntity<List<BoardModel>> findByTypes(@RequestParam("type") String type) {
        log.info(">>> findByType 진입 : {}", type);
        return ResponseEntity.ok(service.findByTypes(type));
    }


}
