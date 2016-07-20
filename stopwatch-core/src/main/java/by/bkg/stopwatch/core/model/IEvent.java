package by.bkg.stopwatch.core.model;

import java.util.List;

/**
 * @author Alexey Baryshnev
 */
public interface IEvent {

    List<ISportsman> getSportsmen();

    List<ITeam> getTeams();

    List<ISplitRecord> getSplits();
}
