package Flare.Modules;


import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
        String[] checkList = new String[]{"Date","Time","Sample_NO", "Quality","Program", "Bloom", "Cod_1", "Cod_2", "Cod_3", "Cod_4","Kristal","Seria","Product", "C","Si","Mn","P","S","Cr","Ni","Mo","Cu","Al","As","V","W","Ti"};
        String quality,bloom,cod_1,cod_2,cod_3,cod_4;
        String[] data = new String[24];
        boolean checkHeaderRow = true;
        List<MSample> sampleList = new ArrayList<>();
        SimpleDateFormat dt = new SimpleDateFormat("dd.MM.yyyy");
        SimpleDateFormat dt1 = new SimpleDateFormat("HH:mm:ss");
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
                data[0] = dt.format(cell.getDateCellValue()); //
                cell = sheet.getRow(row.getRowNum()).getCell(1);
                data[1] = dt1.format(cell.getDateCellValue());
                    for(int i = 2; i<=3;++i)
                    {
                        cell = sheet.getRow(row.getRowNum()).getCell(i);
                        if(cell == null )
                        data[i] = null;  //"sampleNo" "Quality", "Bloom", "Cod_1", "Cod_2", "Cod_3", "Cod_4"
                        else if(cell.getCellTypeEnum().name()== "STRING")
                            data[i] = cell.getStringCellValue();
                        else data[i] = String.valueOf(cell.getNumericCellValue());
                    }
                for(int i = 5; i<=9;++i)
                {
                    cell = sheet.getRow(row.getRowNum()).getCell(i);
                    if(cell == null )
                        data[i-1] = null;  //"sampleNo" "Quality", "Bloom", "Cod_1", "Cod_2", "Cod_3", "Cod_4"
                    else if(cell.getCellTypeEnum().name()== "STRING")
                        data[i-1] = cell.getStringCellValue();
                    else data[i-1] = String.valueOf(cell.getNumericCellValue());
                }
                cell = sheet.getRow(row.getRowNum()).getCell(11);
                if(cell != null) {
                    if(cell.getCellTypeEnum().name()== "STRING")
                    data[9] = cell.getStringCellValue();
                    else
                    {
                        data[9] = dt.format(cell.getDateCellValue());
                        String splitter[] = data[9].split("\\.");
                        data[9]=Integer.parseInt(splitter[0])+"*"+Integer.parseInt(splitter[1]);
                    }
                }
                else data[9] = null;

                for(int i = 13; i<=26;++i)
                {
                    cell = sheet.getRow(row.getRowNum()).getCell(i);
                    if(cell==null) data[i-3] = null;
                    else if(cell.getCellTypeEnum().name()== "STRING") {
                        data[i-3] = cell.getStringCellValue();  //
                        data[i-3]=data[i-3].replace(",", ".");
                        data[i-3]=data[i-3].replace("<", "");
                        data[i-3]=data[i-3].replace(">", "");
                    }
                    else data[i-3] = String.valueOf(cell.getNumericCellValue());
                }
                System.out.println("");
                if(data[4]!=null)//Убираем .0 после номера блюма
                {
                StringBuffer buffer = new StringBuffer(data[4]);
                buffer.delete(data[4].length()-2,data[4].length());
                data[4]= buffer.toString();
                }
                int bloomCount = 0;
                String[] bloomDivide;
                for(int i = 0; i<4;++i) {
                    try {
                        bloomDivide = data[i+5].split("\\*");
                        bloomCount += Integer.parseInt(bloomDivide[0]);
                    } catch (Exception e) {
                    }
                }
                MSample sample = MSample.builder()
                        .date(data[0])
                        .time(data[1])
                        .sampleNo(data[2])
                        .quality(data[3])
                        .bloom(data[4])
                        .codes(String.valueOf(bloomCount))
                        .seria(data[9])
                        .C(data[10])
                        .Si(data[11])
                        .Mn(data[12])
                        .P(data[13])
                        .S(data[14])
                        .Cr(data[15])
                        .Ni(data[16])
                        .Mo(data[17])
                        .Cu(data[18])
                        .Al(data[19])
                        .V(data[21])
                        .W(data[22])
                        .Ti(data[23])
                        .build();
                sampleList.add(sample);
            }

        return sampleList;
    }
}
