package by.bkg.stopwatch.desktop.view.component.dialog;

import by.bkg.stopwatch.desktop.view.utilities.ComponentFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.swing.*;

/**
 * <a href"mailto:alexey.baryshnev@ctco.lv">Alexey Baryshnev</a>
 */
@Component
public class EditSplitDialog extends JDialog {

    @Autowired
    private ComponentFactory componentFactory;

    private static final int MIN_WIDTH = 400;
    private static final int MIN_HEIGHT = 220;

    public void init() {
    }
}
