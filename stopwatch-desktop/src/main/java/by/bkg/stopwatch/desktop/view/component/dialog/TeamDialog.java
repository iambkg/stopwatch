package by.bkg.stopwatch.desktop.view.component.dialog;

import by.bkg.stopwatch.core.model.ITeam;
import by.bkg.stopwatch.desktop.view.i18n.AppMessages;
import by.bkg.stopwatch.desktop.view.model.Callback;
import by.bkg.stopwatch.desktop.view.model.factory.DataFactory;
import by.bkg.stopwatch.desktop.view.utilities.ComponentFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.swing.*;
import java.util.List;

/**
 * @author <a href="alexey.baryshnev@ctco.lv">Alexey Baryshnev</a>
 */
@Component
public class TeamDialog extends AbstractDialog<ITeam, List<ITeam>> {

    @Autowired
    private AppMessages appMessages;

    @Autowired
    private DataFactory dataFactory;

    @Autowired
    private ComponentFactory componentFactory;

//    @Autowired
//    private TeamDialogController controller;

    private Mode mode;

    private JTextField startNumberField;
    private JLabel startNumberErrorMsg;

    @Override
    public void init() {

    }

    public void open(final Mode mode, final ITeam team, final Callback<List<ITeam>> callback) {
        bind(team);
        open(mode, callback);
    }

    public void open(final Mode mode, final Callback<List<ITeam>> callback) {
        setMode(mode);
        super.open(callback);
    }

    @Override
    public void open(final ITeam sportsman, final Callback<List<ITeam>> callback) {
        open(Mode.ADD, sportsman, callback);
    }

    @Override
    protected JPanel createFormPanel() {
        return null;
    }

    @Override
    protected JComponent createButtonPanel() {
        return null;
    }

    @Override
    protected void clearInputs() {

    }

    @Override
    public void bind(ITeam model) {

    }

    @Override
    public ITeam unbind() {
        return null;
    }

    private void setMode(final Mode mode) {
        this.mode = mode;
        setTitle(appMessages.getString(mode.getTitleI18NKey()));
        if (startNumberField != null) {
            startNumberField.setEnabled(Mode.ADD.equals(mode));
        }
    }

    public enum Mode {

        ADD("label.add-team"), EDIT("label.edit-team");

        private final String titleI18NKey;

        Mode(final String titleI18NKey) {
            this.titleI18NKey = titleI18NKey;
        }

        public String getTitleI18NKey() {
            return titleI18NKey;
        }
    }
}
