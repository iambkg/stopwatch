package by.bkg.stopwatch.desktop.view.component.controller;

import by.bkg.stopwatch.core.model.ISportsman;
import by.bkg.stopwatch.core.service.ILogicService;
import by.bkg.stopwatch.desktop.view.component.dialog.RegisteredItemDialog;
import by.bkg.stopwatch.desktop.view.component.dialog.SportsmanDialog;
import by.bkg.stopwatch.desktop.view.i18n.AppMessages;
import by.bkg.stopwatch.desktop.view.model.Callback;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.awt.event.ActionEvent;
import java.util.List;

/**
 * @author Alexey Baryshnev
 */
@Controller
public class RegisteredSportsmanDialogController implements RegisteredDialogController<ISportsman> {

    @Autowired
    private AppMessages appMessages;

    @Autowired
    private ILogicService logicService;

    @Autowired
    private SportsmanDialog sportsmanDialog;

    @Override
    public void init(RegisteredItemDialog<ISportsman> dialog) {
        sportsmanDialog.init();
        sportsmanDialog.setLocationRelativeTo(dialog);
    }

    @Override
    public final List<ISportsman> getAll() {
        return logicService.getSportsmen();
    }

    @Override
    public void startEditing(final ISportsman sportsmanToEdit, final Callback<List<ISportsman>> callback) {
        sportsmanDialog.open(SportsmanDialog.Mode.EDIT, sportsmanToEdit, callback);
    }

    @Override
    public void addItem(ActionEvent e, final Callback<List<ISportsman>> callback) {
        sportsmanDialog.open(SportsmanDialog.Mode.ADD, callback);
    }

    @Override
    public void doRemove(final ISportsman sportsmanToDelete, Callback<List<ISportsman>> callback) {
        List<ISportsman> refreshedList = logicService.deleteSportsman(sportsmanToDelete);
        callback.execute(refreshedList);
    }

    @Override
    public String getTitle() {
        return appMessages.getString("label.sportsmen");
    }

    @Override
    public String getEditLabel() {
        return appMessages.getString("btn.edit-person");
    }

    @Override
    public String getDeleteLabel() {
        return appMessages.getString("btn.delete-person");
    }

    @Override
    public String getAddLabel() {
        return appMessages.getString("btn.add-person");
    }
}
