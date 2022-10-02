package com.sena.leonardo.algamoneyapi.controller;

import com.sena.leonardo.algamoneyapi.model.Categoria;
import com.sena.leonardo.algamoneyapi.repository.CategoriaRepository;
import com.sena.leonardo.algamoneyapi.service.CategoriaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/categorias")
public class CategoriaController {

    private CategoriaService categoriaService;

    public CategoriaController(CategoriaService categoriaService) {
        this.categoriaService = categoriaService;
    }

    @GetMapping
    public ResponseEntity<List<Categoria>> listarCategorias() {
        List<Categoria> categorias = categoriaService.findAll();
        return ResponseEntity.ok().body(categorias);
    }

    @PostMapping
    public ResponseEntity<Categoria> inserirCategorias(@RequestBody Categoria categoria) {
        Categoria categoriaSalva = categoriaService.inserirCategoria(categoria);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(categoria.getId()).toUri();
        return ResponseEntity.created(uri).body(categoria);

    }
}
