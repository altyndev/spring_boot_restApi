package noygut.spring_boot_restapi.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/test")
@PreAuthorize("hasAnyAuthority('SUPER_ADMIN', 'ADMIN')")
public class TestController {

    @GetMapping("/hello")
    public String helloAdmin() {

        return "I am Ulan, i am admin";
    }
}
