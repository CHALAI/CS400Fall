// --== CS400 Project One File Header ==--
// Name: <Tianchen Guan>
// CSL Username: <tianchen>
// Email: <tguan9@wisc.edu>
// Lecture #: <003 @2:25pm>
// Notes to Grader: <Hi! Have a nice day!>


import java.util.*;


public class HashtableMap<KeyType, ValueType> implements MapADT<KeyType, ValueType> {


    class HashElements {
        public KeyType key;
        public ValueType value;

        public KeyType getKey() {
            return key;
        }

        public void setKey(KeyType key) {
            this.key = key;
        }

        public ValueType getValue() {
            return value;
        }

        public void setValue(ValueType value) {
            this.value = value;
        }
    }

    private int capacity;
    private int size = 0;
    LinkedList<HashElements>[] hashList;

    public HashtableMap() {
        this(15);
    }

    public HashtableMap(int capacity) {
        this.capacity = capacity;
        hashList = new LinkedList[capacity];
        for (int i = 0; i < capacity; i++) {
            hashList[i] = new LinkedList<>();
        }
    }

    @Override
    public boolean put(KeyType key, ValueType value) {
        if (containsKey(key)) {
            return false;
        }
        int position = hashFun(key);
        HashElements hashElements = new HashElements();
        hashElements.setKey(key);
        hashElements.setValue(value);
        // If add success, size add one and check if factor is greater or equal to 70%
        if (hashList[position].add(hashElements)) {
            size++;
            if ((float) size / capacity >= 0.7) {
                resize();
            }
            return true;
        }
        return false;
    }

    @Override
    public ValueType get(KeyType key) throws NoSuchElementException {
        int position = hashFun(key);
        for (int i = 0; i < hashList[position].size(); i++) {
            if (hashList[position].get(i).getKey().equals(key)) {
                return hashList[position].get(i).getValue();
            }
        }
        throw new NoSuchElementException();
    }

    @Override
    public ValueType remove(KeyType key) {
        int position = hashFun(key);
        for (int i = 0; i < hashList[position].size(); i++) {
            if (hashList[position].get(i).getKey().equals(key)) {
                size--;
                return hashList[position].remove(i).getValue();
            }
        }
        return null;
    }

    @Override
    public boolean containsKey(KeyType key) {
        int position = hashFun(key);
        for (int i = 0; i < hashList[position].size(); i++) {
            if (hashList[position].get(i).getKey().equals(key)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void clear() {
        LinkedList<HashElements>[] newHashList = new LinkedList[capacity];
        for (int i = 0; i < 15; i++) {
            newHashList[i] = new LinkedList<>();
        }
        size = 0;
        hashList = newHashList;
    }

    /**
     * Double the capacity of the Hashtable and rehashes it.
     */
    private void resize() {
        capacity = 2 * capacity;
        LinkedList<HashElements>[] newHashList = new LinkedList[capacity];
        for (int i = 0; i < capacity; i++) {
            newHashList[i] = new LinkedList<>();
        }
        // rehash all elements stored
        for (LinkedList<HashElements> hashElements : hashList) {
            if (hashElements.size() == 0) {
                continue;
            }
            int num = hashElements.size();
            for (int j = 0; j < num; j++) {
                newHashList[Math.abs(hashElements.get(j).getKey().hashCode() % capacity)].add(hashElements.get(j));
            }
        }
        hashList = newHashList;
    }

    /**
     * Turns a given key to a hash code with the hash function within 0 - capacity.
     *
     * @return Returns a hash code within 0-capacity.
     */
    private int hashFun(KeyType key) {
        return Math.abs(key.hashCode() % capacity);
    }

    class Book1 implements IBook {
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
    }

    class Book2 implements IBook {
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
        }
    }

    public Iterator iterator(){
        return Arrays.asList(new IBook[] {new Book1(), new Book2()}).iterator();

    }
}
