package by.bkg.stopwatch.mvc.model;

/**
 * Class for a person category.
 *
 * @author Alexey Baryshnev
 */
public class Category {

    private String name;

//    /**
//     * TODO: what will be here for separate starts?
//     */
//    private Integer numberOfLaps;

    public Category() {
    }

    public Category(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return getName();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Category) {
            return this.name.equals(((Category)obj).getName());
        }
        return super.equals(obj);
    }

    @Override
    public int hashCode() {
        return 31 * name.hashCode();
    }
}
