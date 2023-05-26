package br.com.artkou.junit.book;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;
import java.util.List;
import java.util.Optional;
import br.com.artkou.entity.BookEntity;
import br.com.artkou.exception.RequiredObjectIsNullException;
import br.com.artkou.model.Book;
import br.com.artkou.repository.BookRepository;
import br.com.artkou.service.BookService;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;


public class BookServiceTest {

    MockBook input;

    @InjectMocks
    private BookService service;

    @Mock
    BookRepository repository;

    @Rule
    public final ExpectedException ex = ExpectedException.none();

    @Before
    public void setUpMocks() {
        input = new MockBook();
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testFindById() {
        BookEntity entity = input.mockEntity(1);
        entity.setId(1L);

        when(repository.findById(1L)).thenReturn(Optional.of(entity));

        var result = service.findById(1L);
        assertNotNull(result);
        assertNotNull(result.getKey());
        assertNotNull(result.getLinks());

        assertTrue(result.getLinks().toString().contains("</api/book/v1/1>;rel=\"self\""));
        assertEquals("Some Author1", result.getAuthor());
        assertEquals("Some Title1", result.getTitle());
        assertEquals(Double.valueOf(25), result.getPrice());
        assertNotNull(result.getLaunchDate());
    }

    /*@Test
    public void testCreate() {
        BookEntity entity = input.mockEntity(1);
        entity.setId(1L);

        BookEntity persisted = entity;
        persisted.setId(1L);

        Book vo = input.mockVO(1);
        vo.setKey(1L);

        when(repository.save(entity)).thenReturn(persisted);

        var result = service.create(vo);

        assertNotNull(result);
        assertNotNull(result.getKey());
        assertNotNull(result.getLinks());

        assertTrue(result.getLinks().toString().contains("</api/book/v1/1>;rel=\"self\""));
        assertEquals("Some Author1", result.getAuthor());
        assertEquals("Some Title1", result.getTitle());
        assertEquals(Double.valueOf(25), result.getPrice());
        assertNotNull(result.getLaunchDate());
    }*/

    @Test
    public void testCreateWithNullBook() {

        ex.expect(RequiredObjectIsNullException.class);
        ex.expectMessage("It is not allowed to persist a null object!");
        service.create(null);
    }


    @Test
    public void testUpdate() {
        BookEntity entity = input.mockEntity(1);

        BookEntity persisted = entity;
        persisted.setId(1L);

        Book vo = input.mockVO(1);
        vo.setKey(1L);


        when(repository.findById(1L)).thenReturn(Optional.of(entity));
        when(repository.save(entity)).thenReturn(persisted);

        var result = service.update(vo);

        assertNotNull(result);
        assertNotNull(result.getKey());
        assertNotNull(result.getLinks());

        assertTrue(result.getLinks().toString().contains("</api/book/v1/1>;rel=\"self\""));
        assertEquals("Some Author1", result.getAuthor());
        assertEquals("Some Title1", result.getTitle());
        assertEquals(Double.valueOf(25), result.getPrice());
        assertNotNull(result.getLaunchDate());
    }



    @Test
    public void testUpdateWithNullBook() {

        ex.expect(RequiredObjectIsNullException.class);
        ex.expectMessage("It is not allowed to persist a null object!");
        service.create(null);
    }

    @Test
    public void testDelete() {
        BookEntity entity = input.mockEntity(1);
        entity.setId(1L);

        when(repository.findById(1L)).thenReturn(Optional.of(entity));

        service.delete(1L);
    }

    @Test
    public void testFindAll() {
        List<BookEntity> list = input.mockEntityList();

        when(repository.findAll()).thenReturn(list);

        var people = service.findAll();

        assertNotNull(people);
        assertEquals(14, people.size());

        var bookOne = people.get(1);

        assertNotNull(bookOne);
        assertNotNull(bookOne.getKey());
        assertNotNull(bookOne.getLinks());
        assertTrue(bookOne.getLinks().toString().contains("</api/book/v1/1>;rel=\"self\""));
        assertEquals("Some Author1", bookOne.getAuthor());
        assertEquals("Some Title1", bookOne.getTitle());
        assertEquals(Double.valueOf(25), bookOne.getPrice());
        assertNotNull(bookOne.getLaunchDate());

        var bookFour = people.get(4);

        assertNotNull(bookFour);
        assertNotNull(bookFour.getKey());
        assertNotNull(bookFour.getLinks());

        assertTrue(bookFour.getLinks().toString().contains("</api/book/v1/4>;rel=\"self\""));
        assertEquals("Some Author4", bookFour.getAuthor());
        assertEquals("Some Title4", bookFour.getTitle());
        assertEquals(Double.valueOf(25), bookFour.getPrice());
        assertNotNull(bookFour.getLaunchDate());

        var bookSeven = people.get(7);

        assertNotNull(bookSeven);
        assertNotNull(bookSeven.getKey());
        assertNotNull(bookSeven.getLinks());

        assertTrue(bookSeven.getLinks().toString().contains("</api/book/v1/7>;rel=\"self\""));
        assertEquals("Some Author7", bookSeven.getAuthor());
        assertEquals("Some Title7", bookSeven.getTitle());
        assertEquals(Double.valueOf(25), bookSeven.getPrice());
        assertNotNull(bookSeven.getLaunchDate());
    }

}
