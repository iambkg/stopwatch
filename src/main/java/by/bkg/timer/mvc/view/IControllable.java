package by.bkg.timer.mvc.view;

import by.bkg.timer.mvc.controller.IPanelController;

/**
 * Defines View classes with Controller inside
 *
 * @author Alexey Baryshnev
 */
public interface IControllable<T extends IPanelController> {

    void setController(T controller);
}
