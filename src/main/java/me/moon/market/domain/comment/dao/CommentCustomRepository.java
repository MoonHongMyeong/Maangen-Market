package me.moon.market.domain.comment.dao;

import me.moon.market.domain.comment.entity.Comment;
import me.moon.market.domain.post.entity.Post;

import java.util.List;

public interface CommentCustomRepository {
    List<Comment> findCommentsByPost(Post post);
}
