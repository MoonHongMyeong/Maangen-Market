package me.moon.market.domain.post.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import me.moon.market.domain.post.dto.PostUpdateRequest;
import me.moon.market.domain.user.entity.User;
import me.moon.market.global.entity.BaseTimeEntity;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class Post extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "POST_ID")
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CATEGORY_ID")
    private Category category;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_ID")
    private User author;

    @Column(name = "TITLE")
    private String title;

    @Column(name = "PRICE")
    private int price;

    @Column(name = "CONTENT")
    private String content;

    @Column(name = "THUMBNAIL")
    private String thumbnail;

    @Column(name = "TRADE_STATUS")
    private TradeStatus tradeStatus;

    @Column(name = "IS_REMOVED")
    private boolean removed = false;

    @Builder
    public Post(Category category, User author, String title, int price, String content, TradeStatus tradeStatus){
        this.category=category;
        this.author=author;
        this.title=title;
        this.price=price;
        this.content=content;
        this.tradeStatus=tradeStatus;
    }

    public void setCategory(Category category){
        this.category=category;
    }

    public void setThumbnail(String thumbnailUrl){
        if(thumbnailUrl == null || thumbnailUrl.isEmpty()){
            this.thumbnail="defaultImage.png";
        }
        this.thumbnail=thumbnailUrl;
    }

    public void update(PostUpdateRequest dto) {
        this.title=dto.getTitle();
        this.content=dto.getContent();
    }

    public void changeStatus(TradeStatus status){
        this.tradeStatus=status;
    }

    public void delete() {
        this.removed = true;
    }
}
