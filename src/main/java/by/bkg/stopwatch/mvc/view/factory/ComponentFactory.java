package by.bkg.stopwatch.mvc.view.factory;

import by.bkg.stopwatch.mvc.controller.factory.ControllerFactory;
import by.bkg.stopwatch.mvc.controller.IEventBus;
import by.bkg.stopwatch.mvc.view.dialog.AddPersonDialog;
import by.bkg.stopwatch.mvc.view.panel.RegisteredPersonsPanel;
import by.bkg.stopwatch.mvc.view.panel.StopWatchPanel;

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

    public static StopWatchPanel createStopWatchComponent(IEventBus eventBus) {
        StopWatchPanel stopWatchPanel = new StopWatchPanel();
        stopWatchPanel.setController(ControllerFactory.getStopWatchController(eventBus, stopWatchPanel));
        stopWatchPanel.init();
        return stopWatchPanel;
    }

    public static RegisteredPersonsPanel createRegisteredPersonsComponent(IEventBus eventBus) {
        RegisteredPersonsPanel registeredPersonsPanel = new RegisteredPersonsPanel();
        registeredPersonsPanel.setController(ControllerFactory.getRegisteredPersonsController(eventBus, registeredPersonsPanel));
        registeredPersonsPanel.init();
        return registeredPersonsPanel;
    }

    public static AddPersonDialog createAddPersonDialog(IEventBus eventBus) {
        AddPersonDialog addPersonDialog = new AddPersonDialog(eventBus);
        return addPersonDialog;
    }

    public static JButton createBtn(String label, ActionListener listener) {
        JButton btn = new JButton(label);
        btn.addActionListener(listener);
        return btn;
    }
}
