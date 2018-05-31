package service;

import dao.domain.Book;
import dto.BookDto;

import java.util.List;

public interface BookService {

    void createBook(BookDto bookDto);

    List<BookDto> listBooks();

    BookDto getBookById(Long id);

    void updateBook(BookDto bookDto);

    void readBook(BookDto bookDto);

    void deleteBook(BookDto bookDto);

    List<BookDto> searchByProperties(String...properties);
}
