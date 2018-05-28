package dao.domain;

import java.util.List;

public interface BookRepository {

    void createBook(Book book);

    List<Book> readBooks();

    void updateBook(Book book);

    void deleteBook(Book book);

    List<Book> searchByProperties(String...properties);

}
