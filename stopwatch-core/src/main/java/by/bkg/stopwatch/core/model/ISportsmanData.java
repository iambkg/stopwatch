package by.bkg.stopwatch.core.model;

import by.bkg.stopwatch.core.model.enums.Sex;

import java.util.Date;

/**
 * @author Alexey Baryshnev
 */
public interface ISportsmanData {

    // TODO ABA: looks like this won't work
    public Object EMPTY = new Object();

    ICategory getCategory();

    String getFirstName();

    String getMiddleName();

    String getLastName();

    Date getDateOfBirth();

    Sex getSex();

    String getStartNumber();
}
