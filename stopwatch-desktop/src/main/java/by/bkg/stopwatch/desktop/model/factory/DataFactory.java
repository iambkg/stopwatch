package by.bkg.stopwatch.desktop.model.factory;

import by.bkg.stopwatch.core.model.ICategory;
import by.bkg.stopwatch.core.model.stub.CategoryDictionary;

import java.util.Vector;

/**
 * Provides data. TODO do we need this class?
 *
 * @author Alexey Baryshnev
 */
@Deprecated
public final class DataFactory {

    private static CategoryDictionary categoryDictionary;

    private DataFactory() {
    }

    public static Vector<ICategory> getAvailableCategories() {
        if (categoryDictionary == null) {
            categoryDictionary = new CategoryDictionary();
        }
        return categoryDictionary.getAvailableCategories();
    }
}
