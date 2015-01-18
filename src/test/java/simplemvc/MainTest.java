package simplemvc;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class MainTest {
    @Mock
    private Model model;

    @Mock
    private View view;

    @Mock
    private Controller controller;

    @InjectMocks
    private Main main;


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