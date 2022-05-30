package Flare;
import Flare.Modules.ComplexSearch;
import Flare.Modules.Config;
import Flare.Modules.ImportFromExcel;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.geom.Line2D;
import java.io.File;
import java.io.IOException;




public class Program {



    public static void main(String[] args) {

        JFrame frame = new JFrame("Flare");
        JFileChooser fileChooser = new JFileChooser();
        Controller controller = new Controller();
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
        JScrollPane output = new JScrollPane(controller.createOutputTable());
        output.setBounds(0, 0, 1100, 600);
        OpenFile.setBounds(325, 620, 150, 30);
        deleteButton.setBounds(525, 620, 150, 30);
        SaveTableButton.setBounds(125, 620, 150, 30);
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

/*


            ОКНО РАСШИРЕННОГО ПОИСКА


 */

        JDialog Sframe = new JDialog(frame,"Расширенный поиск",true);
        JButton enableChemsButton = new JButton("Хим. элементы");
        JTextField firstDateBox = new JTextField();
        JTextField secondDateBox = new JTextField();
        JTextField qualityBox = new JTextField();
        JTextField bloomBox = new JTextField();
        JTextField seriaFromBox = new JTextField();
        JTextField seriaToBox = new JTextField();

        JTextField CBox = new JTextField();
        JTextField SiBox = new JTextField();
        JTextField MnBox = new JTextField();
        JTextField PBox = new JTextField();
        JTextField SBox = new JTextField();
        JTextField CrBox = new JTextField();
        JTextField NiBox = new JTextField();
        JTextField MoBox = new JTextField();
        JTextField CuBox = new JTextField();
        JTextField AlBox = new JTextField();
        JTextField AsBox = new JTextField();
        JTextField VBox = new JTextField();
        JTextField WBox = new JTextField();
        JTextField TiBox = new JTextField();

        JLabel dateSubscr = new JLabel("Дата:");
        JLabel qualitySubscr = new JLabel("Марка стали:");
        JLabel bloomSubscr = new JLabel("Номер блюма:");
        JLabel seriaSubscr = new JLabel("Серия:");
        JLabel seriaFromSubscr = new JLabel("из");

        JLabel chemSubscr = new JLabel("C               Si              Mn              P               S               Cr              Ni              Mo              Cu              Al              V               W              Ti");
        JLabel chemLineSubscr = new JLabel("Значения");
        JLabel deviationLineSubscr = new JLabel("Отклонения");
        JCheckBox dateCheckBox = new JCheckBox((controller.config.isDateCheckBox())?"Вкл.":"Выкл.", controller.config.isDateCheckBox());//config
        JCheckBox qualityCheckBox = new JCheckBox(controller.config.isQualityCheckBox()?"Вкл.":"Выкл.", controller.config.isQualityCheckBox());//config
        JCheckBox bloomCheckBox = new JCheckBox(controller.config.isBloomCheckBox()?"Вкл.":"Выкл.", controller.config.isBloomCheckBox());//config
        JCheckBox seriaCheckBox = new JCheckBox(controller.config.isSeriaCheckBox()?"Вкл.":"Выкл.",controller.config.isSeriaCheckBox());//config
        JCheckBox datePeriodCheckBox = new JCheckBox("Период (от/до)", controller.config.isDatePeriodCheckBox());//config


        enableChemsButton.setBounds(10, 140, 150, 30);
        enableChemsButton.setBackground(controller.config.getChemsButton());
        firstDateBox.setBounds(10, 40, 150, 30);
        secondDateBox.setBounds(10, 80, 150, 30);
        qualityBox.setBounds(200, 40, 150, 30);
        bloomBox.setBounds(390, 40, 150, 30);
        seriaFromBox.setBounds(580, 40, 40, 30);
        seriaToBox.setBounds(650, 40, 40, 30);


        dateSubscr.setBounds(10,10,70,20);
        qualitySubscr.setBounds(200,10,90,20);
        bloomSubscr.setBounds(390, 10, 90, 20);
        seriaSubscr.setBounds(580, 10, 90, 20);
        seriaFromSubscr.setBounds(628, 43, 90, 20);
        seriaFromSubscr.setFont(new Font("Arial",Font.PLAIN,12));
        chemSubscr.setBounds(43, 190, 900, 20);
        chemLineSubscr.setBounds(43, 190, 900, 20);
        deviationLineSubscr.setBounds(43, 190, 900, 20);
        CBox.setBounds(20, 220, 50, 20);
        SiBox.setBounds(75, 220, 50, 20);
        MnBox.setBounds(130, 220, 50, 20);
        PBox.setBounds(185, 220, 50, 20);
        SBox.setBounds(240, 220, 50, 20);
        CrBox.setBounds(295, 220, 50, 20);
        NiBox.setBounds(350, 220, 50, 20);
        MoBox.setBounds(405, 220, 50, 20);
        CuBox.setBounds(460, 220, 50, 20);
        AlBox.setBounds(515, 220, 50, 20);
        VBox.setBounds(570, 220, 50, 20);
        WBox.setBounds(625, 220, 50, 20);
        TiBox.setBounds(680, 220, 50, 20);


        datePeriodCheckBox.setBounds(10, 110, 120, 30);
        dateCheckBox.setBounds(100, 5, 100, 30);
        qualityCheckBox.setBounds(290, 5, 100, 30);
        bloomCheckBox.setBounds(480, 5, 100, 30);
        seriaCheckBox.setBounds(630, 5, 100, 30);


        secondDateBox.setEnabled(false);
        Sframe.setSize(900, 220);
        Sframe.setResizable(false);

        Sframe.setLocation(400, 150);

        Sframe.add(enableChemsButton);


        Sframe.add(dateSubscr);
        Sframe.add(qualitySubscr);
        Sframe.add(bloomSubscr);
        Sframe.add(seriaSubscr);
        Sframe.add(seriaFromSubscr);
        Sframe.add(chemSubscr);
        Sframe.add(datePeriodCheckBox);
        Sframe.add(dateCheckBox);
        Sframe.add(qualityCheckBox);
        Sframe.add(bloomCheckBox);
        Sframe.add(seriaCheckBox);

        Sframe.add(firstDateBox);
        Sframe.add(secondDateBox);
        Sframe.add(qualityBox);
        Sframe.add(bloomBox);
        Sframe.add(seriaFromBox);
        Sframe.add(seriaToBox);
        Sframe.add(CBox);
        Sframe.add(SiBox);
        Sframe.add(MnBox);
        Sframe.add(PBox);
        Sframe.add(SBox);
        Sframe.add(CrBox);
        Sframe.add(NiBox);
        Sframe.add(MoBox);
        Sframe.add(CuBox);
        Sframe.add(AlBox);
        Sframe.add(VBox);
        Sframe.add(WBox);
        Sframe.add(TiBox);
        JPanel p = new JPanel() {
            @Override
            public void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g;
                //Shape line = new Line2D.Double(0, 180, 800, 180);
                g2.setColor(Color.LIGHT_GRAY);
                Shape rect = new Rectangle(0, 0, 900, 180);
                //g2.draw(line);
                g2.draw(rect);

            }
        };
        Sframe.getContentPane().add(p);

        if(dateCheckBox.isSelected()) {
            dateCheckBox.setForeground(new Color(4, 125, 0));
            dateCheckBox.setText("Вкл.");
        }
        else
        {
            dateCheckBox.setText("Выкл.");
            dateCheckBox.setForeground(Color.GRAY);
        }
        firstDateBox.setEnabled(dateCheckBox.isSelected());
        if(datePeriodCheckBox.isSelected())
            secondDateBox.setEnabled(dateCheckBox.isSelected());
        if(qualityCheckBox.isSelected()) {
            qualityCheckBox.setForeground(new Color(4, 125, 0));
            qualityCheckBox.setText("Вкл.");
        }
        else
        {
            qualityCheckBox.setText("Выкл.");
            qualityCheckBox.setForeground(Color.GRAY);
        }
        qualityBox.setEnabled(qualityCheckBox.isSelected());
        if(bloomCheckBox.isSelected()) {
            bloomCheckBox.setForeground(new Color(4, 125, 0));
            bloomCheckBox.setText("Вкл.");
        }
        else
        {
            bloomCheckBox.setText("Выкл.");
            bloomCheckBox.setForeground(Color.GRAY);
        }
        bloomBox.setEnabled(bloomCheckBox.isSelected());
        if(seriaCheckBox.isSelected()) {
            seriaCheckBox.setForeground(new Color(4, 125, 0));
            seriaCheckBox.setText("Вкл.");
        }
        else
        {
            seriaCheckBox.setText("Выкл.");
            seriaCheckBox.setForeground(Color.GRAY);
        }
        seriaToBox.setEnabled(seriaCheckBox.isSelected());
        seriaFromBox.setEnabled(seriaCheckBox.isSelected());


        datePeriodCheckBox.addActionListener(actionListener ->
        {
            if(dateCheckBox.isSelected())
            secondDateBox.setEnabled(datePeriodCheckBox.isSelected());
        });
        dateCheckBox.addActionListener(actionListener ->
        {
            if(dateCheckBox.isSelected()) {
                dateCheckBox.setForeground(new Color(4, 125, 0));
                dateCheckBox.setText("Вкл.");
            }
            else
            {
                dateCheckBox.setText("Выкл.");
                dateCheckBox.setForeground(Color.GRAY);
            }
            firstDateBox.setEnabled(dateCheckBox.isSelected());
            if(datePeriodCheckBox.isSelected())
            secondDateBox.setEnabled(dateCheckBox.isSelected());
        });
        qualityCheckBox.addActionListener(actionListener ->
        {
            if(qualityCheckBox.isSelected()) {
                qualityCheckBox.setForeground(new Color(4, 125, 0));
                qualityCheckBox.setText("Вкл.");
            }
            else
            {
                qualityCheckBox.setText("Выкл.");
                qualityCheckBox.setForeground(Color.GRAY);
            }
            qualityBox.setEnabled(qualityCheckBox.isSelected());
        });
        bloomCheckBox.addActionListener(actionListener ->
        {
            if(bloomCheckBox.isSelected()) {
                bloomCheckBox.setForeground(new Color(4, 125, 0));
                bloomCheckBox.setText("Вкл.");
            }
            else
            {
                bloomCheckBox.setText("Выкл.");
                bloomCheckBox.setForeground(Color.GRAY);
            }
            bloomBox.setEnabled(bloomCheckBox.isSelected());
        });
        seriaCheckBox.addActionListener(actionListener ->
        {
            if(seriaCheckBox.isSelected()) {
                seriaCheckBox.setForeground(new Color(4, 125, 0));
                seriaCheckBox.setText("Вкл.");
            }
            else
            {
                seriaCheckBox.setText("Выкл.");
                seriaCheckBox.setForeground(Color.GRAY);
            }
            seriaToBox.setEnabled(seriaCheckBox.isSelected());
            seriaFromBox.setEnabled(seriaCheckBox.isSelected());
        });
        enableChemsButton.addActionListener(actionListener ->
        {
            if (enableChemsButton.getBackground().getGreen() != 255)
            {
                enableChemsButton.setBackground(new Color(130, 255, 130));
                Sframe.setSize(900,600);
            }
            else {
                enableChemsButton.setBackground(null);
                Sframe.setSize(900, 220);
            }


        });

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
        searchSettingsButton.addActionListener(actionListener ->
        {
            if(enableChemsButton.getBackground().getGreen() != 255) Sframe.setSize(900, 220);
            else Sframe.setSize(900,600);

        Sframe.setVisible(true);



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
        frame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                controller.config = Config.builder()
                        .dateCheckBox(dateCheckBox.isSelected())
                        .datePeriodCheckBox(datePeriodCheckBox.isSelected())
                        .qualityCheckBox(qualityCheckBox.isSelected())
                        .bloomCheckBox(bloomCheckBox.isSelected())
                        .seriaCheckBox(seriaCheckBox.isSelected())
                        .chemsButton(enableChemsButton.getBackground())
                        .build();


                controller.setConfig(controller.config);
            }
        });
    }

}
