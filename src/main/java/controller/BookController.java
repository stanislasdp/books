package controller;

import dto.BookDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import service.BookService;

import java.util.HashMap;
import java.util.List;
import java.util.Objects;

@Controller
/*@SessionAttributes("searchParams")*/
public class BookController {

    private BookService bookService;

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping(value = "/listBooks")
    public String listBooks(Model model) {
        model.addAttribute("searchParams", new HashMap<>());
        return redirectToFirstPage();
    }

    @GetMapping(value = "/listBooks/{pageId}")
    public String listBooks(Model model, @PathVariable(name = "pageId") Integer pageId) {
        if (pageId < 1) {
            throw new RuntimeException();
        }
        List<BookDto> books = bookService.listBooks();
        PagedListHolder<BookDto> pagedListHolder = new PagedListHolder<>(books);
        pagedListHolder.setPage(pageId - 1);
        model.addAttribute("searchParams", new HashMap<>());
        model.addAttribute("pageHolder", pagedListHolder);
        return "allBooks";
    }

    @GetMapping("/addBook")
    public String addBook(Model model) {
        BookDto book = new BookDto();
        model.addAttribute("book", book);
        return "addBook";
    }

    @PostMapping(value = "/addNewBook")
    public String addBook(@ModelAttribute("book") BookDto bookDto) {
        bookService.createBook(bookDto);
        return redirectToFirstPage();
    }

    @GetMapping("/getBookForUpdate/{bookId}")
    public String getBook(@PathVariable("bookId") Long id, Model model)  {
        BookDto bookDto = bookService.getBookById(id);
        model.addAttribute("book", bookDto);
        return "updateBook";
    }

    @PostMapping("/updateBook")
    public String updateBookRevision(@ModelAttribute BookDto bookDto) {
        Objects.requireNonNull(bookDto);
        bookDto.setReadAlready(Boolean.FALSE);
        bookService.updateBook(bookDto);
        return redirectToFirstPage();
    }

    @PostMapping("/readBook/{bookId}")
    public String readBook(@PathVariable(name = "bookId") Long id) {
        BookDto bookDto = bookService.getBookById(id);
        Objects.requireNonNull(bookDto);
        bookDto.setReadAlready(Boolean.TRUE);
        bookService.updateBook(bookDto);
        return redirectToFirstPage();
    }

    @DeleteMapping("deleteBook/{bookId}")
    public String deleteBook(@PathVariable(name = "bookId") Long id) {
        BookDto bookDto = new BookDto();
        bookDto.setId(id);
        bookService.deleteBook(bookDto);
        return redirectToFirstPage();
    }

    private String redirectToFirstPage() {
        return "redirect:/listBooks/1";
    }

}
