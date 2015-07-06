package by.bkg.stopwatch.pure.service;

import by.bkg.stopwatch.pure.model.Event;
import by.bkg.stopwatch.pure.model.ISportsman;
import by.bkg.stopwatch.pure.model.ISportsmanData;

import java.util.List;

/**
 * @author Alexey Baryshnev
 */
public interface IService {

    List<ISportsman> addSportsman(ISportsmanData sportsmanData);

    List<ISportsman> editSportsman(ISportsmanData sportsmanData);

    Event getEvent();
}
