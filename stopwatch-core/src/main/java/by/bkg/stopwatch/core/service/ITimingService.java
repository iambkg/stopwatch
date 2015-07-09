package by.bkg.stopwatch.core.service;

import by.bkg.stopwatch.core.model.ITimestamp;

/**
 * <a href"mailto:alexey.baryshnev@ctco.lv">Alexey Baryshnev</a>
 */
public interface ITimingService {

    ITimestamp getTimestamp();

    void init();

    void start();

    void suspend();

    void resume();

    void stop();

    void reset();

    String getCurrentTime();
}
