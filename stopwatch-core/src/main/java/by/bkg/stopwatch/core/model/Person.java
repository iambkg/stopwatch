package by.bkg.stopwatch.core.model;

import java.util.Calendar;

/**
 * @author Alexey Baryshnev
 */
public class Person implements IPerson {

    /**
     * Name
     */
    private String firstName;

    /**
     * Father's name
     */
    private String middleName;

    /**
     * Surname
     */
    private String lastName;

    /**
     * Date of Birth
     */
    private Calendar dateOfBirth;

//    private City city;

//    private Country country;

    public Person() {
    }

    @Override
    public String getFirstName() {
        return firstName;
    }

    @Override
    public void setFirstName(final String firstName) {
        this.firstName = firstName;
    }

    @Override
    public String getMiddleName() {
        return middleName;
    }

    @Override
    public void setMiddleName(final String middleName) {
        this.middleName = middleName;
    }

    @Override
    public String getLastName() {
        return lastName;
    }

    @Override
    public void setLastName(final String lastName) {
        this.lastName = lastName;
    }

    @Override
    public Calendar getDateOfBirth() {
        return dateOfBirth;
    }

    @Override
    public void setDateOfBirth(final Calendar dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    @Override
    public String toString() {
        return "Person{"
                + "firstName='" + firstName + '\''
                + ", middleName='" + middleName + '\''
                + ", lastName='" + lastName + '\''
                + ", dateOfBirth=" + dateOfBirth
                + '}';
    }
}
