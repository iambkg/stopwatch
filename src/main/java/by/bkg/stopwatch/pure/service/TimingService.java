package by.bkg.stopwatch.pure.service;

import by.bkg.stopwatch.pure.model.ITimestamp;
import by.bkg.stopwatch.pure.model.Timestamp;

import java.util.Date;

/**
 * <a href"mailto:alexey.baryshnev@ctco.lv">Alexey Baryshnev</a>
 */
@org.springframework.stereotype.Service
public class TimingService implements ITimingService {

    @Override
    public ITimestamp getTimestamp() {
        return new Timestamp(new Date());
    }
}
