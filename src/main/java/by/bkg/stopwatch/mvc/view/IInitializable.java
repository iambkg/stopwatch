package by.bkg.stopwatch.mvc.view;

/**
 * @author Alexey Baryshnev
 */
public interface IInitializable {

    void setInitialized(Boolean initialized);

    Boolean isInitialized();

    void init();

    void makeInit();
}
