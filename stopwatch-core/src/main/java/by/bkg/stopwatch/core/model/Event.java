package by.bkg.stopwatch.core.model;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Alexey Baryshnev
 */
public class Event implements IEvent {

    private List<ISportsman> sportsmen;

    private List<ISplitRecord> splits;

    public Event() {
        this.sportsmen = new ArrayList<ISportsman>();
        this.splits = new ArrayList<ISplitRecord>();
    }

    @Override
    public List<ISportsman> getSportsmen() {
        return sportsmen;
    }

    @Override
    public List<ISplitRecord> getSplits() {
        return splits;
    }
}
