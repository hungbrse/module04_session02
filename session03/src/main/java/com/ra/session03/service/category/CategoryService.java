package com.ra.session03.service.category;


import com.ra.session03.model.entity.Category;

import java.util.List;

public interface CategoryService {
    List<Category> findAll();
    Category findById(long id);
    Category save(Category category);
    void delete(long id);
}
