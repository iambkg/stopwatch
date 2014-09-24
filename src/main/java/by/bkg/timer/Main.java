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
                MyTestFrame myTestFrame1 = new MyTestFrame();
            }
        });
    }
}
