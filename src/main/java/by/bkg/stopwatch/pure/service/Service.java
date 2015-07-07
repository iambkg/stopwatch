package by.bkg.stopwatch.pure.service;

import by.bkg.stopwatch.pure.model.Event;
import by.bkg.stopwatch.pure.model.ISportsman;
import by.bkg.stopwatch.pure.model.ISportsmanData;
import by.bkg.stopwatch.pure.model.Sportsman;

import java.util.List;

/**
 * @author Alexey Baryshnev
 */
public class Service implements IService {

    private Event event;

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
            // TODO ABA: LOG
        }
        // TODO ABA: check that all data is refreshed
        return getEvent().getSportsmen();
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

    public Event getEvent() {
        return event;
    }
}
