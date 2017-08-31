package simplemvc;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import simplemvc.controller.Controller;
import simplemvc.controller.DefaultController;
import simplemvc.controller.DoNothingController;
import simplemvc.view.SwingView;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class MainTest {
    public static final String[] EMPTY_ARRAY_OF_STRING = new String[]{};
    public static final String[] ARRAY_OF_STRINGS_WITH_STING_CONTAINS_NOTHING_KEYWORD = new String[]{"-nothing"};

    @Mock
    private Model model;

    @Mock
    private SwingView view;

    @Mock
    private Controller controller;

    @InjectMocks
    private Main main;

    @Test
    public void makeController_argsWithoutNothingKeys_returnsDefaultController() {
        Controller controller = Main.makeController(EMPTY_ARRAY_OF_STRING);
        assertThat(controller, is(instanceOf(DefaultController.class)));
    }

    @Test
    public void makeController_argsWithNothingKeys_returnsDoNothingController() {
        Controller controller = Main.makeController(ARRAY_OF_STRINGS_WITH_STING_CONTAINS_NOTHING_KEYWORD);
        assertThat(controller, is(instanceOf(DoNothingController.class)));
    }

    @Test
    public void start_default_callsStart() throws Exception {
        main.start();

        verify(controller).start();
    }

    @Test
    public void Main_default_bindModelViewController() {
        verify(controller).setModel(model);
        verify(controller).setView(view);
        verify(view).setModel(model);
        verify(view).setController(controller);
    }
}
