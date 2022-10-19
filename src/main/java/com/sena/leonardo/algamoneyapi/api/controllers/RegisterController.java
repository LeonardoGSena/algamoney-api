package com.sena.leonardo.algamoneyapi.api.controllers;

import com.sena.leonardo.algamoneyapi.domain.models.Register;
import com.sena.leonardo.algamoneyapi.domain.repositories.RegisterRepository;
import com.sena.leonardo.algamoneyapi.domain.repositories.filter.RegisterFilter;
import com.sena.leonardo.algamoneyapi.domain.repositories.projection.RegisterSummary;
import com.sena.leonardo.algamoneyapi.domain.services.RegisterService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/registers")
public class RegisterController {

    private RegisterService registerService;
    private RegisterRepository registerRepository;

    public RegisterController(RegisterService registerService, RegisterRepository registerRepository) {
        this.registerService = registerService;
        this.registerRepository = registerRepository;
    }


    @GetMapping(value = "/{id}")
    @PreAuthorize("hasAuthority('ROLE_PESQUISAR_LANCAMENTO') and hasAuthority('SCOPE_read')")
    public ResponseEntity<Register> findRegisterById(@PathVariable Long id) {
        return registerService.findRegisterById(id)
                .map(register -> ResponseEntity.ok(register))
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    @PreAuthorize("hasAuthority('ROLE_PESQUISAR_LANCAMENTO') and hasAuthority('SCOPE_read')")
    public Page<Register> search(RegisterFilter registerFilter, Pageable pageable) {
       return registerRepository.filter(registerFilter, pageable);
    }

    @GetMapping(params = "summary")
    @PreAuthorize("hasAuthority('ROLE_PESQUISAR_LANCAMENTO') and hasAuthority('SCOPE_read')")
    public Page<RegisterSummary> registerSummary(RegisterFilter registerFilter, Pageable pageable) {
       return registerRepository.registerSummary(registerFilter, pageable);
    }

    @PostMapping
    @PreAuthorize("hasAuthority('ROLE_CADASTRAR_LANCAMENTO') and hasAuthority('SCOPE_write')")
    public ResponseEntity<Register> insertRegister(@Valid @RequestBody Register register, HttpServletResponse response) {
        return registerService.insertNewRegister(register)
                .map(r -> ResponseEntity.ok(r))
                .orElse(ResponseEntity.badRequest().build());
    }

    @DeleteMapping(value = "/{id}")
    @PreAuthorize("hasAuthority('ROLE_REMOVER_LANCAMENTO') and hasAuthority('SCOPE_write')")
    public ResponseEntity<Void> deleteRegister(@PathVariable Long id) {
        registerService.deleteRegisterById(id);
        return ResponseEntity.noContent().build();
    }
}
