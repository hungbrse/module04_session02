package com.ra.session03.controller;

import com.ra.session03.model.entity.Category;
import com.ra.session03.service.category.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/categories")
public class CategoryController {
    @Autowired
    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping
    private ResponseEntity<List<Category>> index() {
        List<Category> categories = categoryService.findAll();
        return new  ResponseEntity<>(categories, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Category> create(@RequestBody Category category){
        Category categoryNew = categoryService.save(category);
        return new ResponseEntity<>(categoryNew,HttpStatus.CREATED);
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id){
        Category category = categoryService.findById(id);
        if(category != null){
            return new ResponseEntity<>(category,HttpStatus.OK);
        }
        Map<String,String> error = new HashMap<>();
        error.put("mess","Not Phao");
        return new ResponseEntity<>(error,HttpStatus.NOT_FOUND);
    }
    @PutMapping("/{id}")
    public ResponseEntity<Category> update(@PathVariable Long id,@RequestBody Category category){
        category.setCategoryId(id);
        Category categoryUpdate = categoryService.save(category);
        return new ResponseEntity<>(categoryUpdate,HttpStatus.OK);

    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        categoryService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


}
