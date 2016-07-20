package by.bkg.stopwatch.core.model;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Alexey Baryshnev
 */
public class Event implements IEvent {

    private final List<ISportsman> sportsmen;

    private final List<ITeam> teams;

    private final List<ISplitRecord> splits;

    public Event() {
        this.sportsmen = new ArrayList<ISportsman>();
        this.teams = new ArrayList<ITeam>();
        this.splits = new ArrayList<ISplitRecord>();
    }

    @Override
    public List<ISportsman> getSportsmen() {
        return sportsmen;
    }

    @Override
    public List<ITeam> getTeams() {
        return teams;
    }

    @Override
    public List<ISplitRecord> getSplits() {
        return splits;
    }
}
