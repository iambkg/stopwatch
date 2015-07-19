package by.bkg.stopwatch.core.model;

import by.bkg.stopwatch.core.model.enums.Sex;

/**
 * Class for a person category.
 *
 * @author Alexey Baryshnev
 */
public class Category implements ICategory {

    private String name;

    private Sex sex;

    public Category(final String name, final Sex sex) {
        this.name = name;
        this.sex = sex;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public Sex getSex() {
        return sex;
    }

    @Override
    public String toString() {
        return getName();
    }
}
