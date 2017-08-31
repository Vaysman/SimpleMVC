package simplemvc;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import simplemvc.controller.DoNothingController;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyZeroInteractions;

@RunWith(MockitoJUnitRunner.class)
public class DoNothingControllerTest {
    public static final String ANY_STRING = "any string";

    @Mock
    private Model model;

    @InjectMocks
    private DoNothingController controller;

    @Test
    public void addAction_default_callsAdd() throws Exception {
        String aString = ANY_STRING;

        controller.addAction(aString);

        verifyZeroInteractions(model);
    }

    @Test
    public void removeAction_default_callsRemove() throws Exception {
        String aString = ANY_STRING;

        controller.removeAction(aString);

        verifyZeroInteractions(model);
    }
}
