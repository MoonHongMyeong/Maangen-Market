package me.moon.market.domain.post.dao;

import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import me.moon.market.domain.post.entity.Post;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import java.util.List;

import static me.moon.market.domain.post.entity.QPost.post;

public class PostCustomRepositoryImpl extends QuerydslRepositorySupport implements PostCustomRepository {

    private final JPAQueryFactory jpaQueryFactory;

    public PostCustomRepositoryImpl(JPAQueryFactory jpaQueryFactory) {
        super(Post.class);
        this.jpaQueryFactory=jpaQueryFactory;
    }

    @Override
    public PageImpl<Post> getPostsByUserAddress(Double longitude, Double latitude, Pageable pageable) {

        JPAQuery<Post> query = jpaQueryFactory.select(post)
                .from(post)
                .where(
                        post.author.location.longitude.between(longitude - 1/88.64,longitude + 1/88.64),
                        post.author.location.latitude.between(latitude -  1/109.958489129649955, latitude + 1/109.958489129649955),
                        post.removed.eq(false)
                ).orderBy(post.modifiedDate.desc());

        long totalCount = query.fetchCount();
        List<Post> results = getQuerydsl().applyPagination(pageable,query).fetch();

        return new PageImpl<>(results,pageable,totalCount);
    }
}
