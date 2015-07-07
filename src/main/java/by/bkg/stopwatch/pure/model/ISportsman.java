package by.bkg.stopwatch.pure.model;

import by.bkg.stopwatch.mvc.model.business.Category;
import by.bkg.stopwatch.pure.model.enums.Sex;

import java.util.Date;

/**
 * @author Alexey Baryshnev
 */
public interface ISportsman {

    IPerson getPerson();

    Category getCategory();

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
