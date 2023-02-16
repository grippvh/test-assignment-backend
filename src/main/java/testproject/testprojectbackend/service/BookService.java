package testproject.testprojectbackend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import testproject.testprojectbackend.model.Book;
import testproject.testprojectbackend.repository.BookRepository;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Service
public class BookService {

    private final static String errorMessage = "Error: constraints were broken, " +
            "please check your input being not null and release date being in the format yyyy-MM-dd";
    private final static String bookNotFoundMessage = "Book was not found!";

    @Autowired
    private BookRepository bookRepository;


    public String createBook(@Valid Book book){
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
            return errorMessage;
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
                bookRepository.save(bookRepository.findById(book.getId()).get());
                return "Book was updated!";
            } catch (Exception e){
                Book book1 = bookRepository.findById(book.getId()).get();
                //System.out.println("id: " + book1.getId());
                System.out.println("isbn: " + book1.getIsbn());
                System.out.println("name: " + book1.getName());
                System.out.println("author: " + book1.getAuthor());
                System.out.println("date: " + book1.getReleaseDate());

                return errorMessage;
            }
        }
        else {
            return bookNotFoundMessage;
        }
    }

    @Transactional
    public String deleteBook(long id){
        if (bookRepository.findById(id).isPresent()){
            try {
                bookRepository.delete(bookRepository.findById(id).get()); // can be handled better with optional
                return "Book was deleted successfully!";
            }
            catch (Exception e){
                return errorMessage;
            }
        } else {
            return bookNotFoundMessage;
        }
    }
}

