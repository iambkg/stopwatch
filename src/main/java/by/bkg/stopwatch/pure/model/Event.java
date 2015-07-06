package by.bkg.stopwatch.pure.model;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Alexey Baryshnev
 */
public class Event implements IEvent {

    private List<ISportsman> sportsmen;

    private List<SplitRecord> splits;

    public Event() {
        this.sportsmen = new ArrayList<ISportsman>();
        this.splits = new ArrayList<SplitRecord>();
    }

    public List<ISportsman> getSportsmen() {
        return sportsmen;
    }

    public List<SplitRecord> getSplits() {
        return splits;
    }
}
