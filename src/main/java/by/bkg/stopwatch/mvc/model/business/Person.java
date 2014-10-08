package by.bkg.stopwatch.mvc.model.business;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Class for a Person.
 *
 * @author Alexey Baryshnev
 */
public class Person {

    public static final String PERSON_TO_STRING_FORMAT = "%s %s (%s)";
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

    private String startNumber;

    private Date birthDate;

    private List<Split> splits = new ArrayList<Split>();

    private Category category;

    public Person() {
    }

    public Person(String lastName, String startNumber) {
        this.lastName = lastName;
        this.startNumber = startNumber;
    }

    public void addSplit(Split split) {
        splits.add(split);
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

    public String getStartNumber() {
        return startNumber;
    }

    public void setStartNumber(String startNumber) {
        this.startNumber = startNumber;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public List<Split> getSplits() {
        return splits;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return String.format(PERSON_TO_STRING_FORMAT, getLastName(), getFirstName(), getStartNumber());
    }
}
