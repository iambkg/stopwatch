package by.bkg.stopwatch.core.service;

import by.bkg.stopwatch.core.model.IEvent;
import by.bkg.stopwatch.core.model.ISplitRecord;
import by.bkg.stopwatch.core.model.ISportsman;
import by.bkg.stopwatch.core.model.ISportsmanData;

import java.util.List;

/**
 * @author Alexey Baryshnev
 */
public interface ILogicService {

    List<ISportsman> addSportsman(ISportsmanData sportsmanData);

    List<ISportsman> editSportsman(ISportsmanData sportsmanData);

    IEvent getEvent();

    /**
     * Reset Event: clear all stored sportsmen, split records
     */
    void flush();

    List<ISplitRecord> doSplit(String startNumber);
}
