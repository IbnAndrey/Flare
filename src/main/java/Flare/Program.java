package Flare;
import Flare.Modules.ImportFromExcel;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.File;
import java.io.IOException;



public class Program {

    public static void main(String[] args) {
        JFrame frame = new JFrame("Flare");
        JFileChooser fileChooser = new JFileChooser();
        JButton OpenFile = new JButton("Импорт из Excel");
        Controller controller= new Controller();
        JScrollPane output = new JScrollPane(controller.createOutputTable());
        output.setBounds(0, 0, 1100, 600);
        OpenFile.setBounds(325, 620, 150, 30);
        frame.setSize(1280, 720);
        frame.setResizable(false);
        frame.getContentPane().setLayout(null);
        frame.setLocation(400, 150);
        frame.add(OpenFile);
        frame.add(output);
        frame.setVisible(true);



        OpenFile.addActionListener(actionListener ->
        {
            File ExcelFile;
            FileNameExtensionFilter filter = new FileNameExtensionFilter(
                    "Файлы Excel (.xls)", "xls");
            fileChooser.setFileFilter(filter);
            fileChooser.setDialogTitle("Выбор расположения");
            if (fileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION ){
                ExcelFile = fileChooser.getSelectedFile();
                try {
                    controller.pushRow(ImportFromExcel.GetExcelData(ExcelFile));

                }
                catch(IOException e)
                {
                    JOptionPane.showMessageDialog(frame,"Ошибка открытия файла. Возможно:\n 1. Файл не имеет нужных колонок, или он пуст;\n 2. Выбран некорректный файл.","Ошибка",JOptionPane.ERROR_MESSAGE);
                }
            }
        });

    }

}
