package me.moon.market.domain.post.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import me.moon.market.domain.model.Address;
import me.moon.market.domain.post.entity.Post;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PostListResponse {
    private Long id;
    private String nickname;
    private Address address;
    private String title;
    private String content;
    private String thumbnail;

    public PostListResponse(Post post){
        this.id=post.getId();
        this.nickname=post.getAuthor().getNickname();
        this.address=post.getAuthor().getAddress();
        this.title=post.getTitle();
        this.content=post.getContent();
        this.thumbnail=post.getThumbnail();
    }
}
