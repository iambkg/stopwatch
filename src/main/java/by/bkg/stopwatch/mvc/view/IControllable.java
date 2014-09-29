package by.bkg.stopwatch.mvc.view;

import by.bkg.stopwatch.mvc.controller.IPanelController;

/**
 * Defines View classes with Controller inside
 *
 * @author Alexey Baryshnev
 */
public interface IControllable<T extends IPanelController> {

    void setController(T controller);
}
