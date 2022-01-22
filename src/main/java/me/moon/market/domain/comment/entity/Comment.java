package me.moon.market.domain.comment.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import me.moon.market.domain.post.entity.Post;
import me.moon.market.domain.user.entity.User;
import me.moon.market.global.entity.BaseTimeEntity;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class Comment extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "COMMENT_ID")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_ID")
    private User author;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "POST_ID")
    private Post post;

    @Column(name = "BODY")
    private String body;

    @ManyToOne
    @Column(name = "PARENTS_ID")
    private Comment parents;

    @Column(name = "IS_REMOVED")
    private boolean removed = false;

    @Builder
    public Comment(User user, Post post, String body, Comment parents){
        this.author=user;
        this.post=post;
        this.body=body;
        this.parents=parents;
    }
}
