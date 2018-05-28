package service;

import dto.BookDto;

import java.util.List;

public interface BookService {

    void createBook(BookDto bookDto);

    List<BookDto> listBooks();

    void readBook(BookDto bookDto);

    void updateBook(BookDto bookDto);

    void deleteBook(BookDto bookDto);

    List<BookDto> searchByProperties(String...properties);
}
