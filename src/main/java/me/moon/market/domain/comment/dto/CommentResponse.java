package me.moon.market.domain.comment.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import me.moon.market.domain.comment.entity.Comment;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CommentResponse {

    private Long id;
    private String nickname;
    private String body;
    private Long parentId;
    private LocalDateTime modifiedDate;

    public CommentResponse(Comment comment){
        this.id=comment.getId();
        this.nickname=comment.getAuthor().getNickname();
        this.body=comment.getBody();
        this.parentId=comment.getParents().getId();
        this.modifiedDate=comment.getModifiedDate();
    }
}
