package by.bkg.stopwatch.desktop.view.component.controller;

import by.bkg.stopwatch.desktop.view.component.dialog.RegisteredItemDialog;
import by.bkg.stopwatch.desktop.view.model.Callback;

import java.awt.event.ActionEvent;
import java.util.List;

/**
 * @author <a href="alexey.baryshnev@ctco.lv">Alexey Baryshnev</a>
 */
public interface RegisteredDialogController<T> {

    void init(RegisteredItemDialog<T> dialog);

    void delete(T selected, Callback<List<T>> deleteCallback);

    List<T> getAll();

    void startEditing(T itemToEdit, Callback<List<T>> callback);

    void addItem(ActionEvent e, Callback<List<T>> callback);

    void doRemove(T itemToDelete, Callback<List<T>> callback);

    String getTitle();

    String getEditLabel();

    String getDeleteLabel();

    String getAddLabel();
}
