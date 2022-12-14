package com.sena.leonardo.algamoneyapi.domain.repositories;

import com.sena.leonardo.algamoneyapi.domain.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
}
