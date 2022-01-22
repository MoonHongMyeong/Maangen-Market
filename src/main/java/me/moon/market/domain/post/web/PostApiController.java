package me.moon.market.domain.post.web;

import lombok.RequiredArgsConstructor;
import me.moon.market.domain.post.dto.PostSaveRequest;
import me.moon.market.domain.post.dto.PostUpdateRequest;
import me.moon.market.domain.post.service.PostService;
import me.moon.market.global.annotation.LoginRequired;
import me.moon.market.global.annotation.LoginUser;
import me.moon.market.global.dto.SessionUser;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @LoginRequired
    @PutMapping("/posts/{postId}")
    public ResponseEntity<HttpStatus> update(@Valid PostUpdateRequest dto, @PathVariable(name = "postId") Long postId, @LoginUser SessionUser user){

        postService.update(dto, postId, user);

        return ResponseEntity.ok().build();
    }

    @LoginRequired
    @DeleteMapping("/posts/{postId}")
    public ResponseEntity<HttpStatus> delete(@PathVariable(name = "postId") Long postId, @LoginUser SessionUser user){

        postService.delete(postId, user);

        return ResponseEntity.ok().build();
    }

}
