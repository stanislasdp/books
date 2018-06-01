package converters;

import dao.domain.Book;
import dto.BookModel;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.ZoneId;

@Component
public class BookToBookDtoConverter implements Converter<Book, BookModel> {

    @Override
    public BookModel convert(Book source) {
        BookModel bookDto = new BookModel();
        bookDto.setId(source.getId());
        bookDto.setTitle(source.getTitle());
        bookDto.setAuthor(source.getAuthor());
        bookDto.setIsbn(source.getIsbn());
        bookDto.setPrintedDate(Instant.ofEpochMilli(source.getPrintedDate().getTime()).atZone(ZoneId.systemDefault()).toLocalDate());
        bookDto.setReadAlready(source.isReadAlready());
        return bookDto;
    }
}
