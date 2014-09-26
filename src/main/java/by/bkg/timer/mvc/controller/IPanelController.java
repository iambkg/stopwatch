package by.bkg.timer.mvc.controller;

import by.bkg.timer.mvc.view.IControllable;

/**
 * Base interface for Panel controllers
 *
 * @author Alexey Baryshnev
 */
public interface IPanelController<T extends IControllable> {

    T getPanel();
}
