package by.bkg.stopwatch.core.model;

import by.bkg.stopwatch.core.model.enums.Sex;

import java.util.Calendar;

/**
 * @author Alexey Baryshnev
 */
public interface ISportsman {

    IPerson getPerson();

    void setPerson(IPerson person);

    ICategory getCategory();

    void setCategory(ICategory category);

    String getStartNumber();

    void setStartNumber(String startNumber);

    String getFirstName();

    void setFirstName(String firstName);

    String getMiddleName();

    void setMiddleName(String middleName);

    String getLastName();

    void setLastName(String lastName);

    Sex getSex();

    void setSex(Sex sex);

    Calendar getDateOfBirth();

    void setDateOfBirth(Calendar dateOfBirth);

    /**
     * Overwrites data for current sportsman.
     * If some field in <code>sportsmanData</code> is null - null is set.
     * If field is ISportsmanData.EMPTY - nothing happens
     *
     * @param sportsmanData sportsman data
     */
    void refresh(ISportsman sportsmanData);
}
