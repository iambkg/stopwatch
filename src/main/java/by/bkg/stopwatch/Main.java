package by.bkg.stopwatch;

import by.bkg.stopwatch.mvc.view.impl.StopWatchFrame;

import java.awt.*;

/**
 * Application entry point
 *
 * @author Alexey Baryshnev
 */
public class Main {

    public static void main(String [] args) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                StopWatchFrame stopWatchFrame = new StopWatchFrame();
            }
        });
    }
}
