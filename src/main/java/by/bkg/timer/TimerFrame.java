package by.bkg.timer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @author Alexey Baryshnev
 */
public class TimerFrame extends JFrame {

    public void display() {
        this.setTitle("TimerFrame");
        this.setLayout(new GridLayout(0, 1));
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.add(new TimerButton("Back in a second", 1000));
        this.add(new TimerButton("Back in a minute", 60 * 1000));
        this.add(new TimerButton("Back in an hour", 60 * 60 * 1000));
        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    /**
     * A button that hides it's enclosing Window for delay ms.
     */
    private class TimerButton extends JButton {

        private final Timer timer;

        public TimerButton(String text, int delay) {
            super(text);
            this.addActionListener(new StartListener());
            timer = new Timer(delay, new StopListener());
        }

        private class StartListener implements ActionListener {

            @Override
            public void actionPerformed(ActionEvent e) {
                TimerFrame.this.setVisible(false);
                timer.start();
            }
        }

        private class StopListener implements ActionListener {

            @Override
            public void actionPerformed(ActionEvent e) {
                timer.stop();
                TimerFrame.this.setVisible(true);
            }
        }
    }
}
