package by.bkg.stopwatch.pure.model;

import by.bkg.stopwatch.pure.model.enums.Sex;

import java.util.Date;

/**
 * @author Alexey Baryshnev
 */
public class Person implements IPerson {

    private Long id;

    private String firstName;

    private String middleName;

    private String lastName;

    private Date dateOfBirth;

    private Sex sex;

    public Person(ISportsmanData sportsmanData) {
        // TODO ABA: create id correctly
        this.id = Long.getLong(String.valueOf(Math.random()));
        this.firstName = sportsmanData.getFirstName();
        this.middleName = sportsmanData.getMiddleName();
        this.lastName = sportsmanData.getLastName();
        this.dateOfBirth = sportsmanData.getDateOfBirth();
        this.sex = sportsmanData.getSex();
    }

    public Long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public Sex getSex() {
        return sex;
    }

    public void setSex(Sex sex) {
        this.sex = sex;
    }
}
