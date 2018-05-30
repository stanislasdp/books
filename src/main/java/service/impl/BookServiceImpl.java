package service.impl;

import converters.BookDtoToBookConverter;
import converters.BookToBookDtoConverter;
import dao.domain.Book;
import dao.domain.BookRepository;
import dto.BookDto;
import org.springframework.stereotype.Service;
import service.BookService;

import java.util.List;

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

    @Override
    public void readBook(BookDto bookDto) {
        Book book = bookDtoToBookConverter.convert(bookDto);
        book.setReadAlready(Boolean.TRUE);
        bookRepository.updateBook(book);
    }

    @Override
    public void updateBook(BookDto bookDto) {
        Book book = bookDtoToBookConverter.convert(bookDto);
        bookRepository.updateBook(book);
    }

    @Override
    public void deleteBook(BookDto bookDto) {
        Book book = bookDtoToBookConverter.convert(bookDto);
        bookRepository.deleteBook(book);
    }

    @Override
    public List<BookDto> searchByProperties(String... properties) {
       return bookRepository.searchByProperties(properties).stream()
                .map(book -> bookToBookDtoConverter.convert(book))
                .collect(toList());
    }
}
