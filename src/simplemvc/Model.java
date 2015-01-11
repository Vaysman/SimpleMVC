package simplemvc;


import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class Model extends AbstractListModel<String> {
    private List<String> data;

    public Model() {
        data = new ArrayList<>();
    }

    public void addDatum(String datum) {
        data.add(datum);
        int addedElementIndex = data.size() - 1;
        fireIntervalAdded(this, addedElementIndex, addedElementIndex);
    }

    public void removeDatum(String datum) {
        int indexOfElementToRemove = data.indexOf(datum);
        if (indexOfElementToRemove != -1) {
            data.remove(indexOfElementToRemove);
            fireIntervalRemoved(this, indexOfElementToRemove, indexOfElementToRemove);
        }
    }

    @Override
    public int getSize() {
        return data.size();
    }

    @Override
    public String getElementAt(int index) {
        return data.get(index);
    }
}
