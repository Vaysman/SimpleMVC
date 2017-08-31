package simplemvc.view;

import simplemvc.Model;
import simplemvc.controller.Controller;

public interface View {
    void dispose();

    void setController(Controller controller);

    void setModel(Model model);

    void show();
}
