package service;

import controller.BookSearchAttributes;
import dto.BookModel;

import java.util.List;

public interface BookService {

    void createBook(BookModel bookDto);

    List<BookModel> listBooksByParams(BookSearchAttributes searchAttributes);

    BookModel getBookById(Long id);

    void updateBook(BookModel bookDto);

    void readBook(BookModel bookDto);

    void deleteBook(BookModel bookDto);

    List<BookModel> searchByProperties(String...properties);
}
