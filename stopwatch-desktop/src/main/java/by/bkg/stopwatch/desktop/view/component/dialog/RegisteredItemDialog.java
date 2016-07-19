package by.bkg.stopwatch.desktop.view.component.dialog;

import by.bkg.stopwatch.desktop.view.component.controller.RegisteredDialogController;
import by.bkg.stopwatch.desktop.view.i18n.AppMessages;
import by.bkg.stopwatch.desktop.view.model.Callback;
import by.bkg.stopwatch.desktop.view.utilities.ComponentFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

/**
 * @author Alexey Baryshnev
 */
@Component
public class RegisteredItemDialog<T> extends JDialog {    // TODO ABA: make it to extend AbstractDialog

    @Autowired
    private AppMessages appMessages;

    @Autowired
    private ComponentFactory componentFactory;

    private static final int MIN_WIDTH = 300;

    private static final int MIN_HEIGHT = 220;

    private JList itemsList;

    private JButton editPersonBtn;

    private JButton deletePersonBtn;

    private RegisteredDialogController<T> controller;

    public void init(RegisteredDialogController<T> controller) {
        this.controller = controller;

        getController().init(this);

        itemsList = new JList();
        itemsList.setModel(new DefaultListModel());
        itemsList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        itemsList.setLayoutOrientation(JList.VERTICAL);
        itemsList.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                boolean sportsmanSelected = !itemsList.isSelectionEmpty();
                editPersonBtn.setEnabled(sportsmanSelected);
                deletePersonBtn.setEnabled(sportsmanSelected);
            }
        });
        itemsList.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(final MouseEvent evt) {
                if (evt.getClickCount() == 2) {
                    // double-click
                    getController().startEditing(getSelectedItem(), getShowItemsCallback());
                }
            }

            @Override
            public void mousePressed(final MouseEvent evt) {
                if (SwingUtilities.isRightMouseButton(evt)) {
                    int index = itemsList.locationToIndex(evt.getPoint());
                    getController().doRemove((T) itemsList.getModel().getElementAt(index), getShowItemsCallback());
                }
            }
        });

        setMinimumSize(new Dimension(MIN_WIDTH, MIN_HEIGHT));
        setTitle(getController().getTitle());
        setModalityType(ModalityType.APPLICATION_MODAL);
        setIconImage(componentFactory.getImageIcon("icons/x16/Buddy.png").getImage());

        Container c = getContentPane();
        c.setLayout(new BorderLayout());

        add(createLogicButtonPanel(), BorderLayout.NORTH);
        add(new JScrollPane(itemsList), BorderLayout.CENTER);
        add(createButtonPanel(), BorderLayout.SOUTH);

        pack();
        setLocationRelativeTo(null);
    }

    private Callback<List<T>> getShowItemsCallback() {
        return new Callback<List<T>>() {
            @Override
            public void execute(List<T> refreshedItemsList) {
                showSportsmenList(refreshedItemsList);
            }
        };
    }

    private JComponent createLogicButtonPanel() {
        JToolBar toolBar = componentFactory.createToolBar();
        editPersonBtn = componentFactory.createBtn("icons/x16/Edit.png", getController().getEditLabel(), new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                getController().startEditing(getSelectedItem(), getShowItemsCallback());
            }
        });
        editPersonBtn.setEnabled(false);
        deletePersonBtn = componentFactory.createBtn("icons/x16/Symbol-Remove.png", getController().getDeleteLabel(), new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                getController().doRemove(getSelectedItem(), getShowItemsCallback());
            }
        });
        deletePersonBtn.setEnabled(false);

        toolBar.add(componentFactory.createBtn("icons/x16/Symbol-Add.png", getController().getAddLabel(), new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                getController().addItem(e, getShowItemsCallback());
            }
        }));
        toolBar.add(editPersonBtn);
        toolBar.add(deletePersonBtn);
        return toolBar;
    }

    private void showSportsmenList(final List<T> sportsmen) {
        DefaultListModel model = (DefaultListModel) itemsList.getModel();
        model.clear();
        for (T sportsman : sportsmen) {
            model.addElement(sportsman);
        }
    }

    private JComponent createButtonPanel() {
        JPanel btnPanel = new JPanel();

        btnPanel.add(componentFactory.createBtn("icons/x16/Symbol-Check.png", appMessages.getString("btn.close"), appMessages.getString("btn.close"), createCloseBtnListener()));
        return btnPanel;
    }

    private ActionListener createCloseBtnListener() {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                onCloseClick();
            }
        };
    }

    private void onCloseClick() {
        setVisible(false);
    }

    public RegisteredDialogController<T> getController() {
        return controller;
    }

    public void open() {
        showSportsmenList(getController().getAll());
        setVisible(true);
    }

    private T getSelectedItem() {
        return (T) itemsList.getSelectedValue();
    }
}
