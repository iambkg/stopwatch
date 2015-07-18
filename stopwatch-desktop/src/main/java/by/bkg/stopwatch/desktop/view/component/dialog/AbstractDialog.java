package by.bkg.stopwatch.desktop.view.component.dialog;

import by.bkg.stopwatch.desktop.view.model.Callback;

import javax.swing.*;
import java.util.List;

/**
 * @author Alexey Baryshnev
 */
public abstract class AbstractDialog<T, CT> extends JDialog {

    protected static final int INITIAL_X = 6;
    protected static final int INITIAL_Y = 6;
    protected static final int X_PAD = 6;
    protected static final int Y_PAD = 6;

    protected Callback<CT> operationPerformedCallback;

    public abstract void init();

    protected abstract JPanel createFormPanel();

    protected abstract JComponent createButtonPanel();

    protected abstract void clearInputs();

    public abstract void bind(final T model);

    public abstract T unbind();

    public void open(final T model, Callback<CT> callback) {
        bind(model);
        open(callback);
    }

    public void open(final Callback<CT> callback) {
        setOperationPerformedCallback(callback);
        setVisible(true);
    }

    public void close() {
        setOperationPerformedCallback(null);
        setVisible(false);
        clearInputs();
    }

    public void setOperationPerformedCallback(final Callback<CT> operationPerformedCallback) {
        this.operationPerformedCallback = operationPerformedCallback;
    }
}
