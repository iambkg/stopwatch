package by.bkg.stopwatch.desktop.view.controller;

import by.bkg.stopwatch.desktop.view.IControllable;

/**
 * Base interface for Panel controllers
 *
 * @author Alexey Baryshnev
 */
@Deprecated
public interface IComponentController<T extends IControllable> {

    T getPanel();
}
