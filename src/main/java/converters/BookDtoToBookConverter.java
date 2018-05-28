package converters;

import dao.domain.Book;
import dto.BookDto;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.time.ZoneId;
import java.util.Date;

import static java.time.ZoneId.systemDefault;

@Component
public class BookDtoToBookConverter implements Converter<BookDto, Book> {

    @Override
    public Book convert(BookDto source) {
        Book book = new Book();
        book.setIsbn(source.getIsbn());
        book.setAuthor(source.getAuthor());
        book.setTitle(source.getTitle());
        Date printedDate = Date.from(source.getBookPrintedDate().atStartOfDay(systemDefault()).toInstant());
        book.setPrintedDate(printedDate);
        return book;
    }
}