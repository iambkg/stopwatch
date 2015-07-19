package by.bkg.stopwatch.core.model.stub;

import by.bkg.stopwatch.core.model.Category;
import by.bkg.stopwatch.core.model.ICategory;
import by.bkg.stopwatch.core.model.enums.Sex;

import java.util.Vector;

/**
 * Provides list of Categories. TODO ABA: read from XML file
 *
 * @author Alexey Baryshnev
 */
public class CategoryDictionary {

    public Vector<ICategory> getAvailableCategories() {
        Vector<ICategory> result = new Vector<ICategory>();
        result.add(new Category("Men Unior", Sex.MALE));
        result.add(new Category("Men Elite", Sex.MALE));
        result.add(new Category("Men Masters", Sex.MALE));
        result.add(new Category("Men Masters+", Sex.MALE));
        result.add(new Category("Women Unior", Sex.FEMALE));
        result.add(new Category("Women Elite", Sex.FEMALE));
        result.add(new Category("Women Masters", Sex.FEMALE));
        result.add(new Category("Women Masters+", Sex.FEMALE));
        return result;
    }
}
