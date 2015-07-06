package by.bkg.stopwatch.pure.model;

import by.bkg.stopwatch.mvc.model.business.Category;

/**
 * @author Alexey Baryshnev
 */
public interface ISportsman {

    IPerson getPerson();

    Category getCategory();

    Long geId();

    String getFirstName();

    String getMiddleName();

    String getLastName();

    /**
     * Overwrites data for current sportsman. If some field in <code>sportsmanData</code> is null - null is set. If field is ISportsmanData.FAKE - nothing happens
     * @param sportsmanData
     */
    void refresh(ISportsmanData sportsmanData);
}
