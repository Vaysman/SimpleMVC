package simplemvc.controller;

public class DefaultController extends AbstractController {
    @Override
    public void addAction(String text) {
        model.addDatum(text);
    }

    @Override
    public void removeAction(String text) {
        model.removeDatum(text);
    }
}

