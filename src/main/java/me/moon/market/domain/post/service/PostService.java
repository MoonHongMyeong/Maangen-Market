package me.moon.market.domain.post.service;

import lombok.RequiredArgsConstructor;
import me.moon.market.domain.post.dao.PostRepository;
import me.moon.market.domain.post.dto.PostSaveRequest;
import me.moon.market.domain.post.entity.Category;
import me.moon.market.domain.post.entity.Post;
import me.moon.market.domain.user.service.UserFindService;
import me.moon.market.global.dto.SessionUser;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class PostService {

    private PostRepository postRepository;
    private UserFindService userFindService;
    private CategoryFindService categoryFindService;

    public void create(PostSaveRequest dto, SessionUser user) {

        Post post = dto.toEntity(userFindService.findBySessionUser(user));
        Category category = categoryFindService.findByCategoryName(dto.getCategory());

        post.setCategory(category);

        postRepository.save(post);
    }

}
