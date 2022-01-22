package me.moon.market.domain.comment.service;

import lombok.RequiredArgsConstructor;
import me.moon.market.domain.comment.dao.CommentRepository;
import me.moon.market.domain.comment.dto.CommentSaveRequest;
import me.moon.market.domain.comment.dto.CommentUpdateRequest;
import me.moon.market.domain.comment.entity.Comment;
import me.moon.market.domain.comment.exception.CommentNotFoundException;
import me.moon.market.domain.post.entity.Post;
import me.moon.market.domain.post.service.PostFindService;
import me.moon.market.domain.user.entity.User;
import me.moon.market.domain.user.service.UserFindService;
import me.moon.market.global.dto.SessionUser;
import me.moon.market.global.error.exception.UnAuthorizedAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class CommentService {

    private final CommentRepository commentRepository;
    private final PostFindService postFindService;
    private final UserFindService userFindService;

    public void create(Long postId, SessionUser sessionUser, CommentSaveRequest dto) {

        Post post = postFindService.findPostById(postId);
        User user = userFindService.findUserBySessionUser(sessionUser);

        Comment comment = dto.toEntity(user, post);

        commentRepository.save(comment);
    }

    public void update(Long commentId, SessionUser user, CommentUpdateRequest dto) {
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new CommentNotFoundException(commentId.toString()));

        isValidAuthor(user, comment);

        comment.update(dto);
    }

    private void isValidAuthor(SessionUser user, Comment comment) {
        if(new SessionUser(comment.getAuthor()) != user) throw new UnAuthorizedAccessException("");
    }
}
