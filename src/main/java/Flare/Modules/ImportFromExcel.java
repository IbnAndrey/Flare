package Flare.Modules;

import javax.swing.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Iterator;

import Flare.MSample;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;

public class ImportFromExcel {

    private static HSSFCellStyle createStyleForTitle(HSSFWorkbook workbook) {
        HSSFFont font = workbook.createFont();
        font.setBold(true);
        HSSFCellStyle style = workbook.createCellStyle();
        style.setFont(font);
        return style;
    }
    public static List<MSample> GetExcelData(File ExcelFile) throws IOException {
        String[] checkList = new String[]{"Sample_No", "Quality", "Bloom", "Cod_1", "Cod_2", "Cod_3", "Cod_4","Kristal","Product","Seria", "C","Si","Mn","P","S","Cr","Ni","Mo","Cu","Al","V","W","Ti"};
        String quality,bloom,cod_1,cod_2,cod_3,cod_4;
        String[] data = new String[20];
        boolean checkHeaderRow = true;
        List<MSample> sampleList = new ArrayList<>();

        // Read XLS file
        FileInputStream inputStream = new FileInputStream(ExcelFile);

        // Get the workbook instance for XLS file
        HSSFWorkbook workbook = new HSSFWorkbook(inputStream);

        // Get first sheet from the workbook
        HSSFSheet sheet = workbook.getSheetAt(0);

        // Get iterator to all the rows in current sheet
        Iterator<Row> rowIterator = sheet.iterator();
        //ObjectOutputStream fout = new ObjectOutputStream(new FileOutputStream("db.dat"));
            Row row = rowIterator.next();
            // Get iterator to all cells of current row
            Iterator<Cell> cellIterator = row.cellIterator();
            for(int i = 0; i< checkList.length;++i) //Проверка заголовков таблицы на корректность
            {
                Cell cell = cellIterator.next();
                String cellValue = cell.getStringCellValue();
                if(!cellValue.equals(checkList[i]))// Если хоть один заголовок не соответствует нужному порядку из проверочной строки, то выбрасываем исключение
                {
                    throw new IOException();
                }
            }


            while (rowIterator.hasNext()) {
                row = rowIterator.next();
                cellIterator = row.cellIterator();

                //while (cellIterator.hasNext()) {

                    /*Cell cell = sheet.getRow(row.getRowNum()).getCell(0);
                    // Change to getCellType() if using POI 4.x
                    CellType cellType = cell.getCellTypeEnum();*/

                Cell cell = sheet.getRow(row.getRowNum()).getCell(0);
                data[0] = cell.getStringCellValue(); //

                    for(int i = 0; i<=6;++i)
                    {
                        cell = sheet.getRow(row.getRowNum()).getCell(i);
                        if(cell == null )
                        data[i] = null;  //"sampleNo" "Quality", "Bloom", "Cod_1", "Cod_2", "Cod_3", "Cod_4"
                        else if(cell.getCellTypeEnum().name()== "STRING")
                            data[i] = cell.getStringCellValue();
                        else data[i] = String.valueOf(cell.getNumericCellValue());
                    }

                for(int i = 10; i<=22;++i)
                {
                    cell = sheet.getRow(row.getRowNum()).getCell(i);

                    if(cell.getCellTypeEnum().name()== "STRING")
                    data[i-3] = cell.getStringCellValue();  //
                    else data[i-3] = String.valueOf(cell.getNumericCellValue());
                }
                System.out.println("");
                if(data[2]!=null)//Убираем .0 после номера блюма
                {
                StringBuffer buffer = new StringBuffer(data[2]);
                buffer.delete(data[2].length()-2,data[2].length());
                data[2]= buffer.toString();
                }
                MSample sample = MSample.builder()
                        .sampleNo(data[0])
                        .quality(data[1])
                        .bloom(data[2])
                        .cod_1(data[3])
                        .cod_2(data[4])
                        .cod_3(data[5])
                        .cod_4(data[6])
                        .C(data[7])
                        .Si(data[8])
                        .Mn(data[9])
                        .P(data[10])
                        .S(data[11])
                        .Cr(data[12])
                        .Ni(data[13])
                        .Mo(data[14])
                        .Cu(data[15])
                        .Al(data[16])
                        .V(data[17])
                        .W(data[18])
                        .Ti(data[19])
                        .build();
                sampleList.add(sample);
            }

        return sampleList;
    }
}
