package me.moon.market.domain.post.service;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import me.moon.market.domain.post.dao.CategoryRepository;
import me.moon.market.domain.post.entity.Category;
import me.moon.market.domain.post.exception.CategoryNotFoundException;
import org.springframework.transaction.annotation.Transactional;

@Getter
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CategoryFindService {

    private CategoryRepository categoryRepository;

    public Category findByCategoryName(String categoryName){
        return categoryRepository.findByName(categoryName)
                .orElseThrow(() -> new CategoryNotFoundException(categoryName));
    }
}
