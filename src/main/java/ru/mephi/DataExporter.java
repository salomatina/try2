package ru.mephi;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

public class DataExporter {

    public void writeData(LinkedHashMap<String, Double[]> hashMap, String path, String name) throws IOException {
        XSSFWorkbook book = new XSSFWorkbook();
        XSSFSheet sheet = book.createSheet("Salomatina");
        sheet.setColumnWidth(0, 8000);
        XSSFRow row = sheet.createRow(0);
        row.createCell(1).setCellValue("X");
        row.createCell(2).setCellValue("Y");
        row.createCell(3).setCellValue("Z");
        int index = 1;

        for (Map.Entry<String, Double[]> stringEntry : hashMap.entrySet()) {
            XSSFRow tempRow = sheet.createRow(index);
            tempRow.createCell(0).setCellValue(stringEntry.getKey());
            for (int i = 1; i <= 3; i++) {
                tempRow.createCell(i).setCellValue(stringEntry.getValue()[i - 1]);
            }
            index++;
        }

        File file = new File(path + "\\" + name + ".xlsx");
        FileOutputStream outputStream = new FileOutputStream(file);
        book.write(outputStream);
        book.close();
    }

}
