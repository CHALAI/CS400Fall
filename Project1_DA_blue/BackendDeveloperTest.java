import java.util.List;

public class BackendDeveloperTest {

    static IBookMapperBackend backend = new BookMapperBackend();

    /**
     * Add two books using BookMapperBackend, see if the size of IterableMap added.
     * @return true if add is successful
     *         false if add is not successful
     */
    public static boolean test1() {
        backend.addBook(new IBook() {
            @Override
            public String getTitle() {
                return "Title 1";
            }

            @Override
            public String getAuthors() {
                return "Author 1";
            }

            @Override
            public String getISBN13() {
                return "1234567890123";
            }
        });
        backend.addBook(new IBook() {
            @Override
            public String getTitle() {
                return "Title 2";
            }

            @Override
            public String getAuthors() {
                return "Author 2";
            }

            @Override
            public String getISBN13() {
                return "1231231231231";
            }});
        if (backend.getNumberOfBooks()==2){
            return true;
        }
        return false;
    }

    /**
     * Test if getAuthorFilter, setAuthorFilter, and resetAuthorFilter works
     *
     * @return true if these three functions work
     *         false if they don't work.
     */
    public static boolean test2() {
        if(backend.getAuthorFilter()==null){
            backend.setAuthorFilter("Tianchen");
            if(backend.getAuthorFilter()=="Tianchen"){
                backend.resetAuthorFilter();
                if(backend.getAuthorFilter()==null){
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Check if searchByTitleWord function works. Search Title word "Title"
     * See if get correct ISBN.
     *
     * @return true if searchByTitleWord function works.
     *         false if searchByTitleWord function doesn't work.
     */
    public static boolean test3() {
        List<IBook> lb = backend.searchByTitleWord("Title");
        if(lb.get(0).getISBN13()=="1234567890123"&&lb.get(1).getISBN13()=="1231231231231"){
            return true;
        }
        return false;
    }


    /**
     * Check searchByTitleWord after setAuthorFilter.
     *
     * @return true if the filter filtered Books' Author without "123"
     *         false if not.
     */
    public static boolean test4() {
        backend.setAuthorFilter("123");
        List<IBook> lb = backend.searchByTitleWord("Title");
        if(lb.size()!=0){
            return false;
        }
        return true;
    }

    /**
     * Check searchByTitleWord after setAuthorFilter to "2".
     * Should return the second book.
     *
     * @return true if searchByTitleWord function works with authorFilter.
     *         false if not.
     */
    public static boolean test5() {
        backend.setAuthorFilter("2");
        List<IBook> lb = backend.searchByTitleWord("Title");
        if(lb.get(0).getISBN13()=="1231231231231"){
            return true;
        }
        return false;
    }

    public static void main(String[] args) {
        System.out.println(test1());
        System.out.println(test2());
        System.out.println(test3());
        System.out.println(test4());
        System.out.println(test5());
    }
}
