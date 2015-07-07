package by.bkg.stopwatch.pure.service;

import by.bkg.stopwatch.pure.model.IEvent;
import by.bkg.stopwatch.pure.model.ISportsman;
import by.bkg.stopwatch.pure.model.ISportsmanData;

import java.util.List;

/**
 * @author Alexey Baryshnev
 */
public interface IService {

    List<ISportsman> addSportsman(ISportsmanData sportsmanData);

    List<ISportsman> editSportsman(ISportsmanData sportsmanData);

    IEvent getEvent();

    /**
     * Reset Event: clear all stored sportsmen, split records
     */
    void flush();
}
