package br.com.artkou.entity;

import java.io.Serializable;
import java.util.Date;
import br.com.artkou.model.Book;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Table(name = "books")
@NoArgsConstructor
@Data
@Builder
@AllArgsConstructor
public class BookEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false, length = 180)
	private String author;

	@Column(name = "launch_date", nullable = false)
	@Temporal(TemporalType.DATE)
	private Date launchDate;
	
	@Column(nullable = false)
	private Double price;
	
	@Column(nullable = false, length = 250)
	private String title;
	
	public BookEntity(Book book) {
		this.id = book.getKey();
		this.author = book.getAuthor();
		this.launchDate = book.getLaunchDate();
		this.price = book.getPrice();
		this.title = book.getTitle();
	}

	public static BookEntity toEntity(BookEntity bookEntity, Book book) {
		bookEntity.setAuthor(book.getAuthor());
		bookEntity.setLaunchDate(book.getLaunchDate());
		bookEntity.setPrice(book.getPrice());
		bookEntity.setTitle(book.getTitle());
		return bookEntity;
	}
}
