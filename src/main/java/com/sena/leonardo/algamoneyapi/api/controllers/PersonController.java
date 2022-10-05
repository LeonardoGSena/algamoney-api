package com.sena.leonardo.algamoneyapi.api.controllers;

import com.sena.leonardo.algamoneyapi.api.event.ResourceEvent;
import com.sena.leonardo.algamoneyapi.domain.models.Person;
import com.sena.leonardo.algamoneyapi.domain.services.PersonService;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/people")
public class PersonController {
    private PersonService personService;
    private ApplicationEventPublisher publisher;

    public PersonController(PersonService personService, ApplicationEventPublisher publisher) {
        this.personService = personService;
        this.publisher = publisher;
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Person> findPersonById(@PathVariable Long id) {
        return personService.findPersonById(id)
                .map(person -> ResponseEntity.ok(person))
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<Person>> findAllPeople() {
        List<Person> allPeople = personService.findAllPeople();
        return ResponseEntity.ok().body(allPeople);
    }

    @PostMapping
    public ResponseEntity<Person> insertPerson(@Valid @RequestBody Person person, HttpServletResponse response) {
        Person newPerson = personService.insertNewPerson(person);
        publisher.publishEvent(new ResourceEvent(this, response, newPerson.getId()));

        return ResponseEntity.status(HttpStatus.CREATED).body(newPerson);
    }
}
