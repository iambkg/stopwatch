package by.bkg.stopwatch.core.model;

import by.bkg.stopwatch.core.model.enums.Sex;

import java.util.Calendar;

/**
 * @author Alexey Baryshnev
 */
public interface IPerson {

    String getFirstName();

    String getMiddleName();

    String getLastName();

    Calendar getDateOfBirth();

    void setFirstName(String firstName);

    void setMiddleName(String middleName);

    void setLastName(String lastName);

    void setDateOfBirth(Calendar dateOfBirth);
}
