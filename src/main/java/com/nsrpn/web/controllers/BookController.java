package com.nsrpn.web.controllers;

import com.nsrpn.app.entities.Book;
import com.nsrpn.app.entities.UserBook;
import com.nsrpn.app.services.BookService;
import com.nsrpn.app.services.ShelfService;
import com.nsrpn.app.utils.Utils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
@RequestMapping(value = "/books")
public class BookController {

  private final String contentPage = "bookDict";
  private Logger logger = Logger.getLogger(BookController.class);
  private BookService bookService;
  private ShelfService shelfService;

  @Autowired
  public BookController(BookService bookService, ShelfService shelfService) {
    this.bookService = bookService;
    this.shelfService = shelfService;
  }

  @GetMapping
  public String booksDict(Model model, HttpSession session) {
    logger.info("got book dict");
    if (Utils.checkSession(session)) return "redirect:/login";
    logger.info("user_id=" + Utils.getUserIdFromSession(session).toString());
    model.addAttribute("book", new Book());
    model.addAttribute("userBook", new UserBook());
    model.addAttribute("filter", session.getAttribute("filter") != null ? session.getAttribute("filter") : Book.getFilter(contentPage, null));
    model.addAttribute("content", contentPage);
    List<Book> books = bookService.getFiltered(contentPage, session);
    model.addAttribute("bookList", books);
    model.addAttribute("bookToUserList",
      books.stream().collect(
        Collectors.toMap(
            b -> b.getId(),
            b -> b.getUsers().stream().map(ub -> ub.getUser().getId()).collect(Collectors.toList())
        )
      )
    );
    return "index";
  }

  @PostMapping("/save")
  public String saveBook(Book book) {
    bookService.saveBook(book);
    return "redirect:/books";
  }

  @PostMapping("/remove")
  public String removeBook(Book book, BindingResult bindingResult, HttpServletRequest request
  ) {
    if (Utils.checkSession(request)) return "redirect:/login";
    if (!bookService.removeBookById(Long.parseLong(request.getParameter("book_id")))) {
      bindingResult.addError(new ObjectError("Book", "Error due delete"));
    }
    return "redirect:/books";
  }

  @PostMapping("/addToShelf")
  public String addBookToShelf(@Validated Book book, BindingResult bindingResult, HttpServletRequest request) {
    if (Utils.checkSession(request)) return "redirect:/login";
    AddToShelf(Long.parseLong(request.getParameter("book_id")), bindingResult, request);
    return "redirect:/books";
  }

  @PostMapping("/filter")
  public String filter(Book book, HttpServletRequest request) {
    if (Utils.checkSession(request)) return "redirect:/login";
    SetFilterToSession(request);
    return "redirect:/books";
  }

  @PostMapping("/addToShelfFiltered")
  public String addToShelfFiltered(@Validated Book book, BindingResult bindingResult, HttpServletRequest request) {
    if (Utils.checkSession(request)) return "redirect:/login";
    SetFilterToSession(request);
    List<Book> books = bookService.getFiltered(contentPage, request.getSession());
    for (Book b : books) {
      AddToShelf(b.getId(), bindingResult, request);
    }
    return "redirect:/books";
  }

  private void SetFilterToSession(HttpServletRequest rq) {
    Map<String, String> filterList = Book.getFilter(contentPage, rq);
    rq.getSession().setAttribute("filter", filterList);
  }

  private void AddToShelf(Long book_id, BindingResult bindingResult, HttpServletRequest request) {
    try {
      shelfService.saveBook(
          Utils.getUserIdFromSession(request.getSession()),
          book_id
      );
    } catch (Exception e) {
      bindingResult.addError(
          new ObjectError(
              "book",
              e.getCause().getCause().getMessage().contains("uq_userbook_user_book") ? "Book with id=" + book_id.toString() + " is in Library already" : e.getMessage())
      );
    }
  }
}
