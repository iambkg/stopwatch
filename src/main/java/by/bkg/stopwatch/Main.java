package by.bkg.stopwatch;

import by.bkg.stopwatch.mvc.controller.EventBus;
import by.bkg.stopwatch.mvc.controller.impl.StopWatchAppController;
import by.bkg.stopwatch.mvc.model.Person;
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
