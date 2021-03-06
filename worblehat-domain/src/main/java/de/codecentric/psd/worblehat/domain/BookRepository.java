package de.codecentric.psd.worblehat.domain;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface BookRepository extends JpaRepository<Book, Long> {

  List<Book> findAllByOrderByTitle();

  Set<Book> findByIsbn(String isbn);

  Optional<Book> findTopByIsbn(String isbn);

  Set<Book> findByAuthor(String author);

  Set<Book> findByEdition(String edition);

  @Query(
      "SELECT bo FROM Book bo WHERE bo.borrowing.borrowerEmailAddress = :borrowerEmailAddress ORDER BY bo.borrowing.borrowDate ASC")
  List<Book> findAllByBorrower(@Param("borrowerEmailAddress") String borrowerEmailAddress);
}
