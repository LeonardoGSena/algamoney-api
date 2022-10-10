package com.sena.leonardo.algamoneyapi.domain.services;

import com.sena.leonardo.algamoneyapi.domain.models.Person;
import com.sena.leonardo.algamoneyapi.domain.repositories.PersonRepository;
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
    public Optional<Person> updatePerson(Long id) {
        return personRepository.findById(id);
    }

    @Transactional
    public Optional<Person> updateActiveProperties(Long id) {
        return personRepository.findById(id);
    }

    @Transactional
    public void deletePerson(Long id) {
        personRepository.deleteById(id);
    }

}
