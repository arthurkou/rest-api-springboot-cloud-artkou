package br.com.artkou.controller;

import br.com.artkou.model.Person;
import br.com.artkou.service.PersonService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/person")
@AllArgsConstructor
public class PersonController {

    private PersonService service;

    @GetMapping
    public List<Person> findAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public Person findById(@PathVariable(value = "id") Long id) {
        return service.findById(id);
    }

    @PostMapping
    public Person create(@RequestBody Person person) {
        return service.create(person);
    }

    @PutMapping
    public Person update(@RequestBody Person person) {
        return service.update(person);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable(value = "id") Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
