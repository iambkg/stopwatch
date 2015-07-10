package by.bkg.stopwatch.desktop.view.model.factory;

import by.bkg.stopwatch.core.model.ICategory;
import by.bkg.stopwatch.core.model.enums.Sex;
import by.bkg.stopwatch.core.model.stub.CategoryDictionary;
import by.bkg.stopwatch.desktop.view.i18n.AppMessages;
import by.bkg.stopwatch.desktop.view.model.SexModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Vector;

/**
 * Provides data. TODO do we need this class?
 *
 * @author Alexey Baryshnev
 */
@Component
public class DataFactory {

    @Autowired
    private AppMessages appMessages;

    private static CategoryDictionary categoryDictionary;

    private DataFactory() {
    }

    public Vector<ICategory> getAvailableCategories() {
        if (categoryDictionary == null) {
            categoryDictionary = new CategoryDictionary();
        }
        return categoryDictionary.getAvailableCategories();
    }

    public Vector<SexModel> getSexValues() {
        Vector<SexModel> result = new Vector<SexModel>();
        for (Sex sex : Sex.values()) {
            result.add(new SexModel(sex, appMessages.getString(sex.getI18nKey())));
        }
        return result;
    }
}
