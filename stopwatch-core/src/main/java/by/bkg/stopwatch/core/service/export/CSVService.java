package by.bkg.stopwatch.core.service.export;

import au.com.bytecode.opencsv.CSVWriter;
import by.bkg.stopwatch.core.service.ILoggingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @author Alexey Baryshnev
 */
@Service
public class CSVService implements IExportService {

    @Autowired
    private ILoggingService loggingService;

    @Override
    public void doExport(final String path, final List<String[]> data) {
        try {
            CSVWriter writer = new CSVWriter(new OutputStreamWriter(new FileOutputStream(new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss").format(new Date()).concat(".csv")), "UTF-8"));
            for (String[] row : data) {
                writer.writeNext(row);
            }
            writer.close();
        } catch (IOException e) {
            // TODO ABA: make something, do not panic
            loggingService.error(e);
        }
    }
}
