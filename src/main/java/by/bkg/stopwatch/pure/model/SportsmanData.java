package by.bkg.stopwatch.pure.model;

import by.bkg.stopwatch.mvc.model.business.Category;
import by.bkg.stopwatch.pure.model.enums.Sex;

import java.util.Date;

/**
 * @author Alexey Baryshnev
 */
public class SportsmanData implements ISportsmanData {

    private String firstName;

    private String middleName;

    private String lastName;

    private Date dateOfBirth;

    private Sex sex;

    private Category category;

    private String startNumber;

    public SportsmanData(String firstName, String middleName, String lastName, Date dateOfBirth, Sex sex, Category category, String startNumber) {
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.sex = sex;
        this.category = category;
        this.startNumber = startNumber;
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

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getStartNumber() {
        return startNumber;
    }

    public void setStartNumber(String startNumber) {
        this.startNumber = startNumber;
    }
}
