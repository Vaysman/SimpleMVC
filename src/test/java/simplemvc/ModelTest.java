package simplemvc;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Observer;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyZeroInteractions;

@RunWith(MockitoJUnitRunner.class)
public class ModelTest {
    public static final String ANY_STRING = "any string";
    public static final String NON_EXISTENT_ELEMENT = "non existent";
    public static final String ANOTHER_STRING = "another string";
    public static final int ANY_INDEX = 0;

    @Mock
    private Observer observer;

    @Test
    public void addDatum_emptyModel_addsDatumToModel() throws Exception {
        Model model = new Model();

        model.addDatum(ANY_STRING);

        assertThat(model.size(), is(1));
        assertThat(model.get(ANY_INDEX), is(ANY_STRING));
    }

    @Test
    public void addDatum_emptyModel_notifiesListener() throws Exception {
        Model model = new Model();
        model.addObserver(observer);

        model.addDatum(ANY_STRING);

        verify(observer).update(model, null);
    }

    @Test
    public void addDatum_oneElementModel_addsDatumToModelAtTheEnd() throws Exception {
        Model model = new Model();
        init_oneElement(model, ANY_STRING);

        model.addDatum(ANOTHER_STRING);

        assertThat(model.size(), is(2));
        assertThat(model.get(ANY_INDEX), is(ANY_STRING));
        assertThat(model.get(1), is(ANOTHER_STRING));
    }

    @Test
    public void addDatum_oneElementModel_notifiesListener() throws Exception {
        Model model = new Model();
        init_oneElement(model, ANY_STRING);
        model.addObserver(observer);

        model.addDatum(ANOTHER_STRING);

        verify(observer).update(model, null);
    }

    @Test
    public void removeDatum_removeAnyElementFromEmptyModel_doesNothing() throws Exception {
        Model model = new Model();
        model.addObserver(observer);

        model.removeDatum(ANY_STRING);

        verifyZeroInteractions(observer);
        assertThat(model.size(), is(ANY_INDEX));
    }

    @Test
    public void removeDatum_removeExistentElementFromModel_removesElement() throws Exception {
        Model model = new Model();
        init_oneElement(model, ANY_STRING);

        model.removeDatum(ANY_STRING);

        assertThat(model.size(), is(ANY_INDEX));
    }

    @Test
    public void removeDatum_removeExistentElementFromModel_notifiesListener() throws Exception {
        Model model = new Model();
        init_oneElement(model, ANY_STRING);
        model.addObserver(observer);

        model.removeDatum(ANY_STRING);

        verify(observer).update(model, null);
    }


    @Test
    public void removeDatum_removeNonExistentElementFromModel_doesNothing() throws Exception {
        Model model = new Model();
        init_oneElement(model, ANY_STRING);
        model.addObserver(observer);

        model.removeDatum(NON_EXISTENT_ELEMENT);

        verifyZeroInteractions(observer);
        assertThat(model.size(), is(1));
    }

    @Test
    public void size_emptyModel_returnsZero() throws Exception {
        Model model = new Model();

        assertThat(model.size(), is(ANY_INDEX));
    }

    @Test
    public void size_oneElementModel_returnsOne() throws Exception {
        Model model = new Model();
        init_oneElement(model, ANY_STRING);

        assertThat(model.size(), is(1));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void get_getElementFromEmptyModel_throwsIndexOutOfBoundsException() throws Exception {
        new Model().get(ANY_INDEX);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void get_getSecondElementFromOneElementModel_throwsIndexOutOfBoundsException() throws Exception {
        Model model = new Model();
        init_oneElement(model, ANY_STRING);

        model.get(1);
    }

    @Test()
    public void get_getFirstElementFromOneElementModel_returnsFirstElement() throws Exception {
        Model model = new Model();
        init_oneElement(model, ANY_STRING);

        assertThat(model.get(0), is(ANY_STRING));
    }

    private Model init_oneElement(Model model, String element) {
        model.addDatum(element);
        return model;
    }
}