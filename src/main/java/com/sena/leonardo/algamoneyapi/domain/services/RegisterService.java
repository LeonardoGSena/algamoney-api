package com.sena.leonardo.algamoneyapi.domain.services;

import com.sena.leonardo.algamoneyapi.domain.models.Register;
import com.sena.leonardo.algamoneyapi.domain.repositories.PersonRepository;
import com.sena.leonardo.algamoneyapi.domain.repositories.RegisterRepository;
import com.sena.leonardo.algamoneyapi.domain.services.exceptions.PersonNotActiveException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class RegisterService {

    private RegisterRepository registerRepository;
    private PersonRepository personRepository;

    public RegisterService(RegisterRepository registerRepository, PersonRepository personRepository) {
        this.registerRepository = registerRepository;
        this.personRepository = personRepository;
    }


    @Transactional(readOnly = true)
    public Optional<Register> findRegisterById(Long id) {
        return registerRepository.findById(id);
    }

    @Transactional(readOnly = true)
    public List<Register> findAllRegisters() {
        return registerRepository.findAll();
    }

    @Transactional
    public Optional<Register> insertNewRegister(Register register) {
        return personRepository.findById(register.getPerson().getId())
                .filter(person -> person.getStatus().booleanValue())
                .map(person -> registerRepository.save(register));
    }
}
