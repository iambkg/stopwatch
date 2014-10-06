package by.bkg.stopwatch;

import by.bkg.stopwatch.mvc.controller.factory.ControllerFactory;
import by.bkg.stopwatch.mvc.controller.IEventBus;
import by.bkg.stopwatch.mvc.controller.IStopWatchAppController;
import by.bkg.stopwatch.mvc.view.StopWatchFrame;

import javax.swing.*;
import java.awt.*;

/**
 * Application entry point
 *
 * @author Alexey Baryshnev
 */
public class Main {

    // TODO ABA: make autowired after Spring integration implemented
    private static IEventBus eventBus = ControllerFactory.getEventBus();

    // TODO ABA: make autowired after Spring integration implemented
    private static IStopWatchAppController appController = ControllerFactory.getAppController();

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new StopWatchFrame(eventBus, appController).setVisible(true);
            }
        });
    }
}
