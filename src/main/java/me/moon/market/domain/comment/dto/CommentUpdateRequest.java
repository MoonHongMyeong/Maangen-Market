package me.moon.market.domain.comment.dto;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CommentUpdateRequest {

    @NotEmpty
    private String body;

    @Builder
    public CommentUpdateRequest(String body){
        this.body=body;
    }
}
