package by.bkg.stopwatch.pure.service;

import by.bkg.stopwatch.pure.model.Event;
import by.bkg.stopwatch.pure.model.IEvent;
import by.bkg.stopwatch.pure.model.ISportsman;
import by.bkg.stopwatch.pure.model.ISportsmanData;
import by.bkg.stopwatch.pure.model.Sportsman;
import org.apache.log4j.Logger;

import java.util.List;

/**
 * @author Alexey Baryshnev
 */
@org.springframework.stereotype.Service
public class Service implements IService {

    private Event event;

    private Logger log = Logger.getLogger(Service.class);

    public Service() {
        this.event = new Event();
    }

    @Override
    public List<ISportsman> addSportsman(ISportsmanData sportsmanData) {
        getEvent().getSportsmen().add(createSportsman(sportsmanData));
        return getEvent().getSportsmen();
    }

    @Override
    public List<ISportsman> editSportsman(ISportsmanData sportsmanData) {
        ISportsman sportsman = findSportsman(sportsmanData.getId());
        if (sportsman != null) {
            sportsman.refresh(sportsmanData);
        } else {
            log.error(String.format("Could not find sportsman (ID = %s) among event members", sportsmanData.getId()));
        }
        return getEvent().getSportsmen();
    }

    @Override
    public void flush() {
        getEvent().getSplits().clear();
        getEvent().getSportsmen().clear();
    }

    private ISportsman findSportsman(Long id) {
        for (ISportsman sportsman : getEvent().getSportsmen()) {
            if (id.equals(sportsman.geId())) {
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
