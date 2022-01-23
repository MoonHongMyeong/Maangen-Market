package me.moon.market.domain.post.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import me.moon.market.domain.image.entity.Image;
import me.moon.market.domain.post.entity.Post;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PostResponse {

    private Long id;
    private String nickname;
    private String title;
    private int price;
    private String content;
    private String tradeStatus;
    private List<Image> images;
    private List<PostListResponse> anotherPosts;
    private LocalDateTime modifiedDate;


    public PostResponse(Post post){
        this.id=post.getId();
        this.nickname=post.getAuthor().getNickname();
        this.title=post.getTitle();
        this.price=post.getPrice();
        this.content=post.getContent();
        this.tradeStatus=post.getTradeStatus().getTradeStatus();
        this.modifiedDate=post.getModifiedDate();
    }

    public void setImages(List<Image> images){
        this.images=images;
    }

    public void setAnotherPosts(List<PostListResponse> anotherPosts){
        this.anotherPosts=anotherPosts;
    }
}
