package me.moon.market.domain.post.service;

import lombok.RequiredArgsConstructor;
import me.moon.market.domain.post.dao.CategoryRepository;
import me.moon.market.domain.post.entity.Category;
import me.moon.market.domain.post.exception.CategoryNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CategoryFindService {

    private CategoryRepository categoryRepository;

    public Category findByCategoryName(String categoryName){
        return categoryRepository.findByName(categoryName)
                .orElseThrow(() -> new CategoryNotFoundException(categoryName));
    }
}
