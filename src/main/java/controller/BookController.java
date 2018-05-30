package controller;

import dto.BookDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import service.BookService;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.List;

@Controller
public class BookController {

    private BookService bookService;

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping(value = "/listBooks/{pageId}")
    public String listBooks(Model model, @PathVariable(name = "pageId") Integer pageId) {
        if (pageId < 1) {
            throw new RuntimeException();
        }
        List<BookDto> books = bookService.listBooks();
        PagedListHolder<BookDto> pagedListHolder = new PagedListHolder<>(books);
        pagedListHolder.setPage(pageId - 1);
        model.addAttribute("pageHolder", pagedListHolder);
        return "allBooks";
    }

    @GetMapping("/addBook")
    public String addBook(Model model) {
        BookDto book = new BookDto();
        model.addAttribute("book", book);
        return "addBook";
    }

    @PostMapping("/addNewBook")
    public String addBook(@ModelAttribute("book") BookDto bookDto, BindingResult bindingResult) {
        bookService.createBook(bookDto);
        return "redirect:/listBooks/1";
    }

}
