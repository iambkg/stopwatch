package by.bkg.stopwatch.common.factory.view;

import by.bkg.stopwatch.common.factory.controller.ControllerFactory;
import by.bkg.stopwatch.mvc.controller.EventBus;
import by.bkg.stopwatch.mvc.view.impl.dialog.AddPersonDialog;
import by.bkg.stopwatch.mvc.view.impl.panel.RegisteredPersonsPanel;
import by.bkg.stopwatch.mvc.view.impl.panel.StopWatchPanel;

import javax.swing.*;
import java.awt.event.ActionListener;

/**
 * Creates dialogs and other components.
 *
 * @author Alexey Baryshnev
 */
public class ComponentFactory {

    private ComponentFactory() {
    }

    public static StopWatchPanel createStopWatchComponent(EventBus eventBus) {
        StopWatchPanel stopWatchPanel = new StopWatchPanel();
        stopWatchPanel.setController(ControllerFactory.getStopWatchController(eventBus, stopWatchPanel));
        stopWatchPanel.init();
        return stopWatchPanel;
    }

    public static RegisteredPersonsPanel createRegisteredPersonsComponent(EventBus eventBus) {
        RegisteredPersonsPanel registeredPersonsPanel = new RegisteredPersonsPanel();
        registeredPersonsPanel.setController(ControllerFactory.getRegisteredPersonsController(eventBus, registeredPersonsPanel));
        registeredPersonsPanel.init();
        return registeredPersonsPanel;
    }

    public static AddPersonDialog createAddPersonDialog(EventBus eventBus) {
        AddPersonDialog addPersonDialog = new AddPersonDialog(eventBus);
        return addPersonDialog;
    }

    public static JButton createBtn(String label, ActionListener listener) {
        JButton btn = new JButton(label);
        btn.addActionListener(listener);
        return btn;
    }
}
