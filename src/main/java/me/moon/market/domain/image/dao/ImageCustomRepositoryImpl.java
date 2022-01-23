package me.moon.market.domain.image.dao;

import com.querydsl.jpa.impl.JPAQueryFactory;
import me.moon.market.domain.image.entity.Image;
import me.moon.market.domain.post.entity.Post;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import java.util.List;

import static me.moon.market.domain.image.entity.QImage.image;

public class ImageCustomRepositoryImpl extends QuerydslRepositorySupport implements ImageCustomRepository {

    private final JPAQueryFactory jpaQueryFactory;

    public ImageCustomRepositoryImpl(JPAQueryFactory jpaQueryFactory) {
        super(Image.class);
        this.jpaQueryFactory=jpaQueryFactory;
    }

    @Override
    public List<Image> findImagesByPost(Post post) {
        return jpaQueryFactory.selectFrom(image)
                .where(image.post.eq(post))
                .fetch();
    }
}
