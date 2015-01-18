package simplemvc;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import javax.swing.*;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class ControllerTest {
    public static final String ANY_STRING = "any string";

    @Mock
    private Model model;

    @Mock
    private View view;

    private Controller controller;

    @SuppressWarnings("unchecked")
    @Before
    public void setUp() {
        when(view.getAdd()).thenReturn(mock(JButton.class));
        when(view.getRemove()).thenReturn(mock(JButton.class));
        when(view.getField()).thenReturn(mock(JTextField.class));
        when(view.getList()).thenReturn(mock(JList.class));
        controller = new Controller(model, view);
    }

    @Test
    public void start_default_callsShow() throws Exception {
        controller.start();

        verify(view).show();
    }

    @Test
    public void onAddButtonClicked_default_callsAdd() throws Exception {
        String aString = ANY_STRING;

        when(view.getField().getText()).thenReturn(aString);
        controller.onAddButtonClicked();

        verify(model).addDatum(aString);
    }

    @Test
    public void onRemoveButtonClicked_default_callsRemove() throws Exception {
        String aString = ANY_STRING;

        when(view.getList().getSelectedValue()).thenReturn(aString);
        controller.onRemoveButtonClicked();

        verify(model).removeDatum(aString);
    }
}