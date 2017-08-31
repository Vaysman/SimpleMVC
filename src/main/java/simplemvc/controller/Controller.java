package simplemvc.controller;

import simplemvc.Model;
import simplemvc.view.View;

public interface Controller {
    void addAction(String text);

    void removeAction(String text);

    void setModel(Model model);

    void setView(View view);

    void start();
}

