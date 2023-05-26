package br.com.artkou.junit.person;

import br.com.artkou.entity.PersonEntity;
import br.com.artkou.exception.RequiredObjectIsNullException;
import br.com.artkou.model.Person;
import br.com.artkou.repository.PersonRepository;
import br.com.artkou.service.PersonService;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.util.List;
import java.util.Optional;
import static org.junit.Assert.*;
import static org.mockito.Mockito.when;


public class PersonServiceTest {

    MockPerson input;

    @InjectMocks
    private PersonService service;

    @Mock
    PersonRepository repository;

    @Rule
    public final ExpectedException ex = ExpectedException.none();

    @Before
    public void setUpMocks() {
        input = new MockPerson();
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testFindById() {
        PersonEntity entity = input.mockEntity(1);
        entity.setId(1L);
        when(repository.findById(1L)).thenReturn(Optional.of(entity));

        var result = service.findById(1L);
        assertNotNull(result);
        assertNotNull(result.getKey());
        assertNotNull(result.getLinks());
        assertTrue(result.getLinks().toString().contains("</api/person/v1/1>;rel=\"self\""));
        assertEquals("Addres Test1", result.getAddress());
        assertEquals("First Name Test1", result.getFirstName());
        assertEquals("Last Name Test1", result.getLastName());
        assertEquals("Female", result.getGender());
    }

    @Test
    public void testCreate() {
        PersonEntity entity = input.mockEntity(1);
        PersonEntity persisted = entity;
        persisted.setId(1L);

        Person vo = input.mockVO(1);
        vo.setKey(1L);

        when(repository.save(entity)).thenReturn(persisted);
        var result = service.create(vo);

        assertNotNull(result);
        assertNotNull(result.getKey());
        assertNotNull(result.getLinks());
        assertTrue(result.getLinks().toString().contains("</api/person/v1/1>;rel=\"self\""));
        assertEquals("Addres Test1", result.getAddress());
        assertEquals("First Name Test1", result.getFirstName());
        assertEquals("Last Name Test1", result.getLastName());
        assertEquals("Female", result.getGender());
    }

    @Test
    public void testCreateWithNullPerson() {

        ex.expect(RequiredObjectIsNullException.class);
        ex.expectMessage("It is not allowed to persist a null object!");
        service.create(null);
    }

    @Test
    public void testUpdate() {
        PersonEntity entity = input.mockEntity(1);
        PersonEntity persisted = entity;
        persisted.setId(1L);

        Person vo = input.mockVO(1);
        vo.setKey(1L);

        when(repository.findById(1L)).thenReturn(Optional.of(entity));
        when(repository.save(entity)).thenReturn(persisted);

        var result = service.update(vo);
        assertNotNull(result);
        assertNotNull(result.getKey());
        assertNotNull(result.getLinks());
        assertTrue(result.getLinks().toString().contains("</api/person/v1/1>;rel=\"self\""));
        assertEquals("Addres Test1", result.getAddress());
        assertEquals("First Name Test1", result.getFirstName());
        assertEquals("Last Name Test1", result.getLastName());
        assertEquals("Female", result.getGender());
    }

    @Test
    public void testUpdateWithNullPerson() {

        ex.expect(RequiredObjectIsNullException.class);
        ex.expectMessage("It is not allowed to persist a null object!");
        service.update(null);
    }

    @Test
    public void testFindAll() {
        List<PersonEntity> list = input.mockEntityList();
        when(repository.findAll()).thenReturn(list);

        var people = service.findAll();
        assertNotNull(people);
        assertEquals(14, people.size());

        var personOne = people.get(1);
        assertNotNull(personOne);
        assertNotNull(personOne.getKey());
        assertNotNull(personOne.getLinks());

        assertTrue(personOne.getLinks().toString().contains("</api/person/v1/1>;rel=\"self\""));
        assertEquals("Addres Test1", personOne.getAddress());
        assertEquals("First Name Test1", personOne.getFirstName());
        assertEquals("Last Name Test1", personOne.getLastName());
        assertEquals("Female", personOne.getGender());

        var personFour = people.get(4);
        assertNotNull(personFour);
        assertNotNull(personFour.getKey());
        assertNotNull(personFour.getLinks());
        assertTrue(personFour.getLinks().toString().contains("</api/person/v1/4>;rel=\"self\""));
        assertEquals("Addres Test4", personFour.getAddress());
        assertEquals("First Name Test4", personFour.getFirstName());
        assertEquals("Last Name Test4", personFour.getLastName());
        assertEquals("Male", personFour.getGender());

        var personSeven = people.get(7);
        assertNotNull(personSeven);
        assertNotNull(personSeven.getKey());
        assertNotNull(personSeven.getLinks());
        assertTrue(personSeven.getLinks().toString().contains("</api/person/v1/7>;rel=\"self\""));
        assertEquals("Addres Test7", personSeven.getAddress());
        assertEquals("First Name Test7", personSeven.getFirstName());
        assertEquals("Last Name Test7", personSeven.getLastName());
        assertEquals("Female", personSeven.getGender());

    }

    @Test
    public void testDelete() {
        PersonEntity entity = input.mockEntity(1);
        entity.setId(1L);
        when(repository.findById(1L)).thenReturn(Optional.of(entity));
        service.delete(1L);
    }
    
}
