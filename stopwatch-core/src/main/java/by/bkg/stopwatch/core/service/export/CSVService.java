package by.bkg.stopwatch.core.service.export;

import au.com.bytecode.opencsv.CSVReader;
import au.com.bytecode.opencsv.CSVWriter;
import by.bkg.stopwatch.core.service.ILoggingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @author Alexey Baryshnev
 */
@Service
public class CSVService implements IExportImportService {

    @Autowired
    private ILoggingService loggingService;

    @Override
    public void doExport(final String path, final List<String[]> data) {
        try {
            CSVWriter writer = new CSVWriter(new FileWriter(new SimpleDateFormat("yyyy-mm-DD_HH_MM_SS").format(new Date()).concat(".csv")));
            // feed in your array (or convert your data to an array)
            String[] rowOne = new String[]{"one", "B1", "3rd 1st"};
            writer.writeNext(rowOne);
            String[] rowTwo = new String[]{"two", "B2", "3rd 2nd"};
            writer.writeNext(rowTwo);
            writer.close();
        } catch (IOException e) {
            // TODO ABA: make something, do not panic
            loggingService.error(e);
        }
    }

    public List<String[]> doImport(final String path) {
        try {
            CSVReader reader = new CSVReader(new FileReader(path));
            List<String[]> myEntries = reader.readAll();
            return myEntries;
        } catch (FileNotFoundException e) {
            // TODO ABA: make something, do not panic
            loggingService.error(e);
        } catch (IOException e) {
            // TODO ABA: make something, do not panic
            loggingService.error(e);
        }
        return null;
    }
}
