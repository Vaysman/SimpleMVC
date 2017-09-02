package simplemvc;

import simplemvc.controller.Controller;
import simplemvc.controller.DefaultController;
import simplemvc.controller.DoNothingController;
import simplemvc.view.SwingView;
import simplemvc.view.View;

import java.util.Objects;

public class Main {
    private Model model;
    private View view;
    private Controller controller;

    private Main(Model model, View view, Controller controller) {
        this.model = model;
        this.view = view;
        this.controller = controller;
        init();
    }

    public static void main(String[] args) {
        Controller controller = makeController(args);
        Main main = new Main(new Model(), new SwingView(), controller);

        main.start();
    }

    static Controller makeController(String... args) {
        Controller controller = new DefaultController();
        for (String arg : args) {
            if (Objects.equals(arg, "-nothing")) {
                controller = new DoNothingController();
            }
        }
        return controller;
    }

    void start() {
        controller.start();
    }

    private void init() {
        view.setModel(model);
        view.setController(controller);
        controller.setView(view);
        controller.setModel(model);
    }
}
