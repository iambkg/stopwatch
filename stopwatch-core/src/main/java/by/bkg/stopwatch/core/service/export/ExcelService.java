package by.bkg.stopwatch.core.service.export;

import by.bkg.stopwatch.core.service.ILoggingService;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileOutputStream;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

/**
 * <a href"mailto:alexey.baryshnev@ctco.lv">Alexey Baryshnev</a>
 */
@Service
public class ExcelService implements IExportImportService {

    @Autowired
    private ILoggingService loggingService;

    @Override
    public void doExport(final String path, final List<String[]> data) {
        //Blank workbook
        XSSFWorkbook workbook = new XSSFWorkbook();

        //Create a blank sheet
        XSSFSheet sheet = workbook.createSheet("Event Data");

        //This data needs to be written (Object[])
        Map<String, Object[]> xData = new TreeMap<String, Object[]>();
        xData.put("1", new Object[]{"ID", "NAME", "LASTNAME"});
        xData.put("2", new Object[]{1, "Amit", "Shukla"});
        xData.put("3", new Object[]{2, "Lokesh", "Gupta"});
        xData.put("4", new Object[]{3, "John", "Adwards"});
        xData.put("5", new Object[]{4, "Brian", "Schultz"});

        //Iterate over data and write to sheet
        Set<String> keyset = xData.keySet();
        int rownum = 0;
        for (String key : keyset) {
            Row row = sheet.createRow(rownum++);
            Object[] objArr = xData.get(key);
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
}
