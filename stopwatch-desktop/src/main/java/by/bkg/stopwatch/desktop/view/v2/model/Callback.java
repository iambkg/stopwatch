package by.bkg.stopwatch.desktop.view.v2.model;

/**
 * @author Alexey Baryshnev
 */
public abstract class Callback<T> {

    public abstract void execute(T param);
}
