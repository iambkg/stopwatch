package by.bkg.stopwatch.core.model;

import by.bkg.stopwatch.core.model.enums.Sex;

import java.util.Date;

/**
 * @author Alexey Baryshnev
 */
public interface ISportsman {

    IPerson getPerson();

    ICategory getCategory();

    String getStartNumber();

    String getFirstName();

    String getMiddleName();

    String getLastName();

    Sex getSex();

    Date getDateOfBirth();

    /**
     * Overwrites data for current sportsman.
     * If some field in <code>sportsmanData</code> is null - null is set.
     * If field is ISportsmanData.EMPTY - nothing happens
     *
     * @param sportsmanData sportsman data
     */
    void refresh(ISportsmanData sportsmanData);
}
