package simplemvc;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

public class Model extends Observable {
    private List<String> data;

    public Model() {
        data = new ArrayList<>();
    }

    public void addDatum(String datum) {
        data.add(datum);
        setChanged();
        notifyObservers();
    }

    public void removeDatum(String datum) {
        int indexOfElementToRemove = data.indexOf(datum);
        if (indexOfElementToRemove != -1) {
            data.remove(indexOfElementToRemove);
            setChanged();
            notifyObservers();
        }
    }

    public int size() {
        return data.size();
    }

    public String get(int index) {
        return data.get(index);
    }
}
