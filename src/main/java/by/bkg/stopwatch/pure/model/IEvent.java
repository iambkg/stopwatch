package by.bkg.stopwatch.pure.model;

import java.util.List;

/**
 * @author Alexey Baryshnev
 */
public interface IEvent {

    List<ISportsman> getSportsmen();

    List<ISplitRecord> getSplits();
}
