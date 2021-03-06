package dao.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
public class Book {

    @Id
    @Column(name = "book_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "book_title", nullable = false, columnDefinition = "VARCHAR(100)")
    private String title;

    @Column(name = "book_author", nullable = false, updatable = false, columnDefinition = "VARCHAR(100)")
    private String author;

    @Column(name = "book_isbn", nullable = false, columnDefinition = "VARCHAR(20)")
    private String isbn;

    @Column(name = "book_printed_date", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date printedDate;

    @Column(name = "book_read_already", nullable = false)
    private boolean readAlready;
}
