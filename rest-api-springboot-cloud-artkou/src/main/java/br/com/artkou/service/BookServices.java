package br.com.artkou.service;

import java.util.List;
import java.util.stream.Collectors;
import br.com.artkou.controller.BookController;
import br.com.artkou.entity.BookEntity;
import br.com.artkou.exception.RequiredObjectIsNullException;
import br.com.artkou.exception.ResourceNotFoundException;
import br.com.artkou.model.Book;
import br.com.artkou.repository.BookRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@AllArgsConstructor
public class BookServices {

	@Autowired
	BookRepository repository;

	public List<Book> findAll() {
		log.info("Finding all book!");
		List<Book> listBook = repository.findAll().stream()
				.map(Book::toEntity)
				.collect(Collectors.toList());
		listBook.stream().forEach(book ->
				book.add(linkTo(methodOn(BookController.class)
						.findById(book.getKey()))
						.withSelfRel()));

		return listBook;
	}

	public Book findById(Long id) {
		log.info("Finding one book!");
		Book book = repository.findById(id).map(Book::toEntity)
				.orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));
		return book.add(linkTo(methodOn(BookController.class).findById(id)).withSelfRel());
	}
	
	public Book create(Book book) {
		if (book == null) throw new RequiredObjectIsNullException();
		log.info("Creating one book!");
		BookEntity bookEntity = repository.save(new BookEntity(book));
		Book bookCreated = Book.toEntity(bookEntity);
		return bookCreated.add(linkTo(methodOn(BookController.class)
				.findById(book.getKey()))
				.withSelfRel());
	}
	
	public Book update(Book book) {
		if (book == null) throw new RequiredObjectIsNullException();
		log.info("Updating one book!");
		BookEntity bookEntity = repository.findById(book.getKey())
				.orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));

		BookEntity bookUpdated = repository.save(BookEntity.toEntity(bookEntity, book));
		Book response = Book.toEntity(bookUpdated);
		return response.add(linkTo(methodOn(BookController.class).findById(book.getKey())).withSelfRel());
	}
	
	public void delete(Long id) {
		log.info("Deleting one book!");
		var entity = repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));
		repository.delete(entity);
	}
}
