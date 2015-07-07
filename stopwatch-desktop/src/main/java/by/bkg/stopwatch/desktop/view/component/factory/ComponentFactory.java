package by.bkg.stopwatch.desktop.view.component.factory;

import by.bkg.stopwatch.desktop.view.component.panel.RegisteredPersonsPanel;
import by.bkg.stopwatch.desktop.view.component.panel.StopWatchPanel;
import org.springframework.stereotype.Component;

import javax.inject.Inject;
import javax.swing.*;
import java.awt.event.ActionListener;

/**
 * Creates dialogs and other components.
 *
 * @author Alexey Baryshnev
 */
@Deprecated
public final class ComponentFactory {

//    @Inject
    private StopWatchPanel stopWatchPanel;

//    @Inject
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
