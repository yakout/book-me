package beans;

public class Author {
    private int ISBN;
    private String name;


    // GETTERS
    public int getISBN() {
        return ISBN;
    }

    public String getName() {
        return name;
    }


    // SETTERS
    public void setISBN(int ISBN) {
        this.ISBN = ISBN;
    }

    public void setName(String name) {
        this.name = name;
    }
}
