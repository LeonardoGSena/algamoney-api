package com.sena.leonardo.algamoneyapi.domain.services;

import com.sena.leonardo.algamoneyapi.domain.exceptions.EntityNotFoundException;
import com.sena.leonardo.algamoneyapi.domain.models.Person;
import com.sena.leonardo.algamoneyapi.domain.repositories.PersonRepository;
import com.sena.leonardo.algamoneyapi.domain.services.exceptions.DatabaseException;
import com.sena.leonardo.algamoneyapi.domain.services.exceptions.ResourceNotFoundException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class PersonService {
    private PersonRepository personRepository;

    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @Transactional(readOnly = true)
    public Optional<Person> findPersonById(Long id) {
        return personRepository.findById(id);
    }

    @Transactional(readOnly = true)
    public List<Person> findAllPeople() {
        return personRepository.findAll();
    }

    @Transactional
    public Person insertNewPerson(Person person) {
        return personRepository.save(person);
    }

    @Transactional
    public void deletePerson(Long id) {
        personRepository.deleteById(id);
    }
}
