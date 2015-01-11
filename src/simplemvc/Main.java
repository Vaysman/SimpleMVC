package simplemvc;

public class Main {
    public static void main(String[] args) {
        Controller controller = new Controller();
        View view = new View();
        Model model = new Model();

        view.setModel(model);
        view.setController(controller);
        controller.setView(view);
        controller.setModel(model);
        controller.start();
    }
}
