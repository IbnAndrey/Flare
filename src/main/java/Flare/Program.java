package Flare;
import Flare.Modules.ImportFromExcel;

import javax.swing.*;
import java.io.File;
import java.io.IOException;



public class Program {

    public static void main(String[] args) {
        JFrame frame = new JFrame("Flare");
        JFileChooser fileChooser = new JFileChooser();
        JButton OpenFile = new JButton("Импорт из Excel");
        OpenFile.setBounds(325, 320, 150, 30);
        frame.setSize(600, 400);
        frame.setResizable(false);
        frame.getContentPane().setLayout(null);
        frame.setLocation(400, 150);
        frame.add(OpenFile);
        frame.setVisible(true);


        OpenFile.addActionListener(actionListener ->
        {
            File ExcelFile;
            fileChooser.setDialogTitle("Выбор расположения");
            if (fileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION ){
                ExcelFile = fileChooser.getSelectedFile();
                try {
                    ImportFromExcel.GetExcelData(ExcelFile);
                }
                catch(IOException e)
                {
                    JOptionPane.showMessageDialog(frame,"Ошибка открытия файла. Возможно:\n 1. Файл не имеет нужных колонок, или он пуст;\n 2. Выбран некорректный файл.","Ошибка",JOptionPane.ERROR_MESSAGE);
                }
            }
        });

    }

}
