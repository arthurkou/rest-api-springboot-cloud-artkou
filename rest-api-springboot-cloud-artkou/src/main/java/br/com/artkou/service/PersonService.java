package br.com.artkou.service;

import java.util.List;
import java.util.stream.Collectors;
import br.com.artkou.controller.PersonController;
import br.com.artkou.exception.RequiredObjectIsNullException;
import br.com.artkou.model.Person;
import br.com.artkou.entity.PersonEntity;
import br.com.artkou.exception.ResourceNotFoundException;
import br.com.artkou.repository.PersonRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;


@Service
@Slf4j
@AllArgsConstructor
public class PersonService {

    private PersonRepository repository;

    public List<Person> findAll() {
        log.info("Finding all people!");
        List<Person> listPerson = repository.findAll().stream()
                .map(Person::toEntity)
                .collect(Collectors.toList());
        listPerson.stream().forEach(person ->
                person.add(linkTo(methodOn(PersonController.class)
                        .findById(person.getKey()))
                        .withSelfRel()));
        return listPerson;
    }

    public Person findById(Long id) {
        log.info("Finding one person!");
        Person person = repository.findById(id).map(Person::toEntity)
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));
        person.add(linkTo(methodOn(PersonController.class).findById(id)).withSelfRel());
        return person;
    }

    public Person create(Person person) {
        if (person == null) throw new RequiredObjectIsNullException();
        log.info("Creating one person!");
        PersonEntity personEntity = repository.save(new PersonEntity(person));
        Person personCreated = Person.toEntity(personEntity);
        personCreated.add(linkTo(methodOn(PersonController.class)
                .findById(person.getKey()))
                .withSelfRel());
        return personCreated;
    }

    public Person update(Person person) {
        if (person == null) throw new RequiredObjectIsNullException();
        log.info("Updating one person!");
        PersonEntity personEntity  = repository.findById(person.getKey())
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));

        PersonEntity personUpdated = repository.save(PersonEntity.toEntity(personEntity, person));
        Person response = Person.toEntity(personUpdated);
        response.add(linkTo(methodOn(PersonController.class).findById(person.getKey())).withSelfRel());
        return response;
    }

    public void delete(Long id) {
        log.info("Deleting one person!");
        var entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));
        repository.delete(entity);
    }
}
