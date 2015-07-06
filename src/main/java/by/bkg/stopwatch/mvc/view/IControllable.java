package by.bkg.stopwatch.mvc.view;

import by.bkg.stopwatch.mvc.view.controller.IComponentController;

/**
 * Defines View classes with Controller inside
 *
 * @author Alexey Baryshnev
 */
public interface IControllable<T extends IComponentController> {

    void setController(T controller);

    T getController();
}
