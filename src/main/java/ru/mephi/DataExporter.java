package ru.mephi;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Map;

public class DataExporter {

    //int numOfFiles = 0;

    public void writeData(Calculator calculator) throws IOException {
        XSSFWorkbook book = new XSSFWorkbook();
        XSSFSheet sheet = book.createSheet("Salomatina");
        sheet.setColumnWidth(0, 8000);
        XSSFRow row = sheet.createRow(0);
//        r.createCell(0).setCellValue("");
        row.createCell(1).setCellValue("X");
        row.createCell(2).setCellValue("Y");
        row.createCell(3).setCellValue("Z");
        DataImporter dataImporter = new DataImporter();
        int index = 1;
        for (Map.Entry<String, Double[]> stringEntry : calculator.getHashMap(dataImporter).entrySet()) {
            XSSFRow tempRow = sheet.createRow(index);
            tempRow.createCell(0).setCellValue(stringEntry.getKey());
            for (int i = 1; i <= 3; i++) {
                tempRow.createCell(i).setCellValue(stringEntry.getValue()[i - 1]);
            }
            index++;
        }
//        String directory;
//        if (numOfFiles == 0) {
//        directory = "C:\\User\\Елена\\IdeaProjects\\hw.xlsx";
//        }
//        else {
//            directory = String.format("C:\\User\\Елена\\IdeaProjects\\hw(%d).xlsx", numOfFiles);
//            numOfFiles++;
//        }

        File file = new File("C:\\Users\\Елена\\Downloads\\hwjava1.xlsx");
        FileOutputStream outputStream = new FileOutputStream(file);
        book.write(outputStream);
        book.close();
    }

//    public static void main(String[] args) throws IOException {
//        Calculator calculator = new Calculator();
//        DataExporter dataExporter = new DataExporter();
//        dataExporter.writeData(calculator);
//
//    }

}
