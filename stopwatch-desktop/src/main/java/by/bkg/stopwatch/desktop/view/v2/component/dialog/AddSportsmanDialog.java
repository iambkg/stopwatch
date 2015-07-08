package by.bkg.stopwatch.desktop.view.v2.component.dialog;

import by.bkg.stopwatch.core.model.ICategory;
import by.bkg.stopwatch.core.model.ISportsman;
import by.bkg.stopwatch.core.model.ISportsmanData;
import by.bkg.stopwatch.core.model.SportsmanData;
import by.bkg.stopwatch.desktop.model.AppConstants;
import by.bkg.stopwatch.desktop.view.i18n.AppMessages;
import by.bkg.stopwatch.desktop.view.utilities.SpringLayoutUtilities;
import by.bkg.stopwatch.desktop.view.v2.component.controller.AddSportsmanDialogController;
import by.bkg.stopwatch.desktop.view.v2.model.SexModel;
import by.bkg.stopwatch.desktop.view.v2.model.factory.DataFactory;
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
public class AddSportsmanDialog extends JDialog {

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
    private AddSportsmanDialogController controller;

    public void init() {
        setMinimumSize(new Dimension(MIN_WIDTH, MIN_HEIGHT));
        setTitle(appMessages.getString("label.add-sportsman"));

        Container c = getContentPane();
        c.setLayout(new BorderLayout());

        JPanel formPanel = createFormPanel();

        add(formPanel, BorderLayout.CENTER);
        add(createButtonPanel(), BorderLayout.SOUTH);

        SpringLayoutUtilities.makeCompactGrid(formPanel, NUMBER_OF_ROWS, NUMBER_OF_COLS, INITIAL_X, INITIAL_Y, X_PAD, Y_PAD);

        pack();
    }

    private JPanel createFormPanel() {
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

    private JComponent createButtonPanel() {
        JPanel btnPanel = new JPanel();

        btnPanel.add(createOkBtn());
        btnPanel.add(createCancelBtn());
        return btnPanel;
    }

    private JComponent createOkBtn() {
        JButton btn = new JButton(appMessages.getString("btn.ok"));
        btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                onOkClick();
            }
        });
        return btn;
    }

    private JComponent createCancelBtn() {
        JButton btn = new JButton(appMessages.getString("btn.cancel"));
        btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                onCancelClick();
            }
        });
        return btn;
    }

    private void onOkClick() {
        List<ISportsman> sportsmen = controller.addSportsman(unbind());
        setVisible(false);
        // TODO ABA: callback is set in "Sportsmen" dialog. It refreshes list of sportsmen on "Sportsmen" dialog
//        callback.execute(sportsmen);
    }

    private void onCancelClick() {
        setVisible(false);
    }

    private JComponent createCategoryField() {
        categoryField = new JComboBox();
        categoryField.setModel(new DefaultComboBoxModel(dataFactory.getAvailableCategories()));
        return categoryField;
    }

    private JComponent createSexField() {
        sexField = new JComboBox();
        sexField.setModel(new DefaultComboBoxModel(dataFactory.getSexValues()));
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
        return startNumberField;
    }

    @Override
    public void setVisible(boolean newVisibility) {
        if (newVisibility) {
            // when set visible to true - reset input data
            clearInputs();
        }
        super.setVisible(newVisibility);
    }

    private void clearInputs() {
        categoryField.getModel().setSelectedItem(null);
        sexField.getModel().setSelectedItem(null);
        lastNameField.setText(AppConstants.EMPTY_STRING);
        firstNameField.setText(AppConstants.EMPTY_STRING);
        middleNameField.setText(AppConstants.EMPTY_STRING);
        startNumberField.setText(AppConstants.EMPTY_STRING);
    }

    public ISportsmanData unbind() {
        ISportsmanData data = new SportsmanData(
                firstNameField.getText(),
                middleNameField.getText(),
                lastNameField.getText(),
                new Date(), // TODO ABA: get from dialog
                ((SexModel) sexField.getModel().getSelectedItem()).getSex(),
                (ICategory) categoryField.getModel().getSelectedItem(),
                startNumberField.getText()
        );
        return data;
    }
}
