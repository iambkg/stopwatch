package by.bkg.timer;

import javax.swing.*;
import java.awt.*;

/**
 * @author Alexey Baryshnev
 */
public class MyTestFrame extends JFrame {
    StopWatch StopWatch1;

    public MyTestFrame() {
        super("My Stop Watch");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Container myPane = getContentPane();

        StopWatch1 = new StopWatch();
        StopWatch1.launchStopWatch();
        myPane.add(StopWatch1);
        pack();
        setVisible(true);
    }
}
