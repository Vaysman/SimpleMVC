package simplemvc;

public class Main {
    private Model model;
    private View view;
    private Controller controller;

    public static void main(String[] args) {
        Main main = new Main(new Model(), new View(), new Controller());

        main.start();
    }

    public Main(Model model, View view, Controller controller) {
        this.model = model;
        this.view = view;
        this.controller = controller;
        init();
    }

    public void start() {
        controller.start();
    }

    private void init() {
        view.setModel(model);
        view.setController(controller);
        controller.setView(view);
        controller.setModel(model);
    }
}
