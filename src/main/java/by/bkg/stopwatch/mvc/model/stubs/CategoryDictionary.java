package by.bkg.stopwatch.mvc.model.stubs;

import by.bkg.stopwatch.mvc.model.Category;

import java.util.Vector;

/**
 * Provides list of Categories. TODO ABA: read from XML file
 *
 * @author Alexey Baryshnev
 */
public class CategoryDictionary {

    public Vector<Category> getAvailableCategories() {
        Vector<Category> result = new Vector<Category>();
        result.add(new Category("Men Unior"));
        result.add(new Category("Men Elite"));
        result.add(new Category("Men Masters"));
        result.add(new Category("Men Masters+"));
        result.add(new Category("Women Unior"));
        result.add(new Category("Women Elite"));
        result.add(new Category("Women Masters"));
        result.add(new Category("Women Masters+"));
        return result;
    }
}
