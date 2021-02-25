package de.codecentric.psd.worblehat.web.controller;

import de.codecentric.psd.worblehat.domain.Book;
import de.codecentric.psd.worblehat.domain.BookService;
import de.codecentric.psd.worblehat.web.formdata.MyBorrowedBooksFormData;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/myBorrowedBooks")
public class MyBorrowedBooksController {

  private BookService bookService;

  @Autowired
  public MyBorrowedBooksController(BookService bookService) {
    this.bookService = bookService;
  }

  @RequestMapping(method = RequestMethod.GET)
  public String setupForm(ModelMap modelMap) {
    modelMap.put("myBorrowedBooksFormData", new MyBorrowedBooksFormData());
    return "myBorrowedBooksForm";
  }

  @RequestMapping(method = RequestMethod.POST)
  public String showBorrowedBooks(
      @ModelAttribute("myBorrowedBooksFormData") @Valid MyBorrowedBooksFormData formData,
      BindingResult result,
      ModelMap modelMap) {
    if (result.hasErrors()) {
      return "myBorrowedBooksForm";
    } else {
      List<Book> books = bookService.findAllBooksByBorrower(formData.getEmail());
      modelMap.addAttribute("books", books);
      return "myBorrowedBooks";
    }
  }
}
