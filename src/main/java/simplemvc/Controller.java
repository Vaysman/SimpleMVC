package simplemvc;

public class Controller {
    private View view;
    private Model model;

    public void setView(View view) {
        this.view = view;
    }

    public void setModel(Model model) {
        this.model = model;
    }

    public void start() {
        view.show();
    }

    public void addAction(String text) {
        model.addDatum(text);
    }

    public void removeAction(String text) {
        model.removeDatum(text);
    }
}

