package by.bkg.stopwatch.desktop.view.component.dialog;

import by.bkg.stopwatch.core.model.ICategory;
import by.bkg.stopwatch.desktop.view.controller.IEventBus;
import by.bkg.stopwatch.desktop.model.AppConstants;
import by.bkg.stopwatch.desktop.model.business.Person;
import by.bkg.stopwatch.desktop.model.factory.DataFactory;
import by.bkg.stopwatch.desktop.view.component.factory.ComponentFactory;
import by.bkg.stopwatch.desktop.view.i18n.AppMessages;
import by.bkg.stopwatch.desktop.view.utilities.SpringUtilities;

import javax.inject.Inject;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * "Add Person" dialog.
 *
 * @author Alexey Baryshnev
 */
public class AddPersonDialog extends JDialog {

    private static final int MIN_WIDTH = 400;
    private static final int MIN_HEIGHT = 200;

    private static final Integer NUMBER_OF_ROWS = 5;
    private static final int NUMBER_OF_COLS = 2;
    private static final int INITIAL_X = 6;
    private static final int INITIAL_Y = 6;
    private static final int X_PAD = 6;
    private static final int Y_PAD = 6;

    private JComboBox categoryField;
    private JTextField lastNameField;
    private JTextField firstNameField;
    private JTextField middleNameField;
    private JTextField startNumberField;

    private IEventBus eventBus;

    private AppMessages appMessages;

    @Inject
    public AddPersonDialog(IEventBus eventBus, AppMessages appMessages) {
        this.eventBus = eventBus;
        this.appMessages = appMessages;
        setMinimumSize(new Dimension(MIN_WIDTH, MIN_HEIGHT));

        Container c = getContentPane();
        c.setLayout(new BorderLayout());

        JPanel formPanel = createFormPanel();

        add(formPanel, BorderLayout.CENTER);
        add(createButtonPanel(), BorderLayout.SOUTH);

        SpringUtilities.makeCompactGrid(formPanel, NUMBER_OF_ROWS, NUMBER_OF_COLS, INITIAL_X, INITIAL_Y, X_PAD, Y_PAD);

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

        formPanel.add(new JLabel(appMessages.getString("label.start-number-long")));
        formPanel.add(createStartNumberField());
        return formPanel;
    }

    private JComponent createButtonPanel() {
        JPanel btnPanel = new JPanel();

        btnPanel.add(createOkBtn());
        btnPanel.add(createCancelBtn());
        return btnPanel;
    }

    private JComponent createOkBtn() {
        return ComponentFactory.createBtn(appMessages.getString("btn.ok"), new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                onOkClick();
            }
        });
    }

    private JComponent createCancelBtn() {
        return ComponentFactory.createBtn(appMessages.getString("btn.cancel"), new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                onCancelClick();
            }
        });
    }

    private void onOkClick() {
        eventBus.onAddPersonConfirmed(unbind());
        setVisible(false);
    }

    private void onCancelClick() {
        setVisible(false);
    }

    private JComponent createCategoryField() {
        categoryField = new JComboBox();
        categoryField.setModel(new DefaultComboBoxModel(DataFactory.getAvailableCategories()));
        return categoryField;
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
        lastNameField.setText(AppConstants.EMPTY_STRING);
        firstNameField.setText(AppConstants.EMPTY_STRING);
        middleNameField.setText(AppConstants.EMPTY_STRING);
        startNumberField.setText(AppConstants.EMPTY_STRING);
    }

    public Person unbind() {
        Person person = new Person();
        person.setCategory((ICategory) categoryField.getModel().getSelectedItem());
        person.setLastName(lastNameField.getText());
        person.setFirstName(firstNameField.getText());
        person.setMiddleName(middleNameField.getText());
        person.setStartNumber(startNumberField.getText());
        return person;
    }
}
