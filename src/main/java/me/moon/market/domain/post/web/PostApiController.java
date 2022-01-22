package me.moon.market.domain.post.web;

import lombok.RequiredArgsConstructor;
import me.moon.market.domain.post.dto.PostSaveRequest;
import me.moon.market.domain.post.service.PostService;
import me.moon.market.global.annotation.LoginRequired;
import me.moon.market.global.annotation.LoginUser;
import me.moon.market.global.dto.SessionUser;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1")
public class PostApiController {

    private final PostService postService;

    @LoginRequired
    @PostMapping("/posts")
    public ResponseEntity<HttpStatus> create(@Valid PostSaveRequest dto, @LoginUser SessionUser user){

        postService.create(dto, user);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

}
