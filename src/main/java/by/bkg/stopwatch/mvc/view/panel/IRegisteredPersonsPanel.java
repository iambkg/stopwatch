package by.bkg.stopwatch.mvc.view.panel;

import by.bkg.stopwatch.mvc.controller.panel.IRegisteredPersonsPanelController;
import by.bkg.stopwatch.mvc.view.IControllable;

/**
 * @author Alexey Baryshnev
 */
public interface IRegisteredPersonsPanel extends IControllable<IRegisteredPersonsPanelController> {

    void init();

    void clear();

    void populateTree();
}
