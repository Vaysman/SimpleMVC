package simplemvc;

public class Main {
    private Model model;
    private View view;
    private Controller controller;

    public Main(Model model, View view, Controller controller) {
        this.model = model;
        this.view = view;
        this.controller = controller;
        init();
    }

    public static void main(String[] args) {
        Controller controller = makeController(args);
        Main main = new Main(new Model(), new View(), controller);

        main.start();
    }

    public static Controller makeController(String... args) {
        Controller controller = new DefaultController();
        for (String arg : args) {
            if(arg.equals("-nothing")) {
                controller = new DoNothingController();
            }
        }
        return controller;
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
