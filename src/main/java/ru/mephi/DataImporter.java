package ru.mephi;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.IOException;

public class DataImporter {

    double[] variablesCache;
    XSSFSheet sheet;

    public void findSheet() throws IOException {
        String path = "C:\\Users\\Елена\\IdeaProjects\\try2\\src\\main\\resources\\ru\\mephi\\ДЗ2 (1).xlsx";
        XSSFWorkbook book = new XSSFWorkbook(path);
        sheet = book.getSheetAt(1);
        book.close();
    }

    public double[] readData(int columnIndex) throws IOException {
        //String path = "C:\\Users\\Елена\\IdeaProjects\\w2\\src\\main\\resources\\ДЗ2 (1).xlsx";
        if (sheet == null) {
            findSheet();
        }
        int rowNumber = sheet.getLastRowNum();
        variablesCache = new double[rowNumber];
        for (int i = 1; i <= rowNumber; i++) {
            variablesCache[i - 1] = sheet.getRow(i).getCell(columnIndex).getNumericCellValue();
        }


        return variablesCache;
    }

}
