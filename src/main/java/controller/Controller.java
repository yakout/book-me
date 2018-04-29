package controller;

public class Controller {
    private static Controller controller;

    public static synchronized Controller getInstace() {
        if (controller == null) {
            controller = new Controller();
        }
        return controller;
    }

    private Controller () {

    }
}
