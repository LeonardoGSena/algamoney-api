package com.sena.leonardo.algamoneyapi.services;

import com.sena.leonardo.algamoneyapi.models.Category;
import com.sena.leonardo.algamoneyapi.repositories.CategoryRepository;
import com.sena.leonardo.algamoneyapi.services.exceptions.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

    private CategoryRepository categoriaRepository;

    public CategoryService(CategoryRepository categoriaRepository) {
        this.categoriaRepository = categoriaRepository;
    }

    @Transactional(readOnly = true)
    public List<Category> findAllCategories() {
        return categoriaRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Category findCategoryById(Long id) {
        Optional<Category> obj = categoriaRepository.findById(id);
        return obj.orElseThrow(() -> new ResourceNotFoundException("Entity not found"));
    }

    @Transactional
    public Category insertNewCategory(Category category) {
        return categoriaRepository.save(category);
    }
}
