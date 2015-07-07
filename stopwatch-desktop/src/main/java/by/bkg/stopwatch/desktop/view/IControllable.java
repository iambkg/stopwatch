package by.bkg.stopwatch.desktop.view;

import by.bkg.stopwatch.desktop.view.controller.IComponentController;

/**
 * Defines View classes with Controller inside
 *
 * @author Alexey Baryshnev
 */
@Deprecated
public interface IControllable<T extends IComponentController> {

    void setController(T controller);

    T getController();
}
