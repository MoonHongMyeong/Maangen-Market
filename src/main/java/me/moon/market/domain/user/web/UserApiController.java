package me.moon.market.domain.user.web;

import lombok.RequiredArgsConstructor;
import me.moon.market.domain.user.dto.UserResponse;
import me.moon.market.domain.user.dto.UserSaveRequest;
import me.moon.market.domain.user.service.UserSignUpService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("api/v1")
@RequiredArgsConstructor
public class UserApiController {

    private final UserSignUpService signUpService;

    @PostMapping("/users")
    public ResponseEntity<UserResponse> signUp(@Valid UserSaveRequest dto){
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new UserResponse(signUpService.doSignUp(dto)));
    }
}
