package com.sena.leonardo.algamoneyapi.domain.services;

import com.sena.leonardo.algamoneyapi.domain.models.Category;
import com.sena.leonardo.algamoneyapi.domain.repositories.CategoryRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

    private CategoryRepository categoriaRepository;

    public CategoryService(CategoryRepository categoriaRepository) {
        this.categoriaRepository = categoriaRepository;
    }

    @Transactional(readOnly = true)
    public Optional<Category> findCategoryById(Long id) {
        return categoriaRepository.findById(id);
    }

    @Transactional(readOnly = true)
    public List<Category> findAllCategories() {
        return categoriaRepository.findAll();
    }

    @Transactional
    public Category insertNewCategory(Category category) {
        return categoriaRepository.save(category);
    }
}
