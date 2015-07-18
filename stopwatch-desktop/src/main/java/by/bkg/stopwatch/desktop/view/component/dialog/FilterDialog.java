package by.bkg.stopwatch.desktop.view.component.dialog;

import by.bkg.stopwatch.core.model.FilterCriteria;
import by.bkg.stopwatch.desktop.view.i18n.AppMessages;
import by.bkg.stopwatch.desktop.view.model.factory.DataFactory;
import by.bkg.stopwatch.desktop.view.utilities.ComponentFactory;
import by.bkg.stopwatch.desktop.view.utilities.SpringLayoutUtilities;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @author Alexey Baryshnev
 */
@Component
public class FilterDialog extends AbstractDialog<FilterCriteria> {

    @Autowired
    private ComponentFactory componentFactory;

    @Autowired
    private AppMessages appMessages;

    @Autowired
    private DataFactory dataFactory;

    private static final int MIN_WIDTH = 400;
    private static final int MIN_HEIGHT = 50;

    private static final Integer NUMBER_OF_ROWS = 2;
    private static final int NUMBER_OF_COLS = 2;

    private JComboBox categoryField;
    private JCheckBox categoryCheckbox;
    private JComboBox sexField;
    private JCheckBox sexCheckbox;

    @Override
    public void init() {
        setMinimumSize(new Dimension(MIN_WIDTH, MIN_HEIGHT));
        setTitle(appMessages.getString("label.filters"));
        // TODO ABA: set icon

        Container c = getContentPane();
        c.setLayout(new BorderLayout());

        JPanel formPanel = createFormPanel();

        add(formPanel, BorderLayout.CENTER);
        add(createButtonPanel(), BorderLayout.SOUTH);

        SpringLayoutUtilities.makeCompactGrid(formPanel, NUMBER_OF_ROWS, NUMBER_OF_COLS, INITIAL_X, INITIAL_Y, X_PAD, Y_PAD);
        clearInputs();

        pack();
    }

    @Override
    protected JPanel createFormPanel() {
        JPanel formPanel = new JPanel();
        SpringLayout layout = new SpringLayout();
        formPanel.setLayout(layout);

        formPanel.add(createCategoryCheckbox());
        formPanel.add(createCategoryField());

        formPanel.add(createSexCheckbox());
        formPanel.add(createSexField());

        return formPanel;
    }

    private JComponent createSexCheckbox() {
        sexCheckbox = new JCheckBox(appMessages.getString("label.sex"));
        sexCheckbox.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                sexField.setEnabled(sexCheckbox.isSelected());
            }
        });
        return sexCheckbox;
    }

    private JComponent createCategoryCheckbox() {
        categoryCheckbox = new JCheckBox(appMessages.getString("label.category"));
        categoryCheckbox.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                categoryField.setEnabled(categoryCheckbox.isSelected());
            }
        });
        return categoryCheckbox;
    }

    private JComponent createCategoryField() {
        categoryField = new JComboBox();
        categoryField.setModel(new DefaultComboBoxModel(dataFactory.getAvailableCategories()));
        categoryField.setSelectedIndex(0);
        categoryField.setEnabled(false);
        return categoryField;
    }

    private JComponent createSexField() {
        sexField = new JComboBox();
        sexField.setModel(new DefaultComboBoxModel(dataFactory.getSexValues()));
        sexField.setSelectedIndex(0);
        sexField.setEnabled(false);
        return sexField;
    }

    @Override
    protected JComponent createButtonPanel() {
        JPanel btnPanel = new JPanel();
        btnPanel.add(componentFactory.createBtn("icons/x16/Symbol-Check.png", appMessages.getString("btn.ok"), appMessages.getString("btn.ok"), createOkBtnListener()));
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
        close();
    }

    private void onCancelClick() {
        close();
    }

    @Override
    protected void clearInputs() {
        categoryField.setSelectedIndex(0);
        sexField.setSelectedIndex(0);
        categoryCheckbox.setSelected(false);
        sexCheckbox.setSelected(false);
    }

    @Override
    public void bind(FilterCriteria criteria) {
//        selectSex(toBind);
//        selectCategory(toBind);
    }

//    private void selectSex(ISportsman toBind) {
//            for (int i = 0; i < sexField.getModel().getSize(); i++) {
//                SexModel sexModel = (SexModel) sexField.getModel().getElementAt(i);
//                if (sexModel.getSex().equals(toBind.getSex())) {
//                    sexField.setSelectedItem(sexModel);
//                    return;
//                }
//            }
//        }
//
//        private void selectCategory(ISportsman toBind) {
//            for (int i = 0; i < categoryField.getModel().getSize(); i++) {
//                ICategory category = (ICategory) categoryField.getModel().getElementAt(i);
//                if (category.equals(toBind.getCategory())) {
//                    categoryField.setSelectedItem(category);
//                    return;
//                }
//            }
//        }

    @Override
    public FilterCriteria unbind() {
//        sportsman.setSex(((SexModel) sexField.getModel().getSelectedItem()).getSex());
//                sportsman.setCategory((ICategory) categoryField.getModel().getSelectedItem());
        clearInputs();
        return null;
    }
}
