import java.io.FileNotFoundException;
import java.util.List;
import java.util.Scanner;

/**
 * Class with main method to run the book mapper app.
 */
public class BookMapper {

    public static void main(String[] args) throws FileNotFoundException {
        IBookLoader bookLoader = new BookLoader(); // new BookLoader();

        // load the books from the data file
        List<IBook> bookList = bookLoader.loadBooks("book.csv"); // bookLoader.loadBooks("books.csv");

        // instantiate the backend
        IBookMapperBackend backend = new BookMapperBackend(); // new BookMapperBackend();
        // add all the books to the backend
        // for (IBook book : bookList) backend.addBook(book);
        for (IBook book : bookList) {
            backend.addBook(book);
        }

        // instantiate the isbn validator
        IISBNValidator isbnValidator = new ISBNValidator(); // new ISBNValidator();
        // instantiate the scanner for user input
        Scanner userInputScanner = new Scanner(System.in);
        // instantiate the front end and pass references to the scanner, backend, and isbn validator to it
        IBookMapperFrontend frontend = new BookMapperFrontend(userInputScanner, backend, isbnValidator); // new BookMapperFrontend(userInputScanner, backend, isbnValidator);

        // start the input loop of the front end
        // frontend.runCommandLoop();
        frontend.runCommandLoop();
    }

}
class BookLoader implements IBookLoader{

    @Override
    public List<IBook> loadBooks(String filepathToCSV) throws FileNotFoundException {
        return null;
    }
}

class ISBNValidator implements IISBNValidator{
    @Override
    public boolean validate(String isbn13) {
        return false;
    }
}

class BookMapperFrontend implements IBookMapperFrontend{

    public BookMapperFrontend(){

    }
    public BookMapperFrontend(Scanner s, IBookMapperBackend bb, IISBNValidator is){

    }
    @Override
    public void runCommandLoop() {

    }

    @Override
    public void displayMainMenu() {

    }

    @Override
    public void displayBooks(List<IBook> books) {

    }

    @Override
    public void isbnLookup() {

    }

    @Override
    public void titleSearch() {

    }
}
