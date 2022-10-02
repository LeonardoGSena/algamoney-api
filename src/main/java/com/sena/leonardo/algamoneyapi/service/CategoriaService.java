package com.sena.leonardo.algamoneyapi.service;

import com.sena.leonardo.algamoneyapi.model.Categoria;
import com.sena.leonardo.algamoneyapi.repository.CategoriaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoriaService {

    private CategoriaRepository categoriaRepository;

    public CategoriaService(CategoriaRepository categoriaRepository) {
        this.categoriaRepository = categoriaRepository;
    }

    public List<Categoria> findAll() {
        return categoriaRepository.findAll();
    }

    public Categoria inserirCategoria(Categoria categoria) {
        return categoriaRepository.save(categoria);
    }
}
