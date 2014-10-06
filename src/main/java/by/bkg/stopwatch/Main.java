package by.bkg.stopwatch;

import by.bkg.stopwatch.mvc.controller.EventBus;
import by.bkg.stopwatch.mvc.controller.impl.StopWatchAppController;
import by.bkg.stopwatch.mvc.model.Person;
import by.bkg.stopwatch.mvc.view.impl.StopWatchFrame;

import javax.swing.*;
import java.awt.*;

/**
 * Application entry point
 *
 * @author Alexey Baryshnev
 */
public class Main {

    public static void main(String [] args) {
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
                new StopWatchFrame(new EventBus(), new StopWatchAppController()).setVisible(true);
            }
        });
    }

    private static Person createFakePerson() {
        Person person = new Person();
        person.setStartNumber(0);
        return person;
    }
}
