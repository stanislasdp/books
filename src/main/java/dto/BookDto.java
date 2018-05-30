package dto;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Data
@NoArgsConstructor
public class BookDto {

    private Long id;

    private String title;

    private String author;

    private String isbn;

    private LocalDate printedDate;

    private Boolean readAlready;
}
