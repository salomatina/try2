package ru.mephi;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.IOException;

public class DataImporter {

    public XSSFSheet findSheet(String path) throws IOException {
        XSSFWorkbook book = new XSSFWorkbook(path);
        XSSFSheet sheet = book.getSheetAt(1);
        book.close();
        return sheet;
    }

    public double[] readData(int columnIndex, XSSFSheet sheet) {
        int rowNumber = sheet.getLastRowNum();
        double[] variables = new double[rowNumber];
        for (int i = 1; i <= rowNumber; i++) {
            variables[i - 1] = sheet.getRow(i).getCell(columnIndex).getNumericCellValue();
        }

        return variables;
    }

    public double[][] readAllData(XSSFSheet sheet) {
        int rowNumber = sheet.getLastRowNum();
        double[][] variables = new double[rowNumber][];
        for (int i = 0; i < 3; i++) {
            variables[i] = readData(i, sheet);
        }
        return variables;
    }

}
