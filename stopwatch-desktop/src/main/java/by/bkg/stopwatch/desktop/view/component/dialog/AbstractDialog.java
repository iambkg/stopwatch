package by.bkg.stopwatch.desktop.view.component.dialog;

import by.bkg.stopwatch.core.model.ISplitRecord;
import by.bkg.stopwatch.desktop.view.model.Callback;

import javax.swing.*;
import java.util.List;

/**
 * @author Alexey Baryshnev
 */
public abstract class AbstractDialog<T> extends JDialog {

    protected static final int INITIAL_X = 6;          // TODO ABA: rename
    protected static final int INITIAL_Y = 6;          // TODO ABA: rename
    protected static final int X_PAD = 6;              // TODO ABA: rename
    protected static final int Y_PAD = 6;              // TODO ABA: rename

    protected Callback<List<T>> operationPerformedCallback;

    public abstract void init();

    protected abstract JPanel createFormPanel();

    protected abstract JComponent createButtonPanel();

    protected abstract void clearInputs();

    public abstract void bind(final T splitToEdit);

    public abstract T unbind();

    public abstract void open(final T splitToEdit, Callback<List<T>> callback);

    public void open(Callback<List<T>> callback) {
        setOperationPerformedCallback(callback);
        setVisible(true);
    }

    public void close() {
        setOperationPerformedCallback(null);
        setVisible(false);
        clearInputs();
    }

    public void setOperationPerformedCallback(final Callback<List<T>> operationPerformedCallback) {
        this.operationPerformedCallback = operationPerformedCallback;
    }
}
