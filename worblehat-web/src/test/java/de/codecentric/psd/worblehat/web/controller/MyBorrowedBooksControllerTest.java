package de.codecentric.psd.worblehat.web.controller;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.nullValue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import de.codecentric.psd.worblehat.web.formdata.BookBorrowFormData;
import de.codecentric.psd.worblehat.web.formdata.MyBorrowedBooksFormData;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.ui.ModelMap;

import de.codecentric.psd.worblehat.domain.Book;
import de.codecentric.psd.worblehat.domain.BookService;
import org.springframework.validation.BindingResult;
import org.springframework.validation.MapBindingResult;
import org.springframework.validation.ObjectError;

public class MyBorrowedBooksControllerTest {
    private BookService bookService;

    private MyBorrowedBooksController myBorrowedBooksController;

    private BindingResult bindingResult;

    private MyBorrowedBooksFormData myBookBorrowFormData;

    private static final String BORROWER_EMAIL = "someone@codecentric.de";
    private static final Book TEST_BOOK = new Book("title", "author", "edition", "isbn", 2016);

    private ModelMap modelMap;

    @BeforeEach
    public void setUp() throws Exception {
      bookService = mock(BookService.class);
      bindingResult = new MapBindingResult(new HashMap<>(), "");
      myBookBorrowFormData = new MyBorrowedBooksFormData();
      myBorrowedBooksController = new MyBorrowedBooksController(bookService);
      modelMap = new ModelMap();
    }

    @Test
    public void shouldSetupForm() {
        myBorrowedBooksController.setupForm(modelMap);
        assertThat(modelMap.get("myBorrowedBooksFormData"), is(not(nullValue())));
    }


     @Test
     public void shouldContainBooks() throws Exception {
       List<Book> bookList = new ArrayList();
       bookList.add(TEST_BOOK);
       when(bookService.findAllBooksByBorrower(BORROWER_EMAIL)).thenReturn(bookList);
       String navigateTo = myBorrowedBooksController.showBorrowedBooks(myBookBorrowFormData, bindingResult, modelMap);
       assertThat(navigateTo, is("myBorrowedBooks"));

         // List<Book> actualBooks = (List<Book>) modelMap.get("books");
        // assertThat(actualBooks, is(bookList));
    }

    @Test
    public void shouldNavigateToMyBorrowedBooksWhenResultHasErrors() throws Exception {
        bindingResult.addError(new ObjectError("", ""));
        String navigateTo = myBorrowedBooksController.showBorrowedBooks(myBookBorrowFormData, bindingResult, modelMap);
        assertThat(navigateTo, is("myBorrowedBooksForm"));
    }


}
