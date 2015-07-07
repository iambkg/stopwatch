package by.bkg.stopwatch.pure.model;

import by.bkg.stopwatch.pure.model.enums.Sex;

import java.util.Date;

/**
 * @author Alexey Baryshnev
 */
public interface IPerson {

    String getFirstName();

    String getMiddleName();

    String getLastName();

    Date getDateOfBirth();

    Sex getSex();

    void setFirstName(String firstName);

    void setMiddleName(String middleName);

    void setLastName(String lastName);

    void setDateOfBirth(Date dateOfBirth);

    void setSex(Sex sex);
}
