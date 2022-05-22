package Flare;
import Flare.Modules.ComplexSearch;
import Flare.Modules.ImportFromExcel;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.io.File;
import java.io.IOException;



public class Program {

    public static void main(String[] args) {
        JFrame frame = new JFrame("Flare");
        JFileChooser fileChooser = new JFileChooser();
        JButton OpenFile = new JButton("Импорт из Excel");
        JButton SaveTableButton = new JButton("Сохранить");
        JButton deleteButton = new JButton("Удалить");
        deleteButton.setToolTipText("Удаляет выделенный елемент(-ы)");
        Icon searchIcon = new ImageIcon("search.png");
        Icon settingsIcon = new ImageIcon("settings.png");
        Icon refreshIcon = new ImageIcon("refresh.png");
        JButton searchButton = new JButton();
        searchButton.setIcon(searchIcon);
        searchButton.setToolTipText("Поиск образцов по номеру");
        JButton searchSettingsButton = new JButton();
        searchSettingsButton.setIcon(settingsIcon);
        searchSettingsButton.setToolTipText("Расширенные параметры поиска");
        JTextField searchBox = new JTextField();
        JButton refreshButton = new JButton();
        refreshButton.setIcon(refreshIcon);
        refreshButton.setToolTipText("Заново вывести данные в таблицу");
        searchBox.setToolTipText("Введите номер образца для поиска");
        Controller controller= new Controller();
        JScrollPane output = new JScrollPane(controller.createOutputTable());
        output.setBounds(0, 0, 1100, 600);
        OpenFile.setBounds(325, 620, 150, 30);
        deleteButton.setBounds(525, 620, 150, 30);
        SaveTableButton.setBounds(1105, 120, 150, 30);
        searchBox.setBounds(1105, 40, 150, 30);
        searchButton.setBounds(1105, 70, 30, 30);
        searchSettingsButton.setBounds(1140, 70, 30, 30);
        refreshButton.setBounds(1175, 70, 30, 30);
        frame.setSize(1280, 720);
        frame.setResizable(false);
        frame.getContentPane().setLayout(null);
        frame.setLocation(400, 150);
        frame.add(OpenFile);
        frame.add(deleteButton);
        frame.add(SaveTableButton);
        frame.add(output);
        frame.add(searchButton);
        frame.add(searchSettingsButton);
        frame.add(refreshButton);
        frame.add(searchBox);
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
        SaveTableButton.addActionListener(actionListener ->
        {

            controller.printOutputTable();
        });
        searchButton.addActionListener(actionListener ->
        {
            if(searchBox.getText().length()>0) {
                SaveTableButton.setEnabled(false);
            }
            controller.search(searchBox.getText());

        });
        refreshButton.addActionListener(actionListener ->
        {
            SaveTableButton.setEnabled(true);
            searchBox.setText(null);
            controller.print(controller.pullData());
        });
        deleteButton.addActionListener(actionListener ->
        {

            controller.delete();
        });
    }

}
