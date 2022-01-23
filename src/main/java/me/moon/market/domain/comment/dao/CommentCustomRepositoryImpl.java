package me.moon.market.domain.comment.dao;

import com.querydsl.jpa.impl.JPAQueryFactory;
import me.moon.market.domain.comment.entity.Comment;
import me.moon.market.domain.post.entity.Post;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import java.util.List;

import static me.moon.market.domain.comment.entity.QComment.comment;

public class CommentCustomRepositoryImpl extends QuerydslRepositorySupport implements CommentCustomRepository {

    private final JPAQueryFactory jpaQueryFactory;

    public CommentCustomRepositoryImpl(JPAQueryFactory jpaQueryFactory) {
        super(Comment.class);
        this.jpaQueryFactory=jpaQueryFactory;
    }

    @Override
    public List<Comment> findCommentsByPost(Post post) {
        return jpaQueryFactory.selectFrom(comment)
                .where(
                        comment.post.eq(post),
                        comment.removed.eq(false)
                )
                .fetch();
    }
}
