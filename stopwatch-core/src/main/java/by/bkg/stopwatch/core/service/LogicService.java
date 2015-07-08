package by.bkg.stopwatch.core.service;

import by.bkg.stopwatch.core.model.Event;
import by.bkg.stopwatch.core.model.IEvent;
import by.bkg.stopwatch.core.model.ISplitRecord;
import by.bkg.stopwatch.core.model.ISportsman;
import by.bkg.stopwatch.core.model.ISportsmanData;
import by.bkg.stopwatch.core.model.SplitRecord;
import by.bkg.stopwatch.core.model.Sportsman;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Alexey Baryshnev
 */
@Service
public class LogicService implements ILogicService {

    @Autowired
    private ITimingService timingService;

    @Autowired
    private ILoggingService loggingService;

    private Event event;

    public LogicService() {
        this.event = new Event();
    }

    @Override
    public List<ISportsman> addSportsman(ISportsmanData sportsmanData) {
        getEvent().getSportsmen().add(createSportsman(sportsmanData));
        loggingService.log(getEvent().getSportsmen());
        return getEvent().getSportsmen();
    }

    @Override
    public List<ISportsman> editSportsman(ISportsmanData sportsmanData) {
        ISportsman sportsman = findSportsman(sportsmanData.getStartNumber());
        if (sportsman != null) {
            sportsman.refresh(sportsmanData);
        } else {
            loggingService.error(String.format("Could not find sportsman (ID = %s) among event members", sportsmanData.getStartNumber()));
        }
        return getEvent().getSportsmen();
    }

    @Override
    public List<ISplitRecord> doSplit(String startNumber) {
        List<ISplitRecord> splits = getEvent().getSplits();
        splits.add(createSplit(startNumber));
        return splits;
    }

    private ISplitRecord createSplit(String startNumber) {
        ISplitRecord split = new SplitRecord();
        split.setStartNumber(startNumber);
        split.setTimestamp(timingService.getTimestamp());
        return split;
    }

    @Override
    public void flush() {
        getEvent().getSplits().clear();
        getEvent().getSportsmen().clear();
    }

    private ISportsman findSportsman(String startNumber) {
        for (ISportsman sportsman : getEvent().getSportsmen()) {
            if (startNumber.equals(sportsman.getStartNumber())) {
                return sportsman;
            }
        }
        return null;
    }

    private Sportsman createSportsman(ISportsmanData sportsmanData) {
        return new Sportsman(sportsmanData);
    }

    public IEvent getEvent() {
        return event;
    }
}
