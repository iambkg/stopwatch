package by.bkg.stopwatch.core.service;

import by.bkg.stopwatch.core.model.Event;
import by.bkg.stopwatch.core.model.IEvent;
import by.bkg.stopwatch.core.model.ISplitRecord;
import by.bkg.stopwatch.core.model.ISportsman;
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
    public void init() {
        startNewEvent();
        timingService.init();
    }

    @Override
    public List<ISplitRecord> startNewEvent() {
        event = new Event();
        return getEvent().getSplits();
    }

    @Override
    public List<ISportsman> getSportsmen() {
        return getEvent().getSportsmen();
    }

    @Override
    public List<ISportsman> addSportsman(ISportsman sportsman) {
        getEvent().getSportsmen().add(createSportsman(sportsman));
        loggingService.log(getEvent().getSportsmen());
        return getEvent().getSportsmen();
    }

    @Override
    public List<ISportsman> editSportsman(ISportsman sportsmanToEdit) {
        ISportsman sportsman = findSportsman(sportsmanToEdit.getStartNumber());
        if (sportsman != null) {
            sportsman.refresh(sportsmanToEdit);
        } else {
            loggingService.error(String.format("EDIT: Could not find sportsman (ID = %s) among event members", sportsmanToEdit.getStartNumber()));
        }
        return getEvent().getSportsmen();
    }

    @Override
    public List<ISportsman> deleteSportsman(ISportsman sportsmanToDelete) {
        ISportsman sportsman = findSportsman(sportsmanToDelete.getStartNumber());
        if (sportsman != null) {
            getEvent().getSportsmen().remove(sportsman);
        } else {
            loggingService.error(String.format("DELETE: Could not find sportsman (ID = %s) among event members", sportsmanToDelete.getStartNumber()));
        }
        return getEvent().getSportsmen();
    }

    @Override
    public List<ISplitRecord> doSplit(String startNumber) {
        List<ISplitRecord> splits = getEvent().getSplits();
        splits.add(timingService.split(startNumber));
        return splits;
    }

    @Override
    public void startEvent() {
        timingService.start();
    }

    @Override
    public void stopEvent() {
        timingService.stop();
        timingService.reset();
    }

    @Override
    public void suspendEvent() {
        timingService.suspend();
    }

    @Override
    public void resumeEvent() {
        timingService.resume();
    }

    @Override
    public void flush() {
        timingService.init();
        getEvent().getSplits().clear();
        getEvent().getSportsmen().clear();
    }

    @Override
    public List<ISplitRecord> editSplit(ISplitRecord splitToEdit) {
        ISplitRecord split = findSplit(splitToEdit.getTimestamp().getSplitTimeAsString());
        if (split != null) {
            split.refresh(splitToEdit);
        } else {
            loggingService.error(String.format("EDIT SPLIT: Could not find split"));
        }
        return getEvent().getSplits();
    }

    private ISplitRecord findSplit(String splitTimeAsString) {
        for (ISplitRecord split : getEvent().getSplits()) {
            if (split.getTimestamp().getSplitTimeAsString().equals(splitTimeAsString)) {
                return split;
            }
        }
        loggingService.error("Did not find split");
        return null;
    }

    @Override
    public List<ISplitRecord> deleteSplit(ISplitRecord splitToDelete) {
        List<ISplitRecord> splits = getEvent().getSplits();
        if (splits.contains(splitToDelete)) {
            splits.remove(splitToDelete);
        } else {
            loggingService.error(String.format("Trying to delete split which is not in splits list. No action taken."));
        }
        return splits;
    }

    @Override
    public String getCurrentTime() {
        return timingService.getCurrentTime();
    }

    private ISportsman findSportsman(String startNumber) {
        for (ISportsman sportsman : getEvent().getSportsmen()) {
            if (startNumber.equals(sportsman.getStartNumber())) {
                return sportsman;
            }
        }
        loggingService.error(String.format("Did not find sportsman (start number %s)", startNumber));
        return null;
    }

    private Sportsman createSportsman(ISportsman sportsman) {
        return new Sportsman(sportsman);
    }

    public IEvent getEvent() {
        return event;
    }
}
