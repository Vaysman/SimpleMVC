package simplemvc.controller;

import simplemvc.Model;
import simplemvc.View;

public abstract class AbstractController implements Controller {
    protected View view;
    protected Model model;

    @Override
    public void setView(View view) {
        this.view = view;
    }

    @Override
    public void setModel(Model model) {
        this.model = model;
    }

    @Override
    public void start() {
        view.show();
    }
}
