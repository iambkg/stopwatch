package by.bkg.stopwatch.core.service;

import by.bkg.stopwatch.core.model.SplitRecord;

/**
 * <a href"mailto:alexey.baryshnev@ctco.lv">Alexey Baryshnev</a>
 */
public interface ITimingService {

    void init();

    void start();

    void suspend();

    void resume();

    void stop();

    void reset();

    SplitRecord split(String startNumber);

    String getCurrentTime();
}
