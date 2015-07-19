package by.bkg.stopwatch.desktop.view.component.dialog;

import by.bkg.stopwatch.core.model.ICategory;
import by.bkg.stopwatch.core.model.ISportsman;
import by.bkg.stopwatch.core.model.Sportsman;
import by.bkg.stopwatch.desktop.model.AppConstants;
import by.bkg.stopwatch.desktop.model.IDocumentListenerAction;
import by.bkg.stopwatch.desktop.model.validation.ValidationChain;
import by.bkg.stopwatch.desktop.model.validation.ValidationEntity;
import by.bkg.stopwatch.desktop.model.validation.ValidationResult;
import by.bkg.stopwatch.desktop.view.component.controller.SportsmanDialogController;
import by.bkg.stopwatch.desktop.view.i18n.AppMessages;
import by.bkg.stopwatch.desktop.view.model.Callback;
import by.bkg.stopwatch.desktop.view.model.factory.DataFactory;
import by.bkg.stopwatch.desktop.view.utilities.ComponentFactory;
import by.bkg.stopwatch.desktop.view.utilities.SpringLayoutUtilities;
import org.jdatepicker.DateModel;
import org.jdatepicker.JDateComponentFactory;
import org.jdatepicker.impl.JDatePickerImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.swing.*;
import javax.swing.text.JTextComponent;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

/**
 * "Add Person" dialog.
 *
 * @author Alexey Baryshnev
 */
@Component
public class SportsmanDialog extends AbstractDialog<ISportsman, List<ISportsman>> {

    @Autowired
    private ComponentFactory componentFactory;

    private Mode mode;

    private static final int MIN_WIDTH = 400;
    private static final int MIN_HEIGHT = 250;

    private static final Integer NUMBER_OF_ROWS = 6;
    private static final int NUMBER_OF_COLS = 2;

    private JComboBox categoryField;
    private JTextField lastNameField;
    private JTextField firstNameField;
    private JTextField middleNameField;
    private JTextField startNumberField;
    private JLabel startNumberErrorMsg;
    private JDatePickerImpl dateOfBirthField;

    private JButton okBtn;

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
        setIconImage(componentFactory.getImageIcon("icons/x16/Buddy.png").getImage());
        addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {
                // do nothing
            }

            @Override
            public void windowClosing(WindowEvent e) {
                // do nothing
            }

            @Override
            public void windowClosed(WindowEvent e) {
                // do nothing
            }

            @Override
            public void windowIconified(WindowEvent e) {
                // do nothing
            }

            @Override
            public void windowDeiconified(WindowEvent e) {
                // do nothing
            }

            @Override
            public void windowActivated(WindowEvent e) {
                startNumberField.requestFocus();
            }

            @Override
            public void windowDeactivated(WindowEvent e) {
                // do nothing
            }
        });

        Container c = getContentPane();
        c.setLayout(new BorderLayout());

        JPanel formPanel = createFormPanel();

        add(formPanel, BorderLayout.CENTER);
        add(createButtonPanel(), BorderLayout.SOUTH);

        SpringLayoutUtilities.makeCompactGrid(formPanel, NUMBER_OF_ROWS, NUMBER_OF_COLS, INITIAL_X, INITIAL_Y, X_PAD, Y_PAD);
        clearInputs();
        setModalityType(ModalityType.APPLICATION_MODAL);

        pack();
    }

    @Override
    protected JPanel createFormPanel() {
        JPanel formPanel = new JPanel();
        SpringLayout layout = new SpringLayout();
        formPanel.setLayout(layout);

        formPanel.add(new JLabel(appMessages.getString("label.start-number-long")));
        formPanel.add(createStartNumberField());

        formPanel.add(new JLabel(appMessages.getString("label.category")));
        formPanel.add(createCategoryField());

        formPanel.add(new JLabel(appMessages.getString("label.last-name")));
        formPanel.add(createLastNameField());

        formPanel.add(new JLabel(appMessages.getString("label.first-name")));
        formPanel.add(createFirstNameField());

        formPanel.add(new JLabel(appMessages.getString("label.middle-name")));
        formPanel.add(createMiddleNameField());

        formPanel.add(new JLabel(appMessages.getString("label.date-of-birth")));
        formPanel.add(createDateOfBirthField());

        return formPanel;
    }

    private JDatePickerImpl createDateOfBirthField() {
        JFormattedTextField.AbstractFormatter formatter = new JFormattedTextField.AbstractFormatter() {
            private SimpleDateFormat dateFormatter = new SimpleDateFormat(appMessages.getString("inner.date-format"));

            @Override
            public Object stringToValue(String text) throws ParseException {
                Calendar cal = Calendar.getInstance();
                cal.setTime(dateFormatter.parse(text));
                return cal;
            }

            @Override
            public String valueToString(Object value) throws ParseException {
                if (value != null) {
                    Calendar cal = (Calendar) value;
                    return dateFormatter.format(cal.getTime());
                }

                return "";
            }
        };

        JDateComponentFactory dateComponentFactory = new JDateComponentFactory(null, formatter, null);
        dateOfBirthField = (JDatePickerImpl) dateComponentFactory.createJDatePicker();
        dateOfBirthField.setShowYearButtons(true);
        return dateOfBirthField;
    }

    @Override
    protected JComponent createButtonPanel() {
        JPanel btnPanel = new JPanel();
        okBtn = componentFactory.createBtn("icons/x16/Symbol-Check.png", appMessages.getString("btn.ok"), appMessages.getString("btn.ok"), createOkBtnListener());
        btnPanel.add(okBtn);
        btnPanel.add(componentFactory.createBtn("icons/x16/Symbol-Delete.png", appMessages.getString("btn.cancel"), appMessages.getString("btn.cancel"), createCancelBtnListener()));
        return btnPanel;
    }

    private ActionListener createOkBtnListener() {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                switch (mode) {
                    case ADD:
                        operationPerformedCallback.execute(controller.addSportsman(unbind()));
                        close();
                        break;
                    case EDIT:
                        operationPerformedCallback.execute(controller.editSportsman(unbind()));
                        close();
                        break;
                }
            }
        };
    }

    private ActionListener createCancelBtnListener() {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                close();
            }
        };
    }

    private JComponent createCategoryField() {
        categoryField = new JComboBox();
        categoryField.setModel(new DefaultComboBoxModel(dataFactory.getAvailableCategories()));
        categoryField.setSelectedIndex(0);
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
        startNumberField.setEnabled(Mode.ADD.equals(mode));
        startNumberField.requestFocus();
        startNumberField.getDocument().addDocumentListener(componentFactory.createDocumentListener(new IDocumentListenerAction() {
            @Override
            public void act() {
                okBtn.setEnabled(true);
            }
        }));

        final ValidationChain<String> validationChain = new ValidationChain<String>()
                .add(ValidationEntity.MANDATORY_TEXT_FIELD)
                .add(ValidationEntity.DIGITS_TEXT_FIELD)
                .add(new ValidationEntity<String>() {
                    @Override
                    public boolean isValid(final String typedValue) {
                        return !controller.startNumberAlreadyRegistered(typedValue);
                    }

                    @Override
                    public String getErrorMessage() {
                        return "validation.start-number-already-registered";
                    }
                });

        startNumberField.setInputVerifier(new InputVerifier() {
            @Override
            public boolean verify(final JComponent input) {
                List<ValidationResult> results = validationChain.validate(((JTextComponent) input).getText());

                for (ValidationResult result : results) {
                    String errorMessage = result.getErrorMessage();
                    if (!AppConstants.EMPTY_STRING.equals(errorMessage)) {
                        startNumberErrorMsg.setText(appMessages.getString(errorMessage));
                        okBtn.setEnabled(false);
                        return false;
                    }
                }

                startNumberErrorMsg.setText(AppConstants.EMPTY_STRING);
                okBtn.setEnabled(true);
                return true;
            }
        });

        startNumberErrorMsg = componentFactory.createValidationMessageLabel();

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
        panel.add(startNumberField);
        panel.add(startNumberErrorMsg);
        return panel;
    }

    @Override
    protected void clearInputs() {
        categoryField.setSelectedIndex(0);
        dateOfBirthField.getModel().setDate(1990, 0, 1);
        lastNameField.setText(AppConstants.EMPTY_STRING);
        firstNameField.setText(AppConstants.EMPTY_STRING);
        middleNameField.setText(AppConstants.EMPTY_STRING);
        startNumberField.setText(AppConstants.EMPTY_STRING);
    }

    @Override
    public void bind(final ISportsman toBind) {
        firstNameField.setText(toBind.getFirstName());
        middleNameField.setText(toBind.getMiddleName());
        lastNameField.setText(toBind.getLastName());
        setDateOfBirthFieldValue(toBind);
        selectCategory(toBind);
        startNumberField.setText(toBind.getStartNumber());
    }

    private void selectCategory(ISportsman toBind) {
        for (int i = 0; i < categoryField.getModel().getSize(); i++) {
            ICategory category = (ICategory) categoryField.getModel().getElementAt(i);
            if (category.equals(toBind.getCategory())) {
                categoryField.setSelectedItem(category);
                return;
            }
        }
    }

    private void setDateOfBirthFieldValue(ISportsman toBind) {
        Calendar dateOfBirth = toBind.getDateOfBirth();
        dateOfBirthField.getModel().setDate(dateOfBirth.get(Calendar.YEAR), dateOfBirth.get(Calendar.MONTH), dateOfBirth.get(Calendar.DAY_OF_MONTH));
    }

    @Override
    public ISportsman unbind() {
        ISportsman sportsman = new Sportsman();
        sportsman.setFirstName(firstNameField.getText());
        sportsman.setMiddleName(middleNameField.getText());
        sportsman.setLastName(lastNameField.getText());
        sportsman.setDateOfBirth(getDateOfBirthFromField());
        sportsman.setCategory((ICategory) categoryField.getModel().getSelectedItem());
        sportsman.setStartNumber(startNumberField.getText());
        clearInputs();
        return sportsman;
    }

    private Calendar getDateOfBirthFromField() {
        DateModel<?> model = dateOfBirthField.getModel();

        Calendar dateOfBirth = Calendar.getInstance();
        dateOfBirth.set(Calendar.YEAR, model.getYear());
        dateOfBirth.set(Calendar.MONTH, model.getMonth());
        dateOfBirth.set(Calendar.DAY_OF_MONTH, model.getDay());

        dateOfBirth.set(Calendar.HOUR_OF_DAY, 0);
        dateOfBirth.set(Calendar.MINUTE, 0);
        dateOfBirth.set(Calendar.SECOND, 0);
        dateOfBirth.set(Calendar.MILLISECOND, 0);

        return dateOfBirth;
    }

    public Mode getMode() {
        return mode;
    }

    private void setMode(final Mode mode) {
        this.mode = mode;
        setTitle(appMessages.getString(mode.getTitleI18NKey()));
        if (startNumberField != null) {
            startNumberField.setEnabled(Mode.ADD.equals(mode));
        }
    }

    public void open(final Mode mode, final ISportsman sportsman, final Callback<List<ISportsman>> callback) {
        bind(sportsman);
        open(mode, callback);
    }

    public void open(final Mode mode, final Callback<List<ISportsman>> callback) {
        setMode(mode);
        super.open(callback);
    }

    @Override
    public void open(final ISportsman sportsman, final Callback<List<ISportsman>> callback) {
        open(Mode.ADD, sportsman, callback);
    }

    @Override
    public void open(Callback<List<ISportsman>> callback) {
        open(Mode.ADD, callback);
    }

    public enum Mode {

        ADD("label.add-sportsman"), EDIT("label.edit-sportsman");

        private String titleI18NKey;

        Mode(final String titleI18NKey) {
            this.titleI18NKey = titleI18NKey;
        }

        public String getTitleI18NKey() {
            return titleI18NKey;
        }
    }
}
