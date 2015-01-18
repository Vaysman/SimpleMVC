package simplemvc;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class ControllerTest {
    public static final String ANY_STRING = "any string";

    @Mock
    private Model model;

    @Mock
    private View view;

    @InjectMocks
    private Controller controller;

    @Test
    public void start_default_callsShow() throws Exception {
        controller.start();

        verify(view).show();
    }

    @Test
    public void addAction_default_callsAdd() throws Exception {
        String aString = ANY_STRING;

        controller.addAction(aString);

        verify(model).addDatum(aString);
    }

    @Test
    public void removeAction_default_callsRemove() throws Exception {
        String aString = ANY_STRING;

        controller.removeAction(aString);

        verify(model).removeDatum(aString);
    }
}