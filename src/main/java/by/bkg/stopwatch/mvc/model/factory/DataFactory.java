package by.bkg.stopwatch.mvc.model.factory;

import by.bkg.stopwatch.mvc.model.business.Category;
import by.bkg.stopwatch.mvc.model.stubs.CategoryDictionary;

import java.util.Vector;

/**
 * Provides data. TODO do we need this class?
 *
 * @author Alexey Baryshnev
 */
public final class DataFactory {

    private static CategoryDictionary categoryDictionary;

    private DataFactory() {
    }

    public static Vector<Category> getAvailableCategories() {
        if (categoryDictionary == null) {
            categoryDictionary = new CategoryDictionary();
        }
        return categoryDictionary.getAvailableCategories();
    }
}
