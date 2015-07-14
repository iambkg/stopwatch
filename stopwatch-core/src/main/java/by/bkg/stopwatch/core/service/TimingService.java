package by.bkg.stopwatch.core.service;

import by.bkg.stopwatch.core.model.SplitRecord;
import org.apache.commons.lang.time.StopWatch;
import org.springframework.stereotype.Service;

/**
 * <a href"mailto:alexey.baryshnev@ctco.lv">Alexey Baryshnev</a>
 */
@Service
public class TimingService implements ITimingService {

    private StopWatch stopWatch;

    public static final String ZERO_TIME = "00:00:00.000";

    @Override
    public void init() {
        stopWatch = new StopWatch();
    }

    @Override
    public void start() {
        stopWatch.start();
    }

    @Override
    public void suspend() {
        stopWatch.suspend();
    }

    @Override
    public void resume() {
        stopWatch.resume();
    }

    @Override
    public void stop() {
        stopWatch.stop();
    }

    @Override
    public void reset() {
        stopWatch.reset();
    }

    @Override
    public String getCurrentTime() {
        if (stopWatch == null) {
            return ZERO_TIME;
        }
        return stopWatch.toString();
    }

    @Override
    public SplitRecord split(final String startNumber) {
        stopWatch.split();
        SplitRecord split = new SplitRecord(startNumber, stopWatch.getSplitTime(), stopWatch.toSplitString());
        stopWatch.unsplit();
        return split;
    }
}
