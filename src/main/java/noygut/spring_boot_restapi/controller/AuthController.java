package noygut.spring_boot_restapi.controller;

import lombok.RequiredArgsConstructor;
import noygut.spring_boot_restapi.dto.LoginMapper;
import noygut.spring_boot_restapi.dto.LoginResponse;
import noygut.spring_boot_restapi.dto.RegisterRequest;
import noygut.spring_boot_restapi.dto.RegisterResponse;
import noygut.spring_boot_restapi.entity.User;
import noygut.spring_boot_restapi.exception.ValidationExceptionType;
import noygut.spring_boot_restapi.repository.UserRepository;
import noygut.spring_boot_restapi.security.jwt.JwtTokenUtil;
import noygut.spring_boot_restapi.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/jwt")
public class AuthController {

    private final UserService service;

    private final UserRepository repository;

    private final JwtTokenUtil jwtTokenUtil;

    private final LoginMapper loginMapper;

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> getLogin(@RequestBody RegisterRequest request) {

        try {

            UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword());

            User user = repository.findByEmail(token.getName()).get();

            return ResponseEntity.ok()
                    .body(loginMapper.loginView(jwtTokenUtil.generateToken(user),
                            ValidationExceptionType.SUCCESSFUL, user));
        }catch (BadCredentialsException ex) {

            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(loginMapper.loginView("", ValidationExceptionType.LOGIN_FAILED, null));
        }
    }

    @PostMapping("registration")
    public RegisterResponse create(@RequestBody RegisterRequest request) {

        return service.create(request);
    }
}
