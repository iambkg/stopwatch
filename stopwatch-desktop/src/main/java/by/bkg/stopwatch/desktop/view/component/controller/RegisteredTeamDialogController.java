package by.bkg.stopwatch.desktop.view.component.controller;

import by.bkg.stopwatch.core.model.ITeam;
import by.bkg.stopwatch.core.service.ILogicService;
import by.bkg.stopwatch.desktop.view.component.dialog.RegisteredItemDialog;
import by.bkg.stopwatch.desktop.view.component.dialog.TeamDialog;
import by.bkg.stopwatch.desktop.view.i18n.AppMessages;
import by.bkg.stopwatch.desktop.view.model.Callback;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.awt.event.ActionEvent;
import java.util.List;

/**
 * @author <a href="alexey.baryshnev@ctco.lv">Alexey Baryshnev</a>
 */
@Controller
public class RegisteredTeamDialogController implements RegisteredDialogController<ITeam> {

    @Autowired
    private AppMessages appMessages;

    @Autowired
    private ILogicService logicService;

    @Autowired
    private TeamDialog teamDialog;

    @Override
    public void init(RegisteredItemDialog<ITeam> dialog) {
        teamDialog.init();
        teamDialog.setLocationRelativeTo(dialog);
    }

    @Override
    public List<ITeam> getAll() {
        return logicService.getTeams();
    }

    @Override
    public void startEditing(ITeam teamToEdit, Callback<List<ITeam>> callback) {
        teamDialog.open(TeamDialog.Mode.EDIT, teamToEdit, callback);
    }

    @Override
    public void addItem(ActionEvent e, Callback<List<ITeam>> callback) {
        teamDialog.open(TeamDialog.Mode.ADD, callback);
    }

    @Override
    public void doRemove(ITeam teamToDelete, Callback<List<ITeam>> callback) {
        List<ITeam> refreshedList = logicService.deleteTeam(teamToDelete);
        callback.execute(refreshedList);
    }

    @Override
    public String getTitle() {
        return appMessages.getString("label.teams");
    }

    @Override
    public String getEditLabel() {
        return appMessages.getString("btn.edit-team");
    }

    @Override
    public String getDeleteLabel() {
        return appMessages.getString("btn.delete-person");
    }

    @Override
    public String getAddLabel() {
        return appMessages.getString("btn.add-team");
    }
}
