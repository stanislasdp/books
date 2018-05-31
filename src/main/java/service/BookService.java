package service;

import controller.BookSearchAttributes;
import dto.BookDto;

import java.util.List;

public interface BookService {

    void createBook(BookDto bookDto);

    List<BookDto> listBooksByParams(BookSearchAttributes searchAttributes);

    BookDto getBookById(Long id);

    void updateBook(BookDto bookDto);

    void readBook(BookDto bookDto);

    void deleteBook(BookDto bookDto);

    List<BookDto> searchByProperties(String...properties);
}
