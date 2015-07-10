package by.bkg.stopwatch.desktop.view.component.dialog;

import by.bkg.stopwatch.core.model.Category;
import by.bkg.stopwatch.core.model.ISportsman;
import by.bkg.stopwatch.core.model.ISportsmanData;
import by.bkg.stopwatch.core.model.SportsmanData;
import by.bkg.stopwatch.core.model.enums.Sex;
import by.bkg.stopwatch.desktop.view.i18n.AppMessages;
import by.bkg.stopwatch.desktop.view.component.controller.RegisteredSportsmanDialogController;
import by.bkg.stopwatch.desktop.view.model.Callback;
import by.bkg.stopwatch.desktop.view.utilities.ComponentFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import java.util.List;

/**
 * @author Alexey Baryshnev
 */
@Component
public class RegisteredSportsmanDialog extends JDialog {

    @Autowired
    private SportsmanDialog sportsmanDialog;

    @Autowired
    private AppMessages appMessages;

    @Autowired
    private RegisteredSportsmanDialogController controller;

    @Autowired
    private ComponentFactory componentFactory;

    private static final int MIN_WIDTH = 400;

    private static final int MIN_HEIGHT = 220;

    private JLabel sportsmenLabel;

    public void init() {
        sportsmanDialog.init();
        sportsmanDialog.setLocationRelativeTo(this);

        sportsmenLabel = new JLabel();
        sportsmenLabel.setVerticalAlignment(SwingConstants.TOP);

        setMinimumSize(new Dimension(MIN_WIDTH, MIN_HEIGHT));
        setTitle(appMessages.getString("label.sportsmen"));

        Container c = getContentPane();
        c.setLayout(new BorderLayout());

//        JPanel formPanel = createFormPanel();

        add(createLogicButtonPanel(), BorderLayout.NORTH);
//        add(formPanel, BorderLayout.CENTER);
        add(sportsmenLabel, BorderLayout.CENTER); // TODO ABA: add scrollpanel
        add(createButtonPanel(), BorderLayout.SOUTH);

        pack();
    }

    private JComponent createLogicButtonPanel() {
        JToolBar toolBar = componentFactory.createToolBar();

        toolBar.add(componentFactory.createBtn(appMessages.getString("btn.add-person"), createAddPersonBtnListener()));
        toolBar.add(componentFactory.createBtn(appMessages.getString("btn.edit-person"), createEditBtnActionListener()));
        toolBar.add(componentFactory.createBtn(appMessages.getString("btn.delete-person"), createDeleteBtnActionListener()));
        return toolBar;
    }

    private ActionListener createAddPersonBtnListener() {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sportsmanDialog.open(SportsmanDialog.Mode.ADD, new Callback<List<ISportsman>>() {
                    @Override
                    public void execute(List<ISportsman> refreshedSportsmenList) {
                        setSportsmen(refreshedSportsmenList);
                    }
                });
            }
        };
    }

    private ActionListener createEditBtnActionListener() {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sportsmanDialog.open(SportsmanDialog.Mode.EDIT, getSelectedSportsman(), new Callback<List<ISportsman>>() {
                    @Override
                    public void execute(List<ISportsman> refreshedSportsmenList) {
                        setSportsmen(refreshedSportsmenList);
                    }
                });
            }
        };
    }

    private ActionListener createDeleteBtnActionListener() {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                getController().delete(getSelectedSportsman(), new Callback<List<ISportsman>>() {
                    @Override
                    public void execute(List<ISportsman> refreshedSportsmenList) {
                        setSportsmen(refreshedSportsmenList);
                    }
                });
            }
        };
    }

    private void setSportsmen(List<ISportsman> sportsmen) {
        // TODO ABA: use correct component
        String listAsString = "<html><body>";
        for (ISportsman sportsman : sportsmen) {
            listAsString += String.format("%s. %s %s %s - %s<br/>", sportsman.getStartNumber(), sportsman.getLastName(), sportsman.getFirstName(), sportsman.getMiddleName(), sportsman.getCategory().getName());
        }
        listAsString += "</body></html>";
        sportsmenLabel.setText(listAsString);
    }

    private JComponent createButtonPanel() {
        JPanel btnPanel = new JPanel();

        btnPanel.add(componentFactory.createBtn(appMessages.getString("btn.close"), createCloseBtnListener()));
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

    public ISportsmanData getSelectedSportsman() {
        // TODO ABA:
        // call controller
        // pass selected start number to controller
        // controller gets Event from LogicService and searches selected Sportsman
        // return found sportsman (sportsmanData)
        return new SportsmanData("fake", "fake", "fake", new Date(), Sex.MALE, new Category("dsdsd"), "123");
    }

    public RegisteredSportsmanDialogController getController() {
        return controller;
    }
}
