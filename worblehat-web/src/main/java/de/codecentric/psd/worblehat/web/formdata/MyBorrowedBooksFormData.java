package de.codecentric.psd.worblehat.web.formdata;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

public class MyBorrowedBooksFormData {
  @NotEmpty(message = "{empty.borrowCmd.email}")
  @Email(message = "{notvalid.borrowCmd.email}")
  private String email;

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }
}
