package by.bkg.stopwatch.desktop.view.component.dialog;

import by.bkg.stopwatch.core.model.ICategory;
import by.bkg.stopwatch.core.model.ISportsman;
import by.bkg.stopwatch.core.model.Sportsman;
import by.bkg.stopwatch.desktop.model.AppConstants;
import by.bkg.stopwatch.desktop.view.i18n.AppMessages;
import by.bkg.stopwatch.desktop.view.utilities.ComponentFactory;
import by.bkg.stopwatch.desktop.view.utilities.SpringLayoutUtilities;
import by.bkg.stopwatch.desktop.view.component.controller.SportsmanDialogController;
import by.bkg.stopwatch.desktop.view.model.Callback;
import by.bkg.stopwatch.desktop.view.model.SexModel;
import by.bkg.stopwatch.desktop.view.model.factory.DataFactory;
import net.sourceforge.jdatepicker.JDatePicker;
import net.sourceforge.jdatepicker.impl.JDatePanelImpl;
import net.sourceforge.jdatepicker.impl.JDatePickerImpl;
import net.sourceforge.jdatepicker.impl.UtilDateModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import java.util.List;

/**
 * "Add Person" dialog.
 *
 * @author Alexey Baryshnev
 */
@Component
public class SportsmanDialog extends AbstractDialog<ISportsman> {

    @Autowired
    private ComponentFactory componentFactory;

    private Mode mode;

    private static final int MIN_WIDTH = 400;
    private static final int MIN_HEIGHT = 220;

    private static final Integer NUMBER_OF_ROWS = 7;
    private static final int NUMBER_OF_COLS = 2;
    private static final int INITIAL_X = 6;          // TODO ABA: rename
    private static final int INITIAL_Y = 6;          // TODO ABA: rename
    private static final int X_PAD = 6;              // TODO ABA: rename
    private static final int Y_PAD = 6;              // TODO ABA: rename

    private JComboBox categoryField;
    private JComboBox sexField;
    private JTextField lastNameField;
    private JTextField firstNameField;
    private JTextField middleNameField;
    private JTextField startNumberField;
    private JDatePicker dateOfBirthField;

    @Autowired
    private AppMessages appMessages;

    @Autowired
    private DataFactory dataFactory;

    @Autowired
    private SportsmanDialogController controller;

    @Override
    public void init() {
        setMode(Mode.ADD);
        setMinimumSize(new Dimension(MIN_WIDTH, MIN_HEIGHT));

        Container c = getContentPane();
        c.setLayout(new BorderLayout());

        JPanel formPanel = createFormPanel();

        add(formPanel, BorderLayout.CENTER);
        add(createButtonPanel(), BorderLayout.SOUTH);

        SpringLayoutUtilities.makeCompactGrid(formPanel, NUMBER_OF_ROWS, NUMBER_OF_COLS, INITIAL_X, INITIAL_Y, X_PAD, Y_PAD);

        pack();
    }

    @Override
    protected JPanel createFormPanel() {
        JPanel formPanel = new JPanel();
        SpringLayout layout = new SpringLayout();
        formPanel.setLayout(layout);

        formPanel.add(new JLabel(appMessages.getString("label.category")));
        formPanel.add(createCategoryField());

        formPanel.add(new JLabel(appMessages.getString("label.last-name")));
        formPanel.add(createLastNameField());

        formPanel.add(new JLabel(appMessages.getString("label.first-name")));
        formPanel.add(createFirstNameField());

        formPanel.add(new JLabel(appMessages.getString("label.middle-name")));
        formPanel.add(createMiddleNameField());

        formPanel.add(new JLabel(appMessages.getString("label.sex")));
        formPanel.add(createSexField());

        formPanel.add(new JLabel(appMessages.getString("label.date-of-birth")));
//        formPanel.add(createDateOfBirthField());
        formPanel.add(new JLabel("field will be here"));

        formPanel.add(new JLabel(appMessages.getString("label.start-number-long")));
        formPanel.add(createStartNumberField());
        return formPanel;
    }

    private JDatePicker createDateOfBirthField() {
        dateOfBirthField = new JDatePickerImpl(new JDatePanelImpl(new UtilDateModel()));
        return dateOfBirthField;
    }

    @Override
    protected JComponent createButtonPanel() {
        JPanel btnPanel = new JPanel();
        btnPanel.add(componentFactory.createBtn(appMessages.getString("btn.ok"), createOkBtnListener()));
        btnPanel.add(componentFactory.createBtn(appMessages.getString("btn.cancel"), createCancelBtnListener()));
        return btnPanel;
    }

    private ActionListener createOkBtnListener() {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                onOkClick();
            }
        };
    }

    private ActionListener createCancelBtnListener() {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                onCancelClick();
            }
        };
    }

    private void onOkClick() {
        List<ISportsman> sportsmen;
        switch (mode) {
            case ADD:
                sportsmen = controller.addSportsman(unbind());
                operationPerformedCallback.execute(sportsmen);
                break;
            case EDIT:
                sportsmen = controller.editSportsman(unbind());
                operationPerformedCallback.execute(sportsmen);
                break;
        }
        close();
    }

    private void onCancelClick() {
        close();
    }

    private JComponent createCategoryField() {
        categoryField = new JComboBox();
        categoryField.setModel(new DefaultComboBoxModel(dataFactory.getAvailableCategories()));
        categoryField.setSelectedIndex(0);
        return categoryField;
    }

    private JComponent createSexField() {
        sexField = new JComboBox();
        sexField.setModel(new DefaultComboBoxModel(dataFactory.getSexValues()));
        sexField.setSelectedIndex(0);
        return sexField;
    }

    private JComponent createLastNameField() {
        lastNameField = new JTextField();
        return lastNameField;
    }

    private JComponent createFirstNameField() {
        firstNameField = new JTextField();
        return firstNameField;
    }

    private JComponent createMiddleNameField() {
        middleNameField = new JTextField();
        return middleNameField;
    }

    private JComponent createStartNumberField() {
        startNumberField = new JTextField();
        startNumberField.setEnabled(Mode.ADD.equals(mode));
        return startNumberField;
    }

    @Override
    protected void clearInputs() {
        categoryField.setSelectedIndex(0);
        sexField.setSelectedIndex(0);
        lastNameField.setText(AppConstants.EMPTY_STRING);
        firstNameField.setText(AppConstants.EMPTY_STRING);
        middleNameField.setText(AppConstants.EMPTY_STRING);
        startNumberField.setText(AppConstants.EMPTY_STRING);
    }

    @Override
    public void bind(ISportsman toBind) {
        firstNameField.setText(toBind.getFirstName());
        middleNameField.setText(toBind.getMiddleName());
        lastNameField.setText(toBind.getLastName());
//                        new Date(), // TODO ABA: get from dialog
//                        ((SexModel) sexField.getModel().getSelectedItem()).getSex();
//                        (ICategory) categoryField.getModel().getSelectedItem();
        startNumberField.setText(toBind.getStartNumber());
    }

    @Override
    public ISportsman unbind() {
        ISportsman sportsman = new Sportsman();
        sportsman.setFirstName(firstNameField.getText());
        sportsman.setMiddleName(middleNameField.getText());
        sportsman.setLastName(lastNameField.getText());
        sportsman.setDateOfBirth(new Date()); // TODO ABA: get from dialog
        sportsman.setSex(((SexModel) sexField.getModel().getSelectedItem()).getSex());
        sportsman.setCategory((ICategory) categoryField.getModel().getSelectedItem());
        sportsman.setStartNumber(startNumberField.getText());
        clearInputs();
        return sportsman;
    }

    public Mode getMode() {
        return mode;
    }

    private void setMode(Mode mode) {
        this.mode = mode;
        setTitle(appMessages.getString(mode.getTitleI18NKey()));
        if (startNumberField != null) {
            startNumberField.setEnabled(Mode.ADD.equals(mode));
        }
    }

    public void open(Mode mode, ISportsman sportsman, Callback<List<ISportsman>> callback) {
        bind(sportsman);
        open(mode, callback);
    }

    public void open(Mode mode, Callback<List<ISportsman>> callback) {
        setMode(mode);
        super.open(callback);
    }

    @Override
    public void open(ISportsman sportsman, Callback<List<ISportsman>> callback) {
        open(Mode.ADD, sportsman, callback);
    }

    @Override
    public void open(Callback<List<ISportsman>> callback) {
        open(Mode.ADD, callback);
    }

    public enum Mode {

        ADD("label.add-sportsman"), EDIT("label.edit-sportsman");

        private String titleI18NKey;

        Mode(String titleI18NKey) {
            this.titleI18NKey = titleI18NKey;
        }

        public String getTitleI18NKey() {
            return titleI18NKey;
        }
    }
}
