package simplemvc;

public interface Controller {
    void setView(View view);

    void setModel(Model model);

    void start();

    void addAction(String text);

    void removeAction(String text);
}

