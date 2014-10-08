package by.bkg.stopwatch.mvc.view.factory;

import by.bkg.stopwatch.mvc.view.panel.RegisteredPersonsPanel;
import by.bkg.stopwatch.mvc.view.panel.StopWatchPanel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.swing.*;
import java.awt.event.ActionListener;

/**
 * Creates dialogs and other components.
 *
 * @author Alexey Baryshnev
 */
@Component
public final class ComponentFactory {

    @Autowired
    private StopWatchPanel stopWatchPanel;

    @Autowired
    private RegisteredPersonsPanel registeredPersonsPanel;

    private ComponentFactory() {
    }

    public StopWatchPanel createStopWatchComponent() {
        stopWatchPanel.init();   // TODO ABA: refactor
        return stopWatchPanel;
    }

    public RegisteredPersonsPanel createRegisteredPersonsComponent() {
        registeredPersonsPanel.init();       // TODO ABA: refactor
        return registeredPersonsPanel;
    }

    public static JButton createBtn(String label, ActionListener listener) {
        JButton btn = new JButton(label);
        btn.addActionListener(listener);
        return btn;
    }
}
