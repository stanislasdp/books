package service.impl;

import controller.BookSearchAttributes;
import converters.BookDtoToBookConverter;
import converters.BookToBookDtoConverter;
import dao.domain.Book;
import dao.domain.BookRepository;
import dto.BookModel;
import org.springframework.stereotype.Service;
import service.BookService;

import java.util.List;
import java.util.Objects;

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
    public void createBook(BookModel bookDto) {
        Book book = bookDtoToBookConverter.convert(bookDto);
        bookRepository.createBook(book);
    }

    @Override
    public List<BookModel> listBooksByParams(BookSearchAttributes searchAttributes) {
        return bookRepository.readBooks(searchAttributes.getSearchParameters()).stream()
            .map(book -> bookToBookDtoConverter.convert(book))
            .collect(toList());
    }

    @Override
    public BookModel getBookById(Long id) {
        Book book = bookRepository.findById(id);
        Objects.requireNonNull(book);
        return bookToBookDtoConverter.convert(book);
    }

    @Override
    public void readBook(BookModel bookDto) {
        Book book = bookDtoToBookConverter.convert(bookDto);
        book.setReadAlready(Boolean.TRUE);
        bookRepository.updateBook(book);
    }

    @Override
    public void updateBook(BookModel bookDto) {
        Book book = bookDtoToBookConverter.convert(bookDto);
        bookRepository.updateBook(book);
    }

    @Override
    public List<BookModel> searchByProperties(String... properties) {
       return bookRepository.searchByProperties(properties).stream()
                .map(book -> bookToBookDtoConverter.convert(book))
                .collect(toList());
    }

    @Override
    public void deleteBook(BookModel bookDto) {
        bookRepository.deleteBookById(bookDto.getId());
    }
}
