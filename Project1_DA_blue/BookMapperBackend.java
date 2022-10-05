import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class BookMapperBackend implements IBookMapperBackend{

    //IterableMapADT
    HashtableMap<Object, IBook> hashtableMap = new HashtableMap<>();

    protected String authorFilter;

    @Override
    public void addBook(IBook book) {
        hashtableMap.put(book.getISBN13(), book);
    }

    @Override
    public int getNumberOfBooks() {
        return hashtableMap.size();
    }

    @Override
    public void setAuthorFilter(String filterBy) {
        this.authorFilter = filterBy;
    }

    @Override
    public String getAuthorFilter() {
        return this.authorFilter;
    }

    @Override
    public void resetAuthorFilter() {
        this.authorFilter = null;
    }

    @Override
    public List<IBook> searchByTitleWord(String word) {
        List<IBook> lb = new ArrayList<IBook>();
        Iterator iterator = hashtableMap.iterator();
        while(iterator.hasNext()){
            IBook book = (IBook) iterator.next();
            if(getAuthorFilter()==null){
                if(book.getTitle().contains(word)){
                    lb.add(book);
                }
            }else {
                if (book.getTitle().contains(word)&& book.getAuthors().contains(authorFilter)){
                    lb.add(book);
                }
            }

        }
        return lb;
    }

    @Override
    public IBook getByISBN(String ISBN) {
        return hashtableMap.get(ISBN);
    }
}
