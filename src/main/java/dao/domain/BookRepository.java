package dao.domain;

import java.util.List;

public interface BookRepository {

    void createBook(Book book);

    List<Book> readBooks();

    Book findById(Long id);

    void updateBook(Book book);

    void deleteBookById(Long id);

    List<Book> searchByProperties(String...properties);

}
