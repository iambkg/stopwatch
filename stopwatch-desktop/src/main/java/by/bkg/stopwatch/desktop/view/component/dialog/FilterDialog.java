package by.bkg.stopwatch.desktop.view.component.dialog;

import by.bkg.stopwatch.core.model.FilterCriteria;
import by.bkg.stopwatch.core.model.ICategory;
import by.bkg.stopwatch.core.model.enums.FilterType;
import by.bkg.stopwatch.core.model.enums.Sex;
import by.bkg.stopwatch.desktop.view.i18n.AppMessages;
import by.bkg.stopwatch.desktop.view.model.SexModel;
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
import java.util.ArrayList;
import java.util.List;

/**
 * @author Alexey Baryshnev
 */
@Component
public class FilterDialog extends AbstractDialog<List<FilterCriteria>, List<FilterCriteria>> {

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
        setIconImage(componentFactory.getImageIcon("icons/x16/Filter.png").getImage());

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
        btnPanel.add(componentFactory.createBtn("icons/x16/Symbol-Check.png", appMessages.getString("btn.done"), appMessages.getString("btn.done"), createOkBtnListener()));
        btnPanel.add(componentFactory.createBtn("icons/x16/Symbol-Delete.png", appMessages.getString("btn.clear"), appMessages.getString("btn.clear"), createClearBtnListener()));
        return btnPanel;
    }

    private ActionListener createOkBtnListener() {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                getOperationPerformedCallback().execute(unbind());
                close();
            }
        };
    }

    private ActionListener createClearBtnListener() {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clearInputs();
            }
        };
    }

    @Override
    protected void clearInputs() {
        categoryField.setSelectedIndex(0);
        sexField.setSelectedIndex(0);
        categoryCheckbox.setSelected(false);
        sexCheckbox.setSelected(false);
    }

    @Override
    public void bind(List<FilterCriteria> criterias) {
        for (FilterCriteria criteria : criterias) {
            switch (criteria.getFilterType()) {
                case BY_SEX:
                    selectSex((Sex) criteria.getValue());
                    break;
                case BY_CATEGORY:
                    selectCategory((ICategory) criteria.getValue());
                    break;
                default:
                    break;
            }
        }
    }

    private void selectSex(Sex sex) {
        for (int i = 0; i < sexField.getModel().getSize(); i++) {
            SexModel sexModel = (SexModel) sexField.getModel().getElementAt(i);
            if (sexModel.getSex().equals(sex)) {
                sexField.setSelectedItem(sexModel);
                return;
            }
        }
    }

    private void selectCategory(ICategory xCategory) {
        for (int i = 0; i < categoryField.getModel().getSize(); i++) {
            ICategory category = (ICategory) categoryField.getModel().getElementAt(i);
            if (category.getName().equals(xCategory.getName())) {
                categoryField.setSelectedItem(category);
                return;
            }
        }
    }

    @Override
    public List<FilterCriteria> unbind() {
        List<FilterCriteria> result = new ArrayList<FilterCriteria>();

        if (sexCheckbox.isSelected()) {
            FilterCriteria sexCriteria = new FilterCriteria();
            sexCriteria.setFilterType(FilterType.BY_SEX);
            sexCriteria.setValue(((SexModel) sexField.getModel().getSelectedItem()).getSex());
            result.add(sexCriteria);
        }

        if (categoryCheckbox.isSelected()) {
            FilterCriteria categoryCriteria = new FilterCriteria();
            categoryCriteria.setFilterType(FilterType.BY_CATEGORY);
            categoryCriteria.setValue(((ICategory) categoryField.getModel().getSelectedItem()).getName());
            result.add(categoryCriteria);
        }

        return result;
    }

    @Override
    public void close() {
        setOperationPerformedCallback(null);
        setVisible(false);
    }
}
