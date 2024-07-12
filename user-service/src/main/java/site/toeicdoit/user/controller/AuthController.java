package site.toeicdoit.user.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import lombok.RequiredArgsConstructor;
import site.toeicdoit.user.domain.dto.UserDto;
import site.toeicdoit.user.domain.vo.Messenger;
import site.toeicdoit.user.service.UserService;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@Slf4j
@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final UserService service;

    // local 회원가입
    @PostMapping("/join")
    public ResponseEntity<Messenger> localJoin(@RequestBody UserDto dto) {
        log.info(">>> localJoin con 진입: {}", dto);
        return ResponseEntity.ok(service.save(dto));
    }

    // oauth 첫 로그인
    @PostMapping("/oauth2/{registration}")
    public ResponseEntity<Messenger> oauthJoin(@RequestBody UserDto dto) {
        log.info(">>> oauthJoin con 진입: {}", dto);
        return ResponseEntity.ok(service.oauthJoin(dto));
    }

    // local에서 local, oauth 로그인
    @PostMapping("/local/login")
    public ResponseEntity<Messenger> login(@RequestBody UserDto dto) {
        log.info(">>> login con 진입: {} ", dto);
        return ResponseEntity.ok(service.login(dto));
    }

    // 로그인 시 Email 존재 여부 확인
    @GetMapping("/exists-email")
    public ResponseEntity<Messenger> existsByEmail(@RequestParam("email") String email) {
        log.info(">>> existsByEmail con: {}", email);
        return ResponseEntity.ok(service.existsByEmail(email));
    }

}
