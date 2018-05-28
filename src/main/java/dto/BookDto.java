package dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class BookDto {

    private Long id;

    private String title;

    private String author;

    private String isbn;

    private LocalDate bookPrintedDate;

    private boolean readAlready;
}
