package by.bkg.stopwatch.desktop.view.component.dialog;

import by.bkg.stopwatch.core.model.ISplitRecord;
import by.bkg.stopwatch.core.model.SplitRecord;
import by.bkg.stopwatch.desktop.model.AppConstants;
import by.bkg.stopwatch.desktop.model.IDocumentListenerAction;
import by.bkg.stopwatch.desktop.model.validation.ValidationChain;
import by.bkg.stopwatch.desktop.model.validation.ValidationEntity;
import by.bkg.stopwatch.desktop.model.validation.ValidationResult;
import by.bkg.stopwatch.desktop.view.component.controller.EditSplitDialogController;
import by.bkg.stopwatch.desktop.view.i18n.AppMessages;
import by.bkg.stopwatch.desktop.view.model.Callback;
import by.bkg.stopwatch.desktop.view.utilities.ComponentFactory;
import by.bkg.stopwatch.desktop.view.utilities.SpringLayoutUtilities;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.swing.*;
import javax.swing.text.JTextComponent;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

/**
 * <a href"mailto:alexey.baryshnev@ctco.lv">Alexey Baryshnev</a>
 */
@Component
public class EditSplitDialog extends AbstractDialog<ISplitRecord, List<ISplitRecord>> {

    @Autowired
    private ComponentFactory componentFactory;

    @Autowired
    private AppMessages appMessages;

    @Autowired
    private EditSplitDialogController controller;

    private static final int MIN_WIDTH = 250;
    private static final int MIN_HEIGHT = 100;

    private static final Integer NUMBER_OF_ROWS = 2;
    private static final int NUMBER_OF_COLS = 2;

    private JTextField startNumberField;

    private JLabel startNumberErrorMsg;

    private JTextField timestampField;

    private Long hiddenSplitTime;

    private JButton okBtn;

    @Override
    public void init() {
        setMinimumSize(new Dimension(MIN_WIDTH, MIN_HEIGHT));
        setTitle(appMessages.getString("label.edit-split"));
        setModalityType(ModalityType.APPLICATION_MODAL);
        setIconImage(componentFactory.getImageIcon("icons/x16/Stopwatch.png").getImage());

        Container c = getContentPane();
        c.setLayout(new BorderLayout());

        JPanel formPanel = createFormPanel();

        add(formPanel, BorderLayout.CENTER);
        add(createButtonPanel(), BorderLayout.SOUTH);

        SpringLayoutUtilities.makeCompactGrid(formPanel, NUMBER_OF_ROWS, NUMBER_OF_COLS, INITIAL_X, INITIAL_Y, X_PAD, Y_PAD);

        pack();
        setLocationRelativeTo(null);
    }

    @Override
    protected JPanel createFormPanel() {
        JPanel formPanel = new JPanel();
        SpringLayout layout = new SpringLayout();
        formPanel.setLayout(layout);

        formPanel.add(new JLabel(appMessages.getString("label.start-number-long")));
        formPanel.add(createStartNumberField());

        formPanel.add(new JLabel(appMessages.getString("label.split-time-value")));
        formPanel.add(createTimestampField());

        return formPanel;
    }

    private JComponent createStartNumberField() {
        startNumberField = new JTextField();
        startNumberField.requestFocus();
        startNumberField.getDocument().addDocumentListener(componentFactory.createDocumentListener(new IDocumentListenerAction() {
            @Override
            public void act() {
                okBtn.setEnabled(true);
            }
        }));
        startNumberField.setInputVerifier(new InputVerifier() {
            @Override
            public boolean verify(final JComponent input) {
                ValidationChain<String> chain = new ValidationChain<String>()
                        .add(ValidationEntity.MANDATORY_TEXT_FIELD)
                        .add(ValidationEntity.DIGITS_TEXT_FIELD);
                List<ValidationResult> results = chain.validate(((JTextComponent) input).getText());
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

    private JComponent createTimestampField() {
        timestampField = new JTextField();
        timestampField.setEnabled(false);
        return timestampField;
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
        List<ISplitRecord> splits = controller.editSplit(unbind());
        getOperationPerformedCallback().execute(splits);
        close();
    }

    private void onCancelClick() {
        close();
    }

    @Override
    protected void clearInputs() {
        startNumberField.setText(AppConstants.EMPTY_STRING);
        timestampField.setText(AppConstants.EMPTY_STRING);
        hiddenSplitTime = null;
    }

    @Override
    public void bind(final ISplitRecord splitToEdit) {
        startNumberField.setText(splitToEdit.getStartNumber());
        timestampField.setText(splitToEdit.getTimestamp().getSplitTimeAsString());
        hiddenSplitTime = splitToEdit.getTimestamp().getSplitTime();
    }

    @Override
    public ISplitRecord unbind() {
        ISplitRecord split = new SplitRecord(startNumberField.getText(), hiddenSplitTime, timestampField.getText());
        clearInputs();
        return split;
    }

    @Override
    public void open(final ISplitRecord splitToEdit, Callback<List<ISplitRecord>> callback) {
        bind(splitToEdit);
        open(callback);
    }
}
