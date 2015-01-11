package simplemvc;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class ControllerTest {
    @Mock
    private Model model;

    @Mock
    private View view;

    @Test
    public void start_default_callsShow() throws Exception {
        Controller controller = make_controller();

        controller.start();

        verify(view).show();
    }

    @Test
    public void addAction_default_callsAdd() throws Exception {
        Controller controller = make_controller();
        String aString = "anyString";

        controller.addAction(aString);

        verify(model).addDatum(aString);
    }

    @Test
    public void removeAction_default_callsRemove() throws Exception {
        Controller controller = make_controller();
        String aString = "anyString";

        controller.removeAction(aString);

        verify(model).removeDatum(aString);
    }

    private Controller make_controller() {
        Controller controller = new Controller();
        controller.setModel(model);
        controller.setView(view);
        return controller;
    }
}