package by.bkg.stopwatch.pure.model;

import by.bkg.stopwatch.mvc.model.business.Category;
import by.bkg.stopwatch.pure.model.enums.Sex;

import java.util.Date;

/**
 * @author Alexey Baryshnev
 */
public interface ISportsmanData {

    public Object FAKE = new Object();

    Category getCategory();

    String getFirstName();

    String getMiddleName();

    String getLastName();

    Date getDateOfBirth();

    Sex getSex();

    Long getId();
}
