import model.Model;

public class Main {

    public static void main(String[] args) {
        Model model = Model.getInstance();
        model.startConnection("jdbc:mysql://localhost:3306/library", "root", "yakout");
        
        model.executeQuery("select * from library.book;");

        model.closeConnection();
    }
}
