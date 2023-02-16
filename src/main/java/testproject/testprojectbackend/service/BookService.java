package testproject.testprojectbackend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import testproject.testprojectbackend.model.Book;
import testproject.testprojectbackend.repository.BookRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;


    public String createBook(Book book){
        System.out.println(book.getIsbn());
        try {
            if (!checkIfBookExistsByISBN(book.getIsbn())){
                bookRepository.save(book);
                return "Book " + book.getName() + " stored successfully!";
            }
            else {
                return "Book already exists!";
            }
        } catch (Exception e){ // better custom exceptions
            throw e;
        }
    }
    private boolean checkIfBookExistsByISBN(long isbn){
        return bookRepository.findAll().stream().filter(book -> book.getIsbn() == isbn).findFirst().isPresent();
    }

    public List<Book> getAllBooks(){
        return bookRepository.findAll();
    }

    public Optional<Book> getBookByID(long id){
        return bookRepository.findById(id);
    }


    public String updateBook(Book book){
        if (checkIfBookExistsByISBN(book.getIsbn())){
            try {
                bookRepository.findById(book.getId()).get().setAuthor(book.getAuthor());
                bookRepository.findById(book.getId()).get().setIsbn(book.getIsbn());
                bookRepository.findById(book.getId()).get().setName(book.getName());
                bookRepository.findById(book.getId()).get().setReleaseDate(book.getReleaseDate());
                // TODO: save or not?
                return "Book was updated!";
            } catch (Exception e){
                throw e;
            }
        }
        else {
            return "Book was not found!";
        }
    }

    @Transactional
    public String deleteBook(long id){
        if (bookRepository.findById(id).isPresent()){
            try {
                bookRepository.delete(bookRepository.findById(id).get()); //TODO: can be handled better with optional
                return "Book was deleted successfully!";
            }
            catch (Exception e){
                throw e;
            }
        } else {
            return "Book to be deleted was not found!";
        }
    }
}

