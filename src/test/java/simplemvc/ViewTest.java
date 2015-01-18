package simplemvc;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.netbeans.jemmy.operators.JButtonOperator;
import org.netbeans.jemmy.operators.JFrameOperator;
import org.netbeans.jemmy.operators.JListOperator;
import org.netbeans.jemmy.operators.JTextFieldOperator;

import javax.swing.*;

import static org.junit.Assert.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ViewTest {
    public static final String ANY_STRING = "any string";
    public static final String EMPTY_STRING = "";
    public static final String ADD_BUTTON = "Add";
    public static final String REMOVE_BUTTON = "Remove";

    @Mock
    private Model model;

    @Mock
    private Controller controller;

    @InjectMocks
    private View view;


    @Before
    public void showView() {
        view.show();
    }

    @After
    public void disposeView() {
        view.dispose();
    }

    @Test
    public void when_add_button_clicked_then_calls_addAction() {
        JFrameOperator root = getRootFrame();
        setTextInField(root, ANY_STRING);

        pushButton(root, ADD_BUTTON);

        verify(controller).addAction(ANY_STRING);
    }

    @Test
    public void when_remove_button_clicked_then_calls_removeAction() {
        init_ListWithOneItem();
        JFrameOperator root = getRootFrame();
        new JListOperator(root, 0).selectItem(0);

        pushButton(root, REMOVE_BUTTON);

        verify(controller).removeAction(ANY_STRING);
    }

    @Test
    public void when_item_in_list_is_select_then_remove_button_is_enabled() {
        init_ListWithOneItem();
        JFrameOperator root = getRootFrame();

        new JListOperator(root, 0).selectItem(0);

        JButtonOperator removeButton = findButton(root, REMOVE_BUTTON);
        assertTrue("Remove button must be enabled", removeButton.isEnabled());
    }

    @Test
    public void when_no_item_in_list_is_select_then_remove_button_is_disabled() {
        init_ListWithOneItem();
        JFrameOperator root = getRootFrame();

        new JListOperator(root, 0).clearSelection();

        JButtonOperator removeButton = findButton(root, REMOVE_BUTTON);
        assertFalse("Remove button must be disabled", removeButton.isEnabled());
    }

    @Test
    public void when_text_field_is_empty_then_add_button_is_disabled() {
        JFrameOperator root = getRootFrame();

        setTextInField(root, EMPTY_STRING);

        JButtonOperator addButton = findButton(root, ADD_BUTTON);
        assertFalse("Add button must be disabled", addButton.isEnabled());
    }

    @Test
    public void when_text_field_is_not_empty_then_add_button_is_enabled() {
        JFrameOperator root = getRootFrame();

        setTextInField(root, ANY_STRING);

        JButtonOperator addButton = findButton(root, ADD_BUTTON);
        assertTrue("Add button must be enabled", addButton.isEnabled());
    }

    @Test
    public void when_frame_is_created_then_default_close_operation_is_exit() {
        JFrameOperator root = getRootFrame();

        assertEquals(WindowConstants.EXIT_ON_CLOSE, root.getDefaultCloseOperation());
    }

    private JButtonOperator findButton(JFrameOperator root, String buttonName) {
        return new JButtonOperator(root, buttonName);
    }

    private JFrameOperator getRootFrame() {
        return new JFrameOperator("List");
    }

    private void init_ListWithOneItem() {
        when(model.getSize()).thenReturn(1);
        when(model.getElementAt(0)).thenReturn(ANY_STRING);
        view.setModel(model);
    }

    private void pushButton(JFrameOperator root, String buttonName) {
        findButton(root, buttonName).push();
    }

    private void setTextInField(JFrameOperator root, String text) {
        new JTextFieldOperator(root, 0).setText(text);
    }
}