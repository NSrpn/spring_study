package com.nsrpn.web.controllers;

import com.nsrpn.app.entities.Book;
import com.nsrpn.app.entities.UserBook;
import com.nsrpn.app.files.FileStorageConfiguration;
import com.nsrpn.app.services.ShelfService;
import com.nsrpn.app.storage.BookStorage;
import com.nsrpn.app.storage.UserStorage;
import com.nsrpn.app.utils.Utils;
import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

@Controller
@RequestMapping(value = "/shelf")
public class ShelfController {

  private final String contentPage = "shelf";
  private Logger logger = Logger.getLogger(ShelfController.class);
  private ShelfService shelfService;

  @Autowired
  FileStorageConfiguration fileStorageConfiguration;

  @Autowired
  public ShelfController(ShelfService bookService) {
    this.shelfService = bookService;
  }

  @GetMapping
  public String shelf(Model model, HttpSession session) {
    logger.info("got book shelf");
    logger.info("user_name=" + Utils.getUserNameFromSession());
    Boolean needPwdChange = UserStorage.getInstance().getByUserName(Utils.getUserNameFromSession()).getNeedPwdChange();
    if (needPwdChange != null && needPwdChange)
      return "redirect:/login/edit";
    model.addAttribute("currentIsAdmin", Utils.checkCurrentUserAdmin());
    model.addAttribute("book", new Book());
    model.addAttribute("userBook", new UserBook());
    model.addAttribute("filter", session.getAttribute("filter") != null ? session.getAttribute("filter") : Book.getFilter(contentPage, null));
    model.addAttribute("content", contentPage);
    model.addAttribute("bookList", shelfService.getBooksByUserName(contentPage, session));
    return "index";
  }

  @PostMapping("/save")
  public String saveBook(Book book, HttpServletRequest request) {
    if (Utils.checkSession()) return "redirect:/login";
    shelfService.saveBook(Utils.getUserNameFromSession(), book.getId());
    logger.info("current repository size: " + shelfService.getAllBooks().size());
    return "redirect:/shelf";
  }

  @PostMapping("/remove")
  public String removeBook(Book book, BindingResult bindingResult, HttpServletRequest request
  ) {
    if (Utils.checkSession()) return "redirect:/login";
    if (!shelfService.removeBookById(
          Utils.getUserNameFromSession(),
          Long.parseLong(request.getParameter("book_id"))
         )
    ) {
      bindingResult.addError(new ObjectError("Book", "Error due delete"));
    }
    return "redirect:/shelf";
  }

  @PostMapping("/filter")
  public String filter(Book Book, HttpServletRequest request) {
    if (Utils.checkSession()) return "redirect:/login";
    Map<String, String> filterList = Book.getFilter(contentPage, request);
    request.getSession().setAttribute("filter", filterList);
    return "redirect:/shelf";
  }

  @PostMapping("/removeFiltered")
  public String removeFiltered(Book Book, HttpServletRequest request) {
    if (Utils.checkSession()) return "redirect:/login";
    Utils.setFilterToSession(contentPage, request);
    for (UserBook ub : shelfService.getFiltered(contentPage, request.getSession())) {
      shelfService.removeBookById(ub.getUser().getUserName(), ub.getBook().getId());
    }
    request.getSession().removeAttribute("filter");
    return "redirect:/shelf";
  }

  @GetMapping("/download")
  public void getFile(@RequestParam("book_id") Long bookId, HttpServletResponse response) {
    try {
      InputStream is =
          new ByteArrayInputStream(
              fileStorageConfiguration.getFileStorage().getFile(
                  BookStorage.getInstance().getById(bookId)
              )
          );
      IOUtils.copy(is, response.getOutputStream());
      response.flushBuffer();
    } catch (IOException ex) {
      throw new RuntimeException("IOError writing file to output stream");
    }

  }

}
