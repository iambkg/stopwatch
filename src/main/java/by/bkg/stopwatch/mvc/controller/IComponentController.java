package by.bkg.stopwatch.mvc.controller;

import by.bkg.stopwatch.mvc.view.IControllable;

/**
 * Base interface for Panel controllers
 *
 * @author Alexey Baryshnev
 */
public interface IComponentController<T extends IControllable> {

    T getPanel();
}
