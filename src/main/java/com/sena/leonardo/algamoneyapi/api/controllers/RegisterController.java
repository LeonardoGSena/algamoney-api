package com.sena.leonardo.algamoneyapi.api.controllers;

import com.sena.leonardo.algamoneyapi.api.event.ResourceEvent;
import com.sena.leonardo.algamoneyapi.domain.models.Register;
import com.sena.leonardo.algamoneyapi.domain.services.RegisterService;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/registers")
public class RegisterController {

    private RegisterService registerService;
    private ApplicationEventPublisher publisher;

    public RegisterController(RegisterService registerService, ApplicationEventPublisher publisher) {
        this.registerService = registerService;
        this.publisher = publisher;
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Register> findRegisterById(@PathVariable Long id) {
        return registerService.findRegisterById(id)
                .map(register -> ResponseEntity.ok(register))
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<Register>> findAllRegisters() {
        List<Register> registers = registerService.findAllRegisters();
        return ResponseEntity.ok().body(registers);
    }

    @PostMapping
    public ResponseEntity<Register> insertRegister(@Valid @RequestBody Register register, HttpServletResponse response) {
        return registerService.insertNewRegister(register)
                .map(r -> ResponseEntity.ok(r))
                .orElse(ResponseEntity.badRequest().build());
    }
}
