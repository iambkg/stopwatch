package by.bkg.stopwatch.core.service;

import by.bkg.stopwatch.core.model.IEvent;
import by.bkg.stopwatch.core.model.ISplitRecord;
import by.bkg.stopwatch.core.model.ISportsman;
import by.bkg.stopwatch.core.model.ITeam;

import java.util.List;

/**
 * @author Alexey Baryshnev
 */
public interface ILogicService {

    // TODO ABA: make better? think that should not call this manually
    void init();

    List<ISportsman> addSportsman(ISportsman sportsman);

    List<ISportsman> editSportsman(ISportsman sportsman);

    List<ISportsman> deleteSportsman(ISportsman selectedSportsman);

    List<ITeam> addTeam(ITeam team);

    List<ITeam> editTeam(ITeam team);

    List<ITeam> deleteTeam(final ITeam team);

    IEvent getEvent();

    /**
     * Reset Event: clear all stored sportsmen, split records
     */
    void flush();

    List<ISplitRecord> doSplit(String startNumber);

    void startEvent();

    void stopEvent();

    void suspendEvent();

    void resumeEvent();

    String getCurrentTime();

    List<ISplitRecord> editSplit(ISplitRecord splitToEdit);

    List<ISplitRecord> deleteSplit(ISplitRecord splitToDelete);

    List<ISplitRecord> startNewEvent();

    List<ISportsman> getSportsmen();

    List<ITeam> getTeams();

    ISportsman getSportsmanByStartNumber(String startNumber);

    boolean isStartNumberRegistered(String startNumber);
}
