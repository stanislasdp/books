package dao.domain;

import java.util.List;
import java.util.Map;

public interface BookRepository {

    void createBook(Book book);

    List<Book> readBooks(Map<String, String> paramsMap);

    Book findById(Long id);

    void updateBook(Book book);

    void deleteBookById(Long id);

    List<Book> searchByProperties(String... properties);

}
