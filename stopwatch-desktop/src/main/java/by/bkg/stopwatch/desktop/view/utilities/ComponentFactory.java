package by.bkg.stopwatch.desktop.view.utilities;

import by.bkg.stopwatch.desktop.model.IDocumentListenerAction;
import org.springframework.stereotype.Component;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.ActionListener;
import java.net.URL;

/**
 * <a href"mailto:alexey.baryshnev@ctco.lv">Alexey Baryshnev</a>
 */
@Component
public class ComponentFactory {

    public JToolBar createToolBar() {
        JToolBar toolBar = new JToolBar();
        toolBar.setFloatable(false);
        return toolBar;
    }

    public JButton createBtn(final String text, final ActionListener listener) {
        JButton btn = new JButton(text);
        btn.addActionListener(listener);
        return btn;
    }

    public JButton createBtn(final String iconPath, final String text, final String tooltip, final ActionListener listener) {
        JButton btn = createBtn(iconPath, tooltip, listener);
        btn.setText(text);
        return btn;
    }

    public JButton createBtn(final String iconPath, final String tooltip, final ActionListener listener) {
        JButton btn = new JButton();
        btn.setIcon(getImageIcon(iconPath));
        btn.setToolTipText(tooltip);
        btn.addActionListener(listener);
        return btn;
    }

    public ImageIcon getImageIcon(String iconPath) {
        ClassLoader cl = this.getClass().getClassLoader();
        URL imageUrl = cl.getResource(iconPath);
        return new ImageIcon(imageUrl);
    }

    public JLabel createValidationMessageLabel() {
        JLabel label = new JLabel();
        label.setForeground(Color.RED);
        return label;
    }

    public DocumentListener createDocumentListener(final IDocumentListenerAction action) {
        return new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                action.act();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                action.act();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                action.act();
            }
        };
    }
}
