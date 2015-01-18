package simplemvc;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class DefaultControllerTest {
    public static final String ANY_STRING = "any string";

    @Mock
    private Model model;

    @InjectMocks
    private DefaultController controller;

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