package ru.childbasket.domain.parent;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.childbasket.domain.parent.dto.ParentLoginDto;
import ru.childbasket.domain.parent.dto.ParentRegisterDto;

@RestController
@RequestMapping(value = {"/api/parent/auth"},
        produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class ParentAuthenticationController {
    private final ParentAuthenticationService parentAuthenticationService;

    @PostMapping("/register")
    public ResponseEntity<Void> registerParent(@RequestBody @Valid ParentRegisterDto parentRegisterDto) {
        parentAuthenticationService.registerParent(parentRegisterDto);

        return ResponseEntity.ok().build();
    }

    @PostMapping("/login")
    public ResponseEntity<Void> loginParent(@RequestBody @Valid ParentLoginDto parentLoginDto) {
        final ResponseCookie responseCookie = parentAuthenticationService.loginParent(parentLoginDto);

        return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE, responseCookie.toString()).build();
    }

    @PostMapping("/signout")
    public ResponseEntity<?> logoutUser() {
        ResponseCookie cookie = parentAuthenticationService.logoutUser();
        return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE, cookie.toString())
                .build();
    }
}
