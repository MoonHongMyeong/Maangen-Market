package me.moon.market.domain.post.dto;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import me.moon.market.domain.post.entity.Post;
import me.moon.market.domain.post.entity.TradeStatus;
import me.moon.market.domain.user.entity.User;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PostSaveRequest {

    @NotEmpty
    @Length(max = 100, message = "제목은 최대 100글자를 초과할 수 없습니다.")
    private String title;

    @NotEmpty
    private String content;

    @NotEmpty
    private String category;

    @Builder
    public PostSaveRequest(String title, String content, String category){
        this.category=category;
        this.title=title;
        this.content=content;
    }

    public Post toEntity(User user) {
        return Post.builder()
                .author(user)
                .title(this.title)
                .content(this.content)
                .tradeStatus(TradeStatus.SALE)
                .build();
    }
}
