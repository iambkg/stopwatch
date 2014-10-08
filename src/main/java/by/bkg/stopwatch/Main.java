package by.bkg.stopwatch;

import by.bkg.stopwatch.mvc.view.StopWatchFrame;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.swing.*;
import java.awt.*;

/**
 * Application entry point
 *
 * @author Alexey Baryshnev
 */
public class Main {

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
                ApplicationContext context = new ClassPathXmlApplicationContext("application-context.xml");
                ((StopWatchFrame) context.getBean("stopWatchFrame")).init();
            }
        });
    }
}
