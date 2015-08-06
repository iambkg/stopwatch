package by.bkg.stopwatch.core.model.stub;

import au.com.bytecode.opencsv.CSVReader;
import by.bkg.stopwatch.core.model.Category;
import by.bkg.stopwatch.core.model.ICategory;
import by.bkg.stopwatch.core.model.enums.Sex;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Vector;

/**
 * Provides list of Categories. TODO ABA: read from XML file
 *
 * @author Alexey Baryshnev
 */
public class CategoryDictionary {

    public Vector<ICategory> getAvailableCategories() {
        try {
            CSVReader reader = new CSVReader(new InputStreamReader(new FileInputStream("categories.csv"), "UTF-8"));
            List<String[]> myEntries = reader.readAll();
            return convert(myEntries);
        } catch (FileNotFoundException e) {
            // TODO ABA: make something, do not panic
//            loggingService.error(e);
        } catch (IOException e) {
            // TODO ABA: make something, do not panic
//            loggingService.error(e);
        }

        Vector<ICategory> result = new Vector<ICategory>();             // TODO ABA: remove
        return result;
    }

    private Vector<ICategory> convert(List<String[]> categories) {
        Vector<ICategory> result = new Vector<ICategory>();
        for (String[] categoryData : categories) {
            if (!check(categoryData)) {
                // TODO ABA: log
                continue;
            }
            ICategory category = new Category(categoryData[0], Sex.valueOf(categoryData[1]));
            result.add(category);
        }
        return result;
    }

    private boolean check(String[] categoryData) {
        return categoryData.length == 2 && Sex.contains(categoryData[1]);
    }
}
