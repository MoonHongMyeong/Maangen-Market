package me.moon.market.domain.comment.web;

import lombok.RequiredArgsConstructor;
import me.moon.market.domain.comment.dto.CommentSaveRequest;
import me.moon.market.domain.comment.service.CommentService;
import me.moon.market.global.annotation.LoginRequired;
import me.moon.market.global.annotation.LoginUser;
import me.moon.market.global.dto.SessionUser;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1")
public class CommentApiController {

    private final CommentService commentService;

    @LoginRequired
    @PostMapping("/posts/{postId}/comments")
    public ResponseEntity<HttpStatus> create(@PathVariable(name = "postId") Long postId,
                                             @LoginUser SessionUser user,
                                             @Valid CommentSaveRequest dto){

        commentService.create(postId, user, dto);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
