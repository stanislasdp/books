package service.impl;

import converters.BookDtoToBookConverter;
import converters.BookToBookDtoConverter;
import dao.domain.Book;
import dao.domain.BookRepository;
import dto.BookDto;
import org.springframework.stereotype.Service;
import service.BookService;

import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

@Service
public class BookServiceImpl implements BookService {

    private BookDtoToBookConverter bookDtoToBookConverter;

    private BookToBookDtoConverter bookToBookDtoConverter;

    private BookRepository bookRepository;

    public BookServiceImpl(BookDtoToBookConverter bookDtoToBookConverter,
                           BookToBookDtoConverter bookToBookDtoConverter,
                           BookRepository bookRepository) {
        this.bookDtoToBookConverter = bookDtoToBookConverter;
        this.bookToBookDtoConverter = bookToBookDtoConverter;
        this.bookRepository = bookRepository;
    }

    @Override
    public void createBook(BookDto bookDto) {
        Book book = bookDtoToBookConverter.convert(bookDto);
        bookRepository.createBook(book);
    }

    @Override
    public List<BookDto> listBooks() {
        return bookRepository.readBooks().stream()
                .map(book -> bookToBookDtoConverter.convert(book))
                .collect(toList());
    }
}
