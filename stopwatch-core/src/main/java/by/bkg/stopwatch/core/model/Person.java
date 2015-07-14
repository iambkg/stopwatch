package by.bkg.stopwatch.core.model;

import by.bkg.stopwatch.core.model.enums.Sex;

import java.util.Date;

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
    private Date dateOfBirth;

    /**
     * Sex
     */
    private Sex sex;

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
    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    @Override
    public void setDateOfBirth(final Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    @Override
    public Sex getSex() {
        return sex;
    }

    @Override
    public void setSex(final Sex sex) {
        this.sex = sex;
    }

    @Override
    public String toString() {
        return "Person{" +
                "firstName='" + firstName + '\'' +
                ", middleName='" + middleName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                ", sex=" + sex +
                '}';
    }
}
