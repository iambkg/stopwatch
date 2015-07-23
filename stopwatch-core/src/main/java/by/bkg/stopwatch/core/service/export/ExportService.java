package by.bkg.stopwatch.core.service.export;

import au.com.bytecode.opencsv.CSVWriter;
import by.bkg.stopwatch.core.service.ILoggingService;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

/**
 * @author Alexey Baryshnev
 */
@Service
public class ExportService implements IExportService {

    @Autowired
    private ILoggingService loggingService;

    @Override
    public void export(final String path) {
        //Blank workbook
        XSSFWorkbook workbook = new XSSFWorkbook();

        //Create a blank sheet
        XSSFSheet sheet = workbook.createSheet("Event Data");

        //This data needs to be written (Object[])
        Map<String, Object[]> data = new TreeMap<String, Object[]>();
        data.put("1", new Object[]{"ID", "NAME", "LASTNAME"});
        data.put("2", new Object[]{1, "Amit", "Shukla"});
        data.put("3", new Object[]{2, "Lokesh", "Gupta"});
        data.put("4", new Object[]{3, "John", "Adwards"});
        data.put("5", new Object[]{4, "Brian", "Schultz"});

        //Iterate over data and write to sheet
        Set<String> keyset = data.keySet();
        int rownum = 0;
        for (String key : keyset) {
            Row row = sheet.createRow(rownum++);
            Object[] objArr = data.get(key);
            int cellnum = 0;
            for (Object obj : objArr) {
                Cell cell = row.createCell(cellnum++);
                if (obj instanceof String) {
                    cell.setCellValue((String) obj);
                } else if (obj instanceof Integer) {
                    cell.setCellValue((Integer) obj);
                }
            }
        }
        try {
            //Write the workbook in file system
            File file = new File("testExportExcell.xlsx");
            FileOutputStream out = new FileOutputStream(file);
            workbook.write(out);
            out.close();
            loggingService.debug("export results are in " + file.getAbsolutePath());
        } catch (Exception e) {
            // TODO ABA: log, send message
        }
    }

    @Override
    public void exportOpenCSV(final String path) {
        try {
            CSVWriter writer = new CSVWriter(new FileWriter(new SimpleDateFormat("YYYY-mm-DD_HH_MM_SS").format(new Date())));
            // feed in your array (or convert your data to an array)
            String[] entries = new String[] {"one", "B1", "3rd"};
            writer.writeNext(entries);
            writer.close();
        } catch (IOException e) {
            // TODO ABA: make something, do not panoc
        }
    }
}
