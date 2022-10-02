package com.sena.leonardo.algamoneyapi.repositories;

import com.sena.leonardo.algamoneyapi.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
