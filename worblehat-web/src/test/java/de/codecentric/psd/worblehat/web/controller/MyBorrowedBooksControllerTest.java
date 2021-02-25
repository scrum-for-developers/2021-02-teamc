package de.codecentric.psd.worblehat.web.controller;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.ui.ModelMap;

import de.codecentric.psd.worblehat.domain.Book;
import de.codecentric.psd.worblehat.domain.BookService;

public class MyBorrowedBooksControllerTest {
    private BookService bookService;

    private MyBorrowedBooksController myBorrowedBooksController;
  
    private static final Book TEST_BOOK = new Book("title", "author", "edition", "isbn", 2016);
  
    private ModelMap modelMap;
  
    @BeforeEach
    public void setUp() throws Exception {
      bookService = mock(BookService.class);
      myBorrowedBooksController = new MyBorrowedBooksController(bookService);
      modelMap = new ModelMap();
    }
  
    @Test
    public void shouldNavigateToBookList() throws Exception {
      String url = myBorrowedBooksController.setupForm(modelMap);
      assertThat(url, is("myBorrowedBooksForm"));
    }
  
    // @Test
    // public void shouldContainBooks() throws Exception {
    //   List<Book> bookList = new ArrayList();
    //   bookList.add(TEST_BOOK);
    //   when(bookService.findAllBooks()).thenReturn(bookList);
    //   myBorrowedBooksController.setupForm(modelMap);
    //   List<Book> actualBooks = (List<Book>) modelMap.get("books");
    //   assertThat(actualBooks, is(bookList));
    // }
}
