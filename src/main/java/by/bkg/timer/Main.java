package by.bkg.timer;

import java.awt.*;

/**
 * @author Alexey Baryshnev
 */
public class Main {

    public static void main(String [] args) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                StopWatchFrame stopWatchFrame1 = new StopWatchFrame();
            }
        });
    }
}
