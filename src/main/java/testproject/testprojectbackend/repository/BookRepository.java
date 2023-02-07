package testproject.testprojectbackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import testproject.testprojectbackend.model.Book;

public interface BookRepository extends JpaRepository<Book, Long> {
}
