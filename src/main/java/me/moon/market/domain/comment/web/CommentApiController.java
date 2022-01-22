package me.moon.market.domain.comment.web;

import lombok.RequiredArgsConstructor;
import me.moon.market.domain.comment.dto.CommentSaveRequest;
import me.moon.market.domain.comment.dto.CommentUpdateRequest;
import me.moon.market.domain.comment.service.CommentService;
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

    @LoginRequired
    @PostMapping("/posts/{postId}/comments/{commentId}")
    public ResponseEntity<HttpStatus> reply(@PathVariable(name = "postId") Long postId,
                                             @PathVariable(name = "commentId") Long commentId,
                                             @LoginUser SessionUser user,
                                             @Valid CommentSaveRequest dto){

        commentService.reply(commentId, user, dto);

        return ResponseEntity.ok().build();
    }

    @LoginRequired
    @PutMapping("/posts/{postId}/comments/{commentId}")
    public ResponseEntity<HttpStatus> update(@PathVariable(name = "postId") Long postId,
                                             @PathVariable(name = "commentId") Long commentId,
                                             @LoginUser SessionUser user,
                                             @Valid CommentUpdateRequest dto){

        commentService.update(commentId, user, dto);

        return ResponseEntity.ok().build();
    }

    @LoginRequired
    @DeleteMapping("/posts/{postId}/comments/{commentId}")
    public ResponseEntity<HttpStatus> delete(@PathVariable(name = "postId") Long postId,
                                             @PathVariable(name = "commentId") Long commentId,
                                             @LoginUser SessionUser user){

        commentService.delete(commentId, user);

        return ResponseEntity.ok().build();
    }
}
