package com.sena.leonardo.algamoneyapi.api.controllers;

import com.sena.leonardo.algamoneyapi.api.event.ResourceEvent;
import com.sena.leonardo.algamoneyapi.domain.models.Category;
import com.sena.leonardo.algamoneyapi.domain.services.CategoryService;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/categories")
public class CategoryController {

    private CategoryService categoryService;
    private ApplicationEventPublisher publisher;

    public CategoryController(CategoryService categoryService, ApplicationEventPublisher publisher) {
        this.categoryService = categoryService;
        this.publisher = publisher;
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Category> findCategoryById(@PathVariable Long id) {
        return categoryService.findCategoryById(id)
                .map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<Category>> findAllCategories() {
        List<Category> categories = categoryService.findAllCategories();
        return ResponseEntity.ok().body(categories);
    }

    @PostMapping
    public ResponseEntity<Category> insertCategory(@Valid @RequestBody Category category, HttpServletResponse response) {
        Category newCategory = categoryService.insertNewCategory(category);
        publisher.publishEvent(new ResourceEvent(this, response, newCategory.getId()));
        return ResponseEntity.status(HttpStatus.CREATED).body(newCategory);
    }
}
