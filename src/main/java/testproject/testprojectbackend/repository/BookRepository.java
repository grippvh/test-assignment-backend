package testproject.testprojectbackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import testproject.testprojectbackend.model.Book;

public interface BookRepository extends JpaRepository<Book, Long> {
    //public boolean checkIfBookExistsByISBN(long isbn);

    @Query("select max(book.id) from Book book")
    public Integer findMaxId();
}
