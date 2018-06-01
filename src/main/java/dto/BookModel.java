package dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Data
@NoArgsConstructor
public class BookModel {

    private Long id;

    @NotEmpty(message = "Title needs to be provided")
    private String title;

    @NotEmpty(message = "Author needs to be provided")
    private String author;

    @NotEmpty(message = "ISBN needs to be provided")
    private String isbn;

    @NotNull(message = "printed date needs to be provided")
    private LocalDate printedDate;

    private boolean readAlready;
}
