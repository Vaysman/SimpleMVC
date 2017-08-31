package simplemvc;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import simplemvc.controller.AbstractController;

import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class AbstractControllerTest {
    @Mock
    private View view;

    @Test
    public void start_default_callsShow() throws Exception {
        AbstractController controller = new TestingAbstractController();
        controller.setView(view);

        controller.start();

        verify(view).show();
    }

    private class TestingAbstractController extends AbstractController {
        @Override
        public void addAction(String text) {
        }

        @Override
        public void removeAction(String text) {
        }
    }
}
