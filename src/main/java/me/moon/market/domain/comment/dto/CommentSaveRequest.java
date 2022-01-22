package me.moon.market.domain.comment.dto;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import me.moon.market.domain.comment.entity.Comment;
import me.moon.market.domain.post.entity.Post;
import me.moon.market.domain.user.entity.User;

import javax.validation.constraints.NotEmpty;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CommentSaveRequest {

    @NotEmpty
    private String body;

    @Builder
    public CommentSaveRequest(String body){
        this.body=body;
    }

    public Comment toEntity(User user, Post post){
        return Comment.builder()
                .user(user)
                .post(post)
                .body(this.body)
                .build();
    }

    public Comment toEntity(User user, Post post, Comment parents){
        return Comment.builder()
                .user(user)
                .post(post)
                .parents(parents)
                .body(this.body)
                .build();
    }
}
