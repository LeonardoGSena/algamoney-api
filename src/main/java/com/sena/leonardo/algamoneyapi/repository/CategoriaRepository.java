package com.sena.leonardo.algamoneyapi.repository;

import com.sena.leonardo.algamoneyapi.model.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {
}
