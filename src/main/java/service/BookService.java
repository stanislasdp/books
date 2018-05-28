package service;

import dto.BookDto;

import java.util.List;

public interface BookService {

    void createBook(BookDto bookDto);

    List<BookDto> listBooks();
}
