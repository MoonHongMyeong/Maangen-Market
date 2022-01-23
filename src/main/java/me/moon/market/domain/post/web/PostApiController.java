package me.moon.market.domain.post.web;

import lombok.RequiredArgsConstructor;
import me.moon.market.domain.post.dto.PostListResponse;
import me.moon.market.domain.post.dto.PostSaveRequest;
import me.moon.market.domain.post.dto.PostUpdateRequest;
import me.moon.market.domain.post.service.PostService;
import me.moon.market.global.annotation.LoginRequired;
import me.moon.market.global.annotation.LoginUser;
import me.moon.market.global.dto.SessionUser;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1")
public class PostApiController {

    private final PostService postService;

    @LoginRequired
    @GetMapping("/posts")
    public ResponseEntity<PageImpl<PostListResponse>> getPosts(@LoginUser SessionUser user, Pageable pageable){

        PageImpl<PostListResponse> response = postService.getPosts(user, pageable);

        return ResponseEntity.ok(response);
    }

    @LoginRequired
    @PostMapping("/posts")
    public ResponseEntity<HttpStatus> create(@RequestPart @Valid PostSaveRequest dto,
                                             @RequestPart(required = false) List<MultipartFile> photos,
                                             @LoginUser SessionUser user){

        postService.create(dto, photos, user);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @LoginRequired
    @PutMapping("/posts/{postId}")
    public ResponseEntity<HttpStatus> update(@Valid PostUpdateRequest dto,
                                             @PathVariable(name = "postId") Long postId,
                                             @LoginUser SessionUser user){

        postService.update(dto, postId, user);

        return ResponseEntity.ok().build();
    }

    @LoginRequired
    @PutMapping("/posts/{postId}/status")
    public ResponseEntity<HttpStatus> updateTradeStatus(@RequestParam(name = "status") String status,
                                                        @PathVariable(name = "postId") Long postId,
                                                        @LoginUser SessionUser user){

        postService.updateTradeStatus(status, postId, user);

        return ResponseEntity.ok().build();
    }

    @LoginRequired
    @DeleteMapping("/posts/{postId}")
    public ResponseEntity<HttpStatus> delete(@PathVariable(name = "postId") Long postId,
                                             @LoginUser SessionUser user){

        postService.delete(postId, user);

        return ResponseEntity.ok().build();
    }

}
