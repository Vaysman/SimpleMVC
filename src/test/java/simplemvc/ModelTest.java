package simplemvc;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import javax.swing.event.ListDataEvent;
import javax.swing.event.ListDataListener;

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
    private ListDataListener listener;

    @Test
    public void addDatum_emptyModel_addsDatumToModel() throws Exception {
        Model model = new Model();

        model.addDatum(ANY_STRING);

        assertThat(model.getSize(), is(1));
        assertThat(model.getElementAt(ANY_INDEX), is(ANY_STRING));
    }

    @Test
    public void addDatum_emptyModel_notifiesListener() throws Exception {
        Model model = new Model();
        model.addListDataListener(listener);

        ArgumentCaptor<ListDataEvent> listDataEventCaptor = ArgumentCaptor.forClass(ListDataEvent.class);

        model.addDatum(ANY_STRING);

        verify(listener).intervalAdded(listDataEventCaptor.capture());
        assertThat(listDataEventCaptor.getValue().getSource(), is(model));
        assertThat(listDataEventCaptor.getValue().getIndex0(), is(ANY_INDEX));
        assertThat(listDataEventCaptor.getValue().getIndex1(), is(ANY_INDEX));
    }

    @Test
    public void addDatum_oneElementModel_addsDatumToModelAtTheEnd() throws Exception {
        Model model = new Model();
        init_oneElement(model, ANY_STRING);

        model.addDatum(ANOTHER_STRING);

        assertThat(model.getSize(), is(2));
        assertThat(model.getElementAt(ANY_INDEX), is(ANY_STRING));
        assertThat(model.getElementAt(1), is(ANOTHER_STRING));
    }

    @Test
    public void addDatum_oneElementModel_notifiesListener() throws Exception {
        Model model = new Model();
        init_oneElement(model, ANY_STRING);
        model.addListDataListener(listener);
        ArgumentCaptor<ListDataEvent> listDataEventCaptor = ArgumentCaptor.forClass(ListDataEvent.class);

        model.addDatum(ANOTHER_STRING);

        verify(listener).intervalAdded(listDataEventCaptor.capture());
        assertThat(listDataEventCaptor.getValue().getSource(), is(model));
        assertThat(listDataEventCaptor.getValue().getIndex0(), is(1));
        assertThat(listDataEventCaptor.getValue().getIndex1(), is(1));
    }

    @Test
    public void removeDatum_removeAnyElementFromEmptyModel_doesNothing() throws Exception {
        Model model = new Model();
        model.addListDataListener(listener);

        model.removeDatum(ANY_STRING);

        verifyZeroInteractions(listener);
        assertThat(model.getSize(), is(ANY_INDEX));
    }

    @Test
    public void removeDatum_removeExistentElementFromModel_removesElement() throws Exception {
        Model model = new Model();
        init_oneElement(model, ANY_STRING);

        model.removeDatum(ANY_STRING);

        assertThat(model.getSize(), is(ANY_INDEX));
    }

    @Test
    public void removeDatum_removeExistentElementFromModel_notifiesListener() throws Exception {
        Model model = new Model();
        init_oneElement(model, ANY_STRING);
        model.addListDataListener(listener);
        ArgumentCaptor<ListDataEvent> listDataEventCaptor = ArgumentCaptor.forClass(ListDataEvent.class);

        model.removeDatum(ANY_STRING);

        verify(listener).intervalRemoved(listDataEventCaptor.capture());
        assertThat(listDataEventCaptor.getValue().getSource(), is(model));
        assertThat(listDataEventCaptor.getValue().getIndex0(), is(ANY_INDEX));
        assertThat(listDataEventCaptor.getValue().getIndex1(), is(ANY_INDEX));
    }


    @Test
    public void removeDatum_removeNonExistentElementFromModel_doesNothing() throws Exception {
        Model model = new Model();
        init_oneElement(model, ANY_STRING);
        model.addListDataListener(listener);

        model.removeDatum(NON_EXISTENT_ELEMENT);

        verifyZeroInteractions(listener);
        assertThat(model.getSize(), is(1));
    }

    @Test
    public void getSize_emptyModel_returnsZero() throws Exception {
        Model model = new Model();

        assertThat(model.getSize(), is(ANY_INDEX));
    }

    @Test
    public void getSize_oneElementModel_returnsOne() throws Exception {
        Model model = new Model();
        init_oneElement(model, ANY_STRING);

        assertThat(model.getSize(), is(1));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void getElementAt_getElementFromEmptyModel_throwsIndexOutOfBoundsException() throws Exception {
        new Model().getElementAt(ANY_INDEX);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void getElementAt_getSecondElementFromOneElementModel_throwsIndexOutOfBoundsException() throws Exception {
        Model model = new Model();
        init_oneElement(model, ANY_STRING);

        model.getElementAt(1);
    }

    @Test()
    public void getElementAt_getFirstElementFromOneElementModel_returnsFirstElement() throws Exception {
        Model model = new Model();
        init_oneElement(model, ANY_STRING);

        assertThat(model.getElementAt(0), is(ANY_STRING));
    }

    private Model init_oneElement(Model model, String element) {
        model.addDatum(element);
        return model;
    }
}