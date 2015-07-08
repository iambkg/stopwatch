package by.bkg.stopwatch.desktop.view.v2.component.dialog;

import by.bkg.stopwatch.core.model.Category;
import by.bkg.stopwatch.core.model.ISportsman;
import by.bkg.stopwatch.core.model.ISportsmanData;
import by.bkg.stopwatch.core.model.SportsmanData;
import by.bkg.stopwatch.core.model.enums.Sex;
import by.bkg.stopwatch.desktop.view.i18n.AppMessages;
import by.bkg.stopwatch.desktop.view.v2.component.controller.RegisteredSportsmanDialogController;
import by.bkg.stopwatch.desktop.view.v2.model.Callback;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.swing.*;
import javax.tools.FileObject;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
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

    private static final int MIN_WIDTH = 400;

    private static final int MIN_HEIGHT = 220;

    private JLabel sportsmenLabel;

    public void init() {
        sportsmanDialog.init();
        sportsmenLabel = new JLabel();
        sportsmenLabel.setVerticalAlignment(SwingConstants.TOP);

        setMinimumSize(new Dimension(MIN_WIDTH, MIN_HEIGHT));
        setTitle(appMessages.getString("label.sportsmen"));

        Container c = getContentPane();
        c.setLayout(new BorderLayout());

//        JPanel formPanel = createFormPanel();

        add(createLogicButtonPanel(), BorderLayout.NORTH);
//        add(formPanel, BorderLayout.CENTER);
        add(sportsmenLabel, BorderLayout.CENTER);
        add(createButtonPanel(), BorderLayout.SOUTH);

        pack();
    }

    private JComponent createLogicButtonPanel() {
        JPanel btnPanel = new JPanel();

        btnPanel.add(createAddBtn());
        btnPanel.add(createEditBtn());
        btnPanel.add(createDeleteBtn());
        return btnPanel;
    }

    private JComponent createAddBtn() {
        JButton btn = new JButton(appMessages.getString("btn.add-person"));
        btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sportsmanDialog.open(SportsmanDialog.Mode.ADD, new Callback<java.util.List<ISportsman>>() {
                    @Override
                    public void execute(List<ISportsman> refreshedSportsmenList) {
                        setSportsmen(refreshedSportsmenList);
                    }
                });
            }
        });
        return btn;
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

    private JComponent createEditBtn() {
        JButton btn = new JButton(appMessages.getString("btn.edit-person"));
        btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sportsmanDialog.open(SportsmanDialog.Mode.EDIT, getSelectedSportsman(), new Callback<java.util.List<ISportsman>>() {
                    @Override
                    public void execute(List<ISportsman> refreshedSportsmenList) {
                        setSportsmen(refreshedSportsmenList);
                    }
                });
            }
        });
        return btn;
    }

    private JComponent createDeleteBtn() {
        JButton btn = new JButton(appMessages.getString("btn.delete-person"));
        btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                getController().delete(getSelectedSportsman(), new Callback<java.util.List<ISportsman>>() {
                    @Override
                    public void execute(List<ISportsman> refreshedSportsmenList) {
                        setSportsmen(refreshedSportsmenList);
                    }
                });
            }
        });
        return btn;
    }

    private JComponent createButtonPanel() {
        JPanel btnPanel = new JPanel();

        btnPanel.add(createCloseBtn());
        return btnPanel;
    }

    private JComponent createCloseBtn() {
        JButton btn = new JButton(appMessages.getString("btn.close"));
        btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                onCloseClick();
            }
        });
        return btn;
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
