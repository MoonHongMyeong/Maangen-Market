package me.moon.market.domain.post.dto;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PostUpdateRequest {

    @NotEmpty
    @Length(max = 100, message = "제목은 최대 100글자를 초과할 수 없습니다.")
    private String title;

    @NotEmpty
    private String content;

    @NotEmpty
    private String category;

    @Builder
    public PostUpdateRequest(String title, String content, String category){
        this.title=title;
        this.content=content;
        this.category=category;
    }
}
