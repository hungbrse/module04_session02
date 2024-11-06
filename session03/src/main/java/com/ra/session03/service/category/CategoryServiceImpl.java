package com.ra.session03.service.category;



import com.ra.session03.model.entity.Category;
import com.ra.session03.repository.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public List<Category> findAll() {
        return categoryRepository.findAll();
    }

    @Override
    public Category save(Category category) {
        return categoryRepository.save(category);
    }

    @Override
    public Category findById(long id) {
        return categoryRepository.findById(id).orElse(null);
    }

    @Override
    public void delete(long id) {
        categoryRepository.deleteById(id);
    }
}
