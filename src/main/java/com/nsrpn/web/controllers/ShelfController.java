package com.nsrpn.web.controllers;

import com.nsrpn.app.entities.Book;
import com.nsrpn.app.entities.UserBook;
import com.nsrpn.app.services.ShelfService;
import com.nsrpn.app.utils.Utils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Map;

@Controller
@RequestMapping(value = "/shelf")
public class ShelfController {

  private final String contentPage = "shelf";
  private Logger logger = Logger.getLogger(ShelfController.class);
  private ShelfService shelfService;

  @Autowired
  public ShelfController(ShelfService bookService) {
    this.shelfService = bookService;
  }

  @GetMapping
  public String shelf(Model model, HttpSession session) {
    logger.info("got book shelf");
    if (Utils.checkSession(session)) return "redirect:/login";
    logger.info("user_id=" + Utils.getUserIdFromSession(session).toString());
    model.addAttribute("book", new Book());
    model.addAttribute("userBook", new UserBook());
    model.addAttribute("filter", session.getAttribute("filter") != null ? session.getAttribute("filter") : Book.getFilter(contentPage, null));
    model.addAttribute("content", contentPage);
    model.addAttribute("bookList", shelfService.getBooksByUserId(contentPage, session));
    return "index";
  }

  @PostMapping("/save")
  public String saveBook(Book book, HttpServletRequest request) {
    if (Utils.checkSession(request)) return "redirect:/login";
    shelfService.saveBook(Utils.getUserIdFromSession(request.getSession()), book.getId());
    logger.info("current repository size: " + shelfService.getAllBooks().size());
    return "redirect:/shelf";
  }

  @PostMapping("/remove")
  public String removeBook(Book book, BindingResult bindingResult, HttpServletRequest request
  ) {
    if (Utils.checkSession(request)) return "redirect:/login";
    if (!shelfService.removeBookById(
          Utils.getUserIdFromSession(request.getSession()),
          Long.parseLong(request.getParameter("book_id"))
         )
    ) {
      bindingResult.addError(new ObjectError("Book", "Error due delete"));
    }
    return "redirect:/shelf";
  }

  @PostMapping("/filter")
  public String filter(Book Book, HttpServletRequest request) {
    if (Utils.checkSession(request)) return "redirect:/login";
    Map<String, String> filterList = Book.getFilter(contentPage, request);
    request.getSession().setAttribute("filter", filterList);
    return "redirect:/shelf";
  }

  @PostMapping("/removeFiltered")
  public String removeFiltered(Book Book, HttpServletRequest request) {
    if (Utils.checkSession(request)) return "redirect:/login";
    Utils.setFilterToSession(contentPage, request);
    for (UserBook ub : shelfService.getFiltered(contentPage, request.getSession())) {
      shelfService.removeBookById(ub.getUser().getId(), ub.getBook().getId());
    }
    request.getSession().removeAttribute("filter");
    return "redirect:/shelf";
  }

}
