package simplemvc;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Controller implements ActionListener {
    private final Model model;
    private final View view;

    public Controller(Model model, View view) {
        this.model = model;
        this.view = view;
        this.view.setModel(model);
        this.view.getAdd().addActionListener(this);
        this.view.getRemove().addActionListener(this);
    }

    public void start() {
        view.show();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == view.getAdd()) {
            onAddButtonClicked();
        } else if (e.getSource() == view.getRemove()) {
            onRemoveButtonClicked();
        }
    }

    public void onAddButtonClicked() {
        model.addDatum(view.getField().getText());
    }

    public void onRemoveButtonClicked() {
        model.removeDatum(view.getList().getSelectedValue());
    }
}

