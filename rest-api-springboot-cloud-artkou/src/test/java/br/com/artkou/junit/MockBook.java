package br.com.artkou.junit;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import br.com.artkou.entity.BookEntity;
import br.com.artkou.model.Book;

public class MockBook {


    public BookEntity mockEntity() {
        return mockEntity(0);
    }

    public Book mockVO() {
        return mockVO(0);
    }

    public List<BookEntity> mockEntityList() {
        List<BookEntity> books = new ArrayList<BookEntity>();
        for (int i = 0; i < 14; i++) {
            books.add(mockEntity(i));
        }
        return books;
    }

    public List<Book> mockVOList() {
        List<Book> books = new ArrayList<>();
        for (int i = 0; i < 14; i++) {
            books.add(mockVO(i));
        }
        return books;
    }

    public BookEntity mockEntity(Integer number) {
        BookEntity book = new BookEntity();
        book.setId(number.longValue());
        book.setAuthor("Some Author" + number);
        book.setLaunchDate(new Date());
        book.setPrice(25D);
        book.setTitle("Some Title" + number);
        return book;
    }

    public Book mockVO(Integer number) {
        Book book = new Book();
        book.setKey(number.longValue());
        book.setAuthor("Some Author" + number);
        book.setLaunchDate(new Date());
        book.setPrice(25D);
        book.setTitle("Some Title" + number);
        return book;
    }

}
