package by.bkg.stopwatch.desktop.view;

/**
 * @author Alexey Baryshnev
 */
@Deprecated
public interface IInitializable {

    void setInitialized(Boolean initialized);

    Boolean isInitialized();

    void init();

    void makeInit();
}
