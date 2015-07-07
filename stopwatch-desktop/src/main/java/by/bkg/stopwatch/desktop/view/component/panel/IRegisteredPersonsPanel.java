package by.bkg.stopwatch.desktop.view.component.panel;

import by.bkg.stopwatch.desktop.view.controller.panel.IRegisteredPersonsPanelController;
import by.bkg.stopwatch.desktop.view.IControllable;

/**
 * @author Alexey Baryshnev
 */
@Deprecated
public interface IRegisteredPersonsPanel extends IControllable<IRegisteredPersonsPanelController> {

    void init();

    void clear();

    void populateTree();
}
