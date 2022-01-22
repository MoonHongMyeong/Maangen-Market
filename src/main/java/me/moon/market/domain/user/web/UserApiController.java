package me.moon.market.domain.user.web;

import lombok.RequiredArgsConstructor;
import me.moon.market.domain.user.dto.LoginUserRequest;
import me.moon.market.domain.user.dto.UserResponse;
import me.moon.market.domain.user.dto.UserSaveRequest;
import me.moon.market.domain.user.dto.UserUpdateRequest;
import me.moon.market.domain.user.service.LoginService;
import me.moon.market.domain.user.service.UserFindService;
import me.moon.market.domain.user.service.UserManageService;
import me.moon.market.global.annotation.LoginRequired;
import me.moon.market.global.annotation.LoginUser;
import me.moon.market.global.dto.SessionUser;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("api/v1")
@RequiredArgsConstructor
public class UserApiController {

    private final UserManageService userService;
    private final UserFindService findService;
    private final LoginService loginService;

    @PostMapping("/users")
    public ResponseEntity<UserResponse> signUp(@Valid UserSaveRequest dto){
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new UserResponse(userService.doSignUp(dto)));
    }

    @LoginRequired
    @GetMapping("/profile")
    public ResponseEntity<UserResponse> getProfile(@LoginUser SessionUser user){
        return ResponseEntity.status(HttpStatus.OK)
                .body(new UserResponse(findService.findUserBySessionUser(user)));
    }

    @LoginRequired
    @PutMapping("/profile")
    public ResponseEntity<HttpStatus> updateMyProfile(@Valid UserUpdateRequest dto, @LoginUser SessionUser user){

        userService.updateMyProfile(dto);

        return ResponseEntity.ok(HttpStatus.OK);
    }

    @PostMapping("/signIn")
    public ResponseEntity<SessionUser> signIn(@Valid LoginUserRequest dto){

        SessionUser user = userService.findUserByLoginRequest(dto);

        loginService.login(user);

        return ResponseEntity.status(HttpStatus.OK)
                .body(user);
    }

    @LoginRequired
    @GetMapping("/signOut")
    public ResponseEntity<HttpStatus> signOut(){
        loginService.logout();
        return ResponseEntity.ok(HttpStatus.OK);
    }
}
