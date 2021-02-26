package de.codecentric.psd.worblehat.web.validation;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;

import javax.validation.ConstraintValidatorContext;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class NumericConstraintValidatorTest {

  private NumericConstraintValidator numericConstraintValidator;

  ConstraintValidatorContext constraintValidatorContext;

  @BeforeEach
  public void setUp() throws Exception {
    numericConstraintValidator = new NumericConstraintValidator();
    constraintValidatorContext = mock(ConstraintValidatorContext.class);
  }

  @Test
  public void shouldReturnTrueIfBlank() throws Exception {
    boolean actual = numericConstraintValidator.isValid("", constraintValidatorContext);
    assertTrue(actual);
  }

  @Test
  public void shouldReturnTrueIfNumeric() throws Exception {
    boolean actual = numericConstraintValidator.isValid("1", constraintValidatorContext);
    assertTrue(actual);
  }

  @Test
  public void shouldReturnFalsIfNotNumeric() throws Exception {
    boolean actual = numericConstraintValidator.isValid("x", constraintValidatorContext);
    assertFalse(actual);
  }
}
