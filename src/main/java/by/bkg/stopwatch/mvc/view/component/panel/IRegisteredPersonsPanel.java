package by.bkg.stopwatch.mvc.view.component.panel;

import by.bkg.stopwatch.mvc.view.controller.panel.IRegisteredPersonsPanelController;
import by.bkg.stopwatch.mvc.view.IControllable;

/**
 * @author Alexey Baryshnev
 */
public interface IRegisteredPersonsPanel extends IControllable<IRegisteredPersonsPanelController> {

    void init();

    void clear();

    void populateTree();
}
