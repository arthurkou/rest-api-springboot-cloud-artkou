package br.com.artkou.model;

import java.io.Serializable;
import java.util.Date;
import br.com.artkou.entity.BookEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.springframework.hateoas.RepresentationModel;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonPropertyOrder({"id", "author", "launchDate", "price", "title"})
public class Book extends RepresentationModel<Book> implements Serializable {

	private static final long serialVersionUID = 1L;

	@JsonProperty("id")
	private Long key;
	private String author;
	private Date launchDate;
	private Double price;
	private String title;

	public static Book toEntity(BookEntity book) {
		return Book.builder()
				.key(book.getId())
				.author(book.getAuthor())
				.launchDate(book.getLaunchDate())
				.price(book.getPrice())
				.title(book.getTitle())
				.build();
	}
}
