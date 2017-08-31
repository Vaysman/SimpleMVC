package simplemvc;

import simplemvc.controller.Controller;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;

public class View implements ActionListener {
    private class ModelWrapper extends AbstractListModel<String> implements Observer{
        private Model model;

        public ModelWrapper(Model model) {
            this.model = model;
            model.addObserver(this);
        }

        @Override
        public int getSize() {
            return model.size();
        }

        @Override
        public String getElementAt(int index) {
            return model.get(index);
        }

        @Override
        public void update(Observable o, Object arg) {
            fireContentsChanged(this, 0, model.size());
        }
    }

    private JFrame frame;
    private JList<String> list;
    private JButton add;
    private JButton remove;
    private JTextField field;

    private Controller controller;

    public View() {
        initComponents();
    }

    public void setModel(Model model) {
        list.setModel(new ModelWrapper(model));
    }

    public void setController(Controller controller) {
        this.controller = controller;
    }

    public void show() {
        SwingUtilities.invokeLater(() -> frame.setVisible(true));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == add) {
            controller.addAction(field.getText());
        } else if (e.getSource() == remove) {
            controller.removeAction(list.getSelectedValue());
            list.clearSelection();
        }
    }

    public void dispose() {
        frame.dispose();
    }

    private JButton createButton(String text) {
        JButton button = new JButton(text);
        button.addActionListener(this);
        button.setEnabled(false);

        return button;
    }

    private JTextField createField() {
        JTextField textField = new JTextField(15);
        textField.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                updateAddButtonState();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                updateAddButtonState();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                updateAddButtonState();
            }

            private void updateAddButtonState() {
                add.setEnabled(!field.getText().isEmpty());
            }
        });

        return textField;
    }

    private JList<String> createList() {
        JList<String> jList = new JList<>();
        jList.setPreferredSize(new Dimension(100, 100));
        jList.addListSelectionListener(e -> remove.setEnabled(!list.isSelectionEmpty()));
        return jList;
    }

    private void initComponents() {
        frame = new JFrame("List");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new FlowLayout());
        frame.setSize(new Dimension(500, 150));
        frame.setContentPane(mainPanel);

        list = createList();
        mainPanel.add(list);

        field = createField();
        mainPanel.add(field);

        add = createButton("Add");
        mainPanel.add(add);

        remove = createButton("Remove");
        mainPanel.add(remove);
    }
}

