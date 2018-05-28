package converters;

import dao.domain.Book;
import dto.BookDto;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;

@Component
public class BookToBookDtoConverter implements Converter<Book, BookDto> {

    @Override
    public BookDto convert(Book source) {
        return BookDto.builder()
                .title(source.getTitle())
                .author(source.getAuthor())
                .isbn(source.getIsbn())
                .bookPrintedDate(Instant.ofEpochMilli(source.getPrintedDate().getTime())
                        .atZone(ZoneId.systemDefault()).toLocalDate())
                .build();
    }
}
