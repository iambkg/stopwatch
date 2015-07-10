package by.bkg.stopwatch.desktop.view.model;

/**
 * @author Alexey Baryshnev
 */
public abstract class Callback<T> {

    public abstract void execute(T param);
}
