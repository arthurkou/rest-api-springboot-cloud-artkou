package br.com.artkou.service;

import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import br.com.artkou.model.Person;
import br.com.artkou.entity.PersonEntity;
import br.com.artkou.exception.ResourceNotFoundException;
import br.com.artkou.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class PersonService {

    private Logger logger = Logger.getLogger(PersonService.class.getName());

    @Autowired
    PersonRepository repository;

    public List<Person> findAll() {
        logger.info("Finding all people!");
        return repository.findAll().stream()
                .map(Person::toEntity)
                .collect(Collectors.toList());
    }

    public Person findById(Long id) {
        logger.info("Finding one person!");
        return repository.findById(id).map(Person::toEntity)
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));
    }

    public Person create(Person person) {
        logger.info("Creating one person!");
        PersonEntity personEntity = repository.save(new PersonEntity(person));
        return Person.toEntity(personEntity);
    }

    public Person update(Person person) {
        logger.info("Updating one person!");
        PersonEntity personEntity  = repository.findById(person.getId())
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));

        PersonEntity personUpdated = repository.save(PersonEntity.toEntity(personEntity, person));
        return  Person.toEntity(personUpdated);
    }

    public void delete(Long id) {
        logger.info("Deleting one person!");
        var entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));
        repository.delete(entity);
    }
}
