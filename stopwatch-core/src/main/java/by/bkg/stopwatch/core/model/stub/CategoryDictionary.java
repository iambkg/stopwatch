package by.bkg.stopwatch.core.model.stub;

import by.bkg.stopwatch.core.model.Category;
import by.bkg.stopwatch.core.model.ICategory;

import java.util.Vector;

/**
 * Provides list of Categories. TODO ABA: read from XML file
 *
 * @author Alexey Baryshnev
 */
public class CategoryDictionary {

    public Vector<ICategory> getAvailableCategories() {
        Vector<ICategory> result = new Vector<ICategory>();
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
