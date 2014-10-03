package by.bkg.stopwatch.mvc.view.impl.dialog;

import by.bkg.stopwatch.common.factory.model.DataFactory;
import by.bkg.stopwatch.common.factory.view.ComponentFactory;
import by.bkg.stopwatch.mvc.controller.EventBus;
import by.bkg.stopwatch.mvc.model.Category;
import by.bkg.stopwatch.mvc.model.Person;
import by.bkg.stopwatch.mvc.view.utilities.SpringUtilities;

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

    private EventBus eventBus;

    private JComboBox categoryField;
    private JTextField lastNameField;
    private JTextField firstNameField;
    private JTextField middleNameField;
    private JTextField startNumberField;

    public AddPersonDialog(EventBus eventBus) {
        this.eventBus = eventBus;

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

        formPanel.add(new JLabel("Category")); // TODO ABA: i18n
        formPanel.add(createCategoryField());

        formPanel.add(new JLabel("Last Name"));   // TODO ABA: i18n
        formPanel.add(createLastNameField());

        formPanel.add(new JLabel("First Name"));  // TODO ABA: i18n
        formPanel.add(createFirstNameField());

        formPanel.add(new JLabel("Middle Name"));  // TODO ABA: i18n
        formPanel.add(createMiddleNameField());

        formPanel.add(new JLabel("Start Number"));  // TODO ABA: i18n
        formPanel.add(createStartNumberField());
        return formPanel;
    }

    private JComponent createButtonPanel() {
        JPanel btnPanel = new JPanel();
        BoxLayout layout = new BoxLayout(btnPanel, BoxLayout.X_AXIS);
        btnPanel.setLayout(layout);

        btnPanel.add(createOkBtn());
        btnPanel.add(createCancelBtn());
        return btnPanel;
    }

    private JComponent createOkBtn() {
        return ComponentFactory.createBtn("Ok", new ActionListener() { // TODO ABA: i18n
            @Override
            public void actionPerformed(ActionEvent e) {
                onOkClick();
            }
        });
    }

    private JComponent createCancelBtn() {
        return ComponentFactory.createBtn("Cancel", new ActionListener() {     // TODO ABA: i18n
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
    }

    public Person unbind() {
        Person person = new Person();
        person.setCategory((Category) categoryField.getModel().getSelectedItem());
        person.setLastName(lastNameField.getText());
        person.setFirstName(firstNameField.getText());
        person.setMiddleName(middleNameField.getText());
        person.setStartNumber(Integer.parseInt(startNumberField.getText()));
        return person;
    }
}
