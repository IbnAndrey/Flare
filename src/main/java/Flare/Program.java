package Flare;
import Flare.Modules.ComplexSearch;
import Flare.Modules.Config;
import Flare.Modules.ImportFromExcel;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.geom.Line2D;
import java.io.File;
import java.io.IOException;




public class Program {



    public static void main(String[] args) {

        JFrame frame = new JFrame("Flare");//TODO: Кнопку переноса образца из одного хранилища в другое
        JFileChooser fileChooser = new JFileChooser();
        Controller controller = new Controller();
        ComplexSearch complexSearcher = new ComplexSearch();
        JButton OpenFile = new JButton("Импорт из Excel");
        JButton SaveTableButton = new JButton("Сохранить");
        JButton deleteButton = new JButton("Удалить");
        JButton addButton = new JButton("Добавить");
        deleteButton.setToolTipText("Удаляет выделенный елемент(-ы)");
        Icon searchIcon = new ImageIcon("icons/search.png");
        Icon settingsIcon = new ImageIcon("icons/settings.png");
        Icon refreshIcon = new ImageIcon("icons/refresh.png");
        Icon MrIcon = new ImageIcon("icons/Mr.png");
        Icon mRIcon = new ImageIcon("icons/Rm.png");
        Icon autoSearchIcon = new ImageIcon("icons/autosearch.png");
        Icon searchNextIcon = new ImageIcon("icons/arrow.png");
        Icon searchStopIcon = new ImageIcon("icons/cross.png");
        Icon autoSearchParamsIcon = new ImageIcon("icons/autosearchparams.png");
        Icon searchInfoIcon = new ImageIcon("icons/info.png");
        Icon changeStorageIcon = new ImageIcon("icons/clean.png");
        Icon bookmarkIcon = new ImageIcon("icons/bookmarks.png");
        Icon addBookmarkIcon = new ImageIcon("icons/addbookmark.png");
        Icon deleteBookmarkIcon = new ImageIcon("icons/deletebookmark.png");
        JButton searchButton = new JButton();
        JButton autoSearchButton = new JButton();
        JButton searchNextButton = new JButton();
        JButton searchStopButton = new JButton();
        JButton searchParamsButton = new JButton();
        JButton searchInfoButton = new JButton();
        JButton changeStorageButton = new JButton();
        JButton addToBookmarkButton = new JButton();
        JButton deleteBookmarkButton = new JButton();
        JButton bookmarkButton = new JButton();

        JLabel autoSearchSubscr = new JLabel("Автопоиск");
        JLabel tableOpsSubscr = new JLabel("Таблица");
        JLabel manualSearchSubscr = new JLabel("Ручной поиск");
        JCheckBox samplesPerStepCheckBox = new JCheckBox("обр. за шаг");
        samplesPerStepCheckBox.setSelected(controller.config.isSamplesPerStepCheckBox());
        samplesPerStepCheckBox.setFont(new Font("Arial",Font.PLAIN,14));
        autoSearchSubscr.setForeground(Color.GRAY);
        autoSearchSubscr.setFont(new Font("Arial",Font.PLAIN,14));
        autoSearchSubscr.setForeground(Color.GRAY);
        manualSearchSubscr.setFont(new Font("Arial",Font.PLAIN,14));
        manualSearchSubscr.setForeground(Color.GRAY);
        tableOpsSubscr.setFont(new Font("Arial",Font.PLAIN,14));
        tableOpsSubscr.setForeground(Color.GRAY);
        searchButton.setIcon(searchIcon);
        autoSearchButton.setIcon(autoSearchIcon);
        searchNextButton.setIcon(searchNextIcon);
        searchStopButton.setIcon(searchStopIcon);
        searchParamsButton.setIcon(autoSearchParamsIcon);
        autoSearchButton.setIcon(autoSearchIcon);
        searchInfoButton.setIcon(searchInfoIcon);
        changeStorageButton.setIcon(changeStorageIcon);
        bookmarkButton.setIcon(bookmarkIcon);
        addToBookmarkButton.setIcon(addBookmarkIcon);
        deleteBookmarkButton.setIcon(deleteBookmarkIcon);
        changeStorageButton.setToolTipText("Перекидывает образец в другое хранилище");
        JButton searchSettingsButton = new JButton();
        searchSettingsButton.setIcon(settingsIcon);
        searchSettingsButton.setToolTipText("Расширенные параметры поиска");
        JTextField searchBox = new JTextField();
        JTextField stepBox = new JTextField(controller.config.getSamplesPerStep());
        JButton refreshButton = new JButton();
        JButton changeSampleTypeButton = new JButton();
        changeSampleTypeButton.setIcon(MrIcon);
        changeSampleTypeButton.setToolTipText("Для отображения выбраны маркировочные образцы. Кликните, чтобы отображать разрывные");
        refreshButton.setIcon(refreshIcon);
        refreshButton.setToolTipText("Заново вывести данные в таблицу");
        searchBox.setToolTipText("Введите номер образца для поиска");
        JScrollPane output = new JScrollPane(controller.createOutputTable());
        output.setBounds(0, 0, 1100, 600);
        OpenFile.setBounds(325, 620, 150, 30);
        deleteButton.setBounds(525, 620, 150, 30);
        SaveTableButton.setBounds(125, 620, 150, 30);
        searchBox.setBounds(1105, 40, 136, 30);
        autoSearchSubscr.setBounds(1140, 150, 150, 30);
        manualSearchSubscr.setBounds(1130, 0, 150, 30);
        tableOpsSubscr.setBounds(1140, 330, 150, 30);
        autoSearchButton.setBounds(1105, 190, 30, 30);
        searchButton.setBounds(1105, 70, 30, 30);
        searchSettingsButton.setBounds(1140, 70, 30, 30);
        refreshButton.setBounds(1175, 70, 30, 30);
        addButton.setBounds(725, 620, 150, 30);
        changeSampleTypeButton.setBounds(1210, 70, 30, 30);
        searchNextButton.setBounds(1140, 190, 60, 30);
        searchStopButton.setBounds(1205, 190, 30, 30);
        searchParamsButton.setBounds(1105, 225, 30, 30);
        changeStorageButton.setBounds(1210, 370, 30, 30);
        bookmarkButton.setBounds(1105, 370, 30, 30);
        addToBookmarkButton.setBounds(1140, 370, 30, 30);
        deleteBookmarkButton.setBounds(1175, 370, 30, 30);
        samplesPerStepCheckBox.setBounds(1100, 275, 100, 30);
        stepBox.setBounds(1210, 275, 20, 30);
        searchInfoButton.setBounds(1205, 225, 30, 30);
        autoSearchButton.setEnabled(false);
        searchNextButton.setEnabled(false);
        searchStopButton.setEnabled(false);
        searchInfoButton.setEnabled(false);
        frame.setSize(1265, 720);
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
        frame.add(addButton);
        frame.add(changeSampleTypeButton);
        frame.add(searchBox);
        frame.add(autoSearchSubscr);
        frame.add(manualSearchSubscr);
        frame.add(tableOpsSubscr);
        frame.add(autoSearchButton);
        frame.add(searchNextButton);
        frame.add(searchStopButton);
        frame.add(searchParamsButton);
        frame.add(searchInfoButton);
        frame.add(changeStorageButton);
        frame.add(samplesPerStepCheckBox);
        frame.add(bookmarkButton);
        frame.add(addToBookmarkButton);
        frame.add(deleteBookmarkButton);
        frame.add(stepBox);
        frame.setVisible(true);


        JDialog addframe = new JDialog(frame,"Ввод образца",true);
        JButton addSampleButton = new JButton();
        Icon saveIcon = new ImageIcon("icons/save.png");
        addframe.getContentPane().setLayout(null);
        JScrollPane input = new JScrollPane(controller.createInputTable());
        addSampleButton.setIcon(saveIcon);
        addSampleButton.setToolTipText("Записать образец. Его принадлежность к разрывным или маркировочным определится автоматически.");
        input.setBounds(0, 0, 1100, 39);
        addSampleButton.setBounds(1120, 0, 40, 40);
        addframe.setSize(1200, 100);
        addframe.add(input);
        addframe.add(addSampleButton);

        if(samplesPerStepCheckBox.isSelected()) {
            stepBox.setEnabled(true);
        }
        else
        {
            stepBox.setEnabled(false);
        }
/*


            ОКНО РАСШИРЕННОГО ПОИСКА


 */

        JDialog Sframe = new JDialog(frame,"Расширенный поиск",true);
        JButton enableChemsButton = new JButton("Хим. элементы");
        JButton advancedSearchButton = new JButton();
        Icon advancedSearchIcon = new ImageIcon("icons/hiResSearch.png");
        advancedSearchButton.setIcon(advancedSearchIcon);
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
        JTextField VBox = new JTextField();
        JTextField WBox = new JTextField();
        JTextField TiBox = new JTextField();
        JTextField CDevBox = new JTextField(controller.config.getCDev());
        JTextField SiDevBox = new JTextField(controller.config.getSiDev());
        JTextField MnDevBox = new JTextField(controller.config.getMnDev());
        JTextField PDevBox = new JTextField(controller.config.getPDev());
        JTextField SDevBox = new JTextField(controller.config.getSDev());
        JTextField CrDevBox = new JTextField(controller.config.getCrDev());
        JTextField NiDevBox = new JTextField(controller.config.getNiDev());
        JTextField MoDevBox = new JTextField(controller.config.getMoDev());
        JTextField CuDevBox = new JTextField(controller.config.getCuDev());
        JTextField AlDevBox = new JTextField(controller.config.getAlDev());
        JTextField VDevBox = new JTextField(controller.config.getVDev());
        JTextField WDevBox = new JTextField(controller.config.getWDev());
        JTextField TiDevBox = new JTextField(controller.config.getTiDev());

        JLabel dateSubscr = new JLabel("Дата:");
        JLabel qualitySubscr = new JLabel("Марка стали:");
        JLabel bloomSubscr = new JLabel("Номер блюма:");
        JLabel seriaSubscr = new JLabel("Серия:");
        JLabel seriaFromSubscr = new JLabel("из");

        JLabel chemSubscr = new JLabel("C               Si              Mn              P               S               Cr              Ni              Mo              Cu              Al              V               W              Ti");
        JLabel chemLineSubscr = new JLabel("значения");
        chemLineSubscr.setToolTipText("Значения хим. элементов");
        JLabel deviationLineSubscr = new JLabel("отклонения");
        deviationLineSubscr.setToolTipText(" +- отклонение от заданных значений хим. элементов при поиске конкретного образца");
        JCheckBox dateCheckBox = new JCheckBox((controller.config.isDateCheckBox())?"Вкл.":"Выкл.", controller.config.isDateCheckBox());//config
        JCheckBox qualityCheckBox = new JCheckBox(controller.config.isQualityCheckBox()?"Вкл.":"Выкл.", controller.config.isQualityCheckBox());//config
        JCheckBox bloomCheckBox = new JCheckBox(controller.config.isBloomCheckBox()?"Вкл.":"Выкл.", controller.config.isBloomCheckBox());//config
        JCheckBox seriaCheckBox = new JCheckBox(controller.config.isSeriaCheckBox()?"Вкл.":"Выкл.",controller.config.isSeriaCheckBox());//config
        JCheckBox datePeriodCheckBox = new JCheckBox("Период (от/до)", controller.config.isDatePeriodCheckBox());//config


        enableChemsButton.setBounds(10, 140, 150, 30);
        enableChemsButton.setBackground(controller.config.getChemsButton());
        advancedSearchButton.setBounds(760,80,50,50);
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
        chemLineSubscr.setBounds(750, 220, 100, 20);
        chemLineSubscr.setFont(new Font("Arial",Font.PLAIN,14));
        deviationLineSubscr.setBounds(750, 250, 100, 20);
        deviationLineSubscr.setFont(new Font("Arial",Font.PLAIN,14));
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
        CDevBox.setBounds(20, 250, 50, 20);
        SiDevBox.setBounds(75, 250, 50, 20);
        MnDevBox.setBounds(130, 250, 50, 20);
        PDevBox.setBounds(185, 250, 50, 20);
        SDevBox.setBounds(240, 250, 50, 20);
        CrDevBox.setBounds(295, 250, 50, 20);
        NiDevBox.setBounds(350, 250, 50, 20);
        MoDevBox.setBounds(405, 250, 50, 20);
        CuDevBox.setBounds(460, 250, 50, 20);
        AlDevBox.setBounds(515, 250, 50, 20);
        VDevBox.setBounds(570, 250, 50, 20);
        WDevBox.setBounds(625, 250, 50, 20);
        TiDevBox.setBounds(680, 250, 50, 20);

        datePeriodCheckBox.setBounds(10, 110, 120, 30);
        dateCheckBox.setBounds(100, 5, 100, 30);
        qualityCheckBox.setBounds(290, 5, 100, 30);
        bloomCheckBox.setBounds(480, 5, 100, 30);
        seriaCheckBox.setBounds(630, 5, 100, 30);


        secondDateBox.setEnabled(false);
        Sframe.setSize(800, 220);
        Sframe.setResizable(false);

        Sframe.setLocation(400, 150);

        Sframe.add(enableChemsButton);
        Sframe.add(advancedSearchButton);


        Sframe.add(dateSubscr);
        Sframe.add(qualitySubscr);
        Sframe.add(bloomSubscr);
        Sframe.add(seriaSubscr);
        Sframe.add(seriaFromSubscr);
        Sframe.add(chemSubscr);
        Sframe.add(chemLineSubscr);
        Sframe.add(deviationLineSubscr);
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
        Sframe.add(CDevBox);
        Sframe.add(SiDevBox);
        Sframe.add(MnDevBox);
        Sframe.add(PDevBox);
        Sframe.add(SDevBox);
        Sframe.add(CrDevBox);
        Sframe.add(NiDevBox);
        Sframe.add(MoDevBox);
        Sframe.add(CuDevBox);
        Sframe.add(AlDevBox);
        Sframe.add(VDevBox);
        Sframe.add(WDevBox);
        Sframe.add(TiDevBox);
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

        JDialog paramsframe = new JDialog(frame,"Параметры автопоиска",true);
        paramsframe.getContentPane().setLayout(null);
        JLabel chemSubscr1 = new JLabel(chemSubscr.getText());
        JLabel deviationLineSubscr1 = new JLabel(deviationLineSubscr.getText());
        JLabel stepSubscr = new JLabel("шаг");
        JLabel dateSubscr1 = new JLabel("по");
        JCheckBox dateCheckBox1 = new JCheckBox("В пределах даты");
        dateCheckBox1.setSelected(controller.config.isDateCheckBox1());
        deviationLineSubscr1.setFont(new Font("Arial",Font.PLAIN,14));
        stepSubscr.setFont(new Font("Arial",Font.PLAIN,14));
        dateSubscr1.setFont(new Font("Arial",Font.PLAIN,14));
        JTextField CDevBox1 = new JTextField(controller.config.getCDevAuto());
        JTextField SiDevBox1 = new JTextField(controller.config.getSiDevAuto());
        JTextField MnDevBox1 = new JTextField(controller.config.getMnDevAuto());
        JTextField PDevBox1 = new JTextField(controller.config.getPDevAuto());
        JTextField SDevBox1 = new JTextField(controller.config.getSDevAuto());
        JTextField CrDevBox1 = new JTextField(controller.config.getCrDevAuto());
        JTextField NiDevBox1 = new JTextField(controller.config.getNiDevAuto());
        JTextField MoDevBox1 = new JTextField(controller.config.getMoDevAuto());
        JTextField CuDevBox1 = new JTextField(controller.config.getCuDevAuto());
        JTextField AlDevBox1 = new JTextField(controller.config.getAlDevAuto());
        JTextField VDevBox1 = new JTextField(controller.config.getVDevAuto());
        JTextField WDevBox1 = new JTextField(controller.config.getWDevAuto());
        JTextField TiDevBox1 = new JTextField(controller.config.getTiDevAuto());
        JTextField CStepBox = new JTextField(controller.config.getCStepAuto());
        JTextField SiStepBox = new JTextField(controller.config.getSiStepAuto());
        JTextField MnStepBox = new JTextField(controller.config.getMnStepAuto());
        JTextField PStepBox = new JTextField(controller.config.getPStepAuto());
        JTextField SStepBox = new JTextField(controller.config.getSStepAuto());
        JTextField CrStepBox = new JTextField(controller.config.getCrStepAuto());
        JTextField NiStepBox = new JTextField(controller.config.getNiStepAuto());
        JTextField MoStepBox = new JTextField(controller.config.getMoStepAuto());
        JTextField CuStepBox = new JTextField(controller.config.getCuStepAuto());
        JTextField AlStepBox = new JTextField(controller.config.getAlStepAuto());
        JTextField VStepBox = new JTextField(controller.config.getVStepAuto());
        JTextField WStepBox = new JTextField(controller.config.getWStepAuto());
        JTextField TiStepBox = new JTextField(controller.config.getTiStepAuto());

        JTextField firstDateBox1 = new JTextField();
        JTextField secondDateBox1 = new JTextField();

        chemSubscr1.setBounds(43, 10, 900, 20);
        deviationLineSubscr1.setBounds(750, 30, 100, 20);
        stepSubscr.setBounds(750, 73, 900, 20);
        paramsframe.setSize(850, 200);
        CDevBox1.setBounds(20,30 , 50, 20);
        SiDevBox1.setBounds(75, 30, 50, 20);
        MnDevBox1.setBounds(130, 30, 50, 20);
        PDevBox1.setBounds(185, 30, 50, 20);
        SDevBox1.setBounds(240, 30, 50, 20);
        CrDevBox1.setBounds(295, 30, 50, 20);
        NiDevBox1.setBounds(350, 30, 50, 20);
        MoDevBox1.setBounds(405, 30, 50, 20);
        CuDevBox1.setBounds(460, 30, 50, 20);
        AlDevBox1.setBounds(515, 30, 50, 20);
        VDevBox1.setBounds(570, 30, 50, 20);
        WDevBox1.setBounds(625, 30, 50, 20);
        TiDevBox1.setBounds(680, 30, 50, 20);
        CStepBox.setBounds(20,70 , 50, 20);
        SiStepBox.setBounds(75, 70, 50, 20);
        MnStepBox.setBounds(130, 70, 50, 20);
        PStepBox.setBounds(185, 70, 50, 20);
        SStepBox.setBounds(240, 70, 50, 20);
        CrStepBox.setBounds(295, 70, 50, 20);
        NiStepBox.setBounds(350, 70, 50, 20);
        MoStepBox.setBounds(405, 70, 50, 20);
        CuStepBox.setBounds(460, 70, 50, 20);
        AlStepBox.setBounds(515, 70, 50, 20);
        VStepBox.setBounds(570, 70, 50, 20);
        WStepBox.setBounds(625, 70, 50, 20);
        TiStepBox.setBounds(680, 70, 50, 20);
        firstDateBox1.setBounds(20,120,150,30);
        secondDateBox1.setBounds(200,120,150,30);
        dateSubscr1.setBounds(175,120,20,30);
        dateCheckBox1.setBounds(360,120,200,30);

        paramsframe.add(chemSubscr1);
        paramsframe.add(deviationLineSubscr1);
        paramsframe.add(stepSubscr);
        paramsframe.add(TiDevBox1);
        paramsframe.add(CDevBox1);
        paramsframe.add(SiDevBox1);
        paramsframe.add(MnDevBox1);
        paramsframe.add(PDevBox1);
        paramsframe.add(SDevBox1);
        paramsframe.add(CrDevBox1);
        paramsframe.add(NiDevBox1);
        paramsframe.add(MoDevBox1);
        paramsframe.add(CuDevBox1);
        paramsframe.add(AlDevBox1);
        paramsframe.add(VDevBox1);
        paramsframe.add(WDevBox1);
        paramsframe.add(TiDevBox1);

        paramsframe.add(TiStepBox);
        paramsframe.add(CStepBox);
        paramsframe.add(SiStepBox);
        paramsframe.add(MnStepBox);
        paramsframe.add(PStepBox);
        paramsframe.add(SStepBox);
        paramsframe.add(CrStepBox);
        paramsframe.add(NiStepBox);
        paramsframe.add(MoStepBox);
        paramsframe.add(CuStepBox);
        paramsframe.add(AlStepBox);
        paramsframe.add(VStepBox);
        paramsframe.add(WStepBox);
        paramsframe.add(TiStepBox);
        paramsframe.add(firstDateBox1);
        paramsframe.add(secondDateBox1);
        paramsframe.add(dateSubscr1);
        paramsframe.add(dateCheckBox1);


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
                Sframe.setSize(850,350);
            }
            else {
                enableChemsButton.setBackground(null);
                Sframe.setSize(850, 220);
            }


        });
        advancedSearchButton.addActionListener(actionListener ->
        {
        if(dateCheckBox.isSelected()&&datePeriodCheckBox.isSelected())
            controller.print(complexSearcher.searchByDate(firstDateBox.getText(),secondDateBox.getText(),controller.getTableValue()));
        else if(dateCheckBox.isSelected())
            controller.print(complexSearcher.searchByDate(firstDateBox.getText(),null,controller.getTableValue()));
        if(qualityCheckBox.isSelected())  controller.print(complexSearcher.searchByQuality(qualityBox.getText(),controller.getTableValue()));
        if(bloomCheckBox.isSelected())  controller.print(complexSearcher.searchByBloom(bloomBox.getText(),controller.getTableValue()));
        if(seriaCheckBox.isSelected())  controller.print(complexSearcher.searchBySeria(seriaFromBox.getText(),seriaToBox.getText(), controller.getTableValue()));
        if(enableChemsButton.getBackground().getGreen()==255)
        {
            controller.config.setCDev(CDevBox.getText());
            controller.config.setSiDev(SiDevBox.getText());
            controller.config.setMnDev(MnDevBox.getText());
            controller.config.setPDev(PDevBox.getText());
            controller.config.setSDev(SDevBox.getText());
            controller.config.setCrDev(CrDevBox.getText());
            controller.config.setNiDev(NiDevBox.getText());
            controller.config.setMoDev(MoDevBox.getText());
            controller.config.setCuDev(CuDevBox.getText());
            controller.config.setAlDev(AlDevBox.getText());
            controller.config.setVDev(VDevBox.getText());
            controller.config.setWDev(WDevBox.getText());
            controller.config.setTiDev(TiDevBox.getText());
            controller.print(complexSearcher.searchByChems(
            CBox.getText(),
            SiBox.getText(),
            MnBox.getText(),
            PBox.getText(),
            SBox.getText(),
            CrBox.getText(),
            NiBox.getText(),
            MoBox.getText(),
            CuBox.getText(),
            AlBox.getText(),
            VBox.getText(),
            WBox .getText(),
            TiBox.getText(),
            controller.config,controller.getTableValue()));
        }
            SaveTableButton.setEnabled(false);
            Sframe.dispose();
        });





        samplesPerStepCheckBox.addActionListener(actionListener ->
        {
            if(samplesPerStepCheckBox.isSelected())stepBox.setEnabled(true);
            else stepBox.setEnabled(false);
        });

        autoSearchButton.addActionListener(actionListener ->
        {
        if(controller.getOutput_table().getSelectedRow()!=-1)
        {
        controller.startAutosearch();
        searchInfoButton.setEnabled(true);
        if(!controller.getSearcher().isFindSuccess())searchNextButton.setEnabled(true);
        }
        else JOptionPane.showMessageDialog(frame,"Выберите разрывной образец","Ошибка",JOptionPane.ERROR_MESSAGE);
        });

        searchInfoButton.addActionListener(actionListener ->
        {
            JOptionPane.showMessageDialog(frame,controller.getSearcher().getReport(),"Завершено",JOptionPane.INFORMATION_MESSAGE);
        });

        searchNextButton.addActionListener(actionListener ->
        {
            controller.config.setSamplesPerStep(stepBox.getText());
            controller.config.setSamplesPerStepCheckBox(samplesPerStepCheckBox.isSelected());
            controller.config.setSamplesPerStep(stepBox.getText());
            controller.startStepsearch(firstDateBox1.getText(),secondDateBox1.getText());
            searchStopButton.setEnabled(true);
        });
        searchStopButton.addActionListener(actionListener ->
        {
            controller.stopStepsearch();
            searchStopButton.setEnabled(false);
            controller.print(controller.pullData("MSamples.dat"));
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
                    controller.pushData(ImportFromExcel.GetExcelData(ExcelFile));
                    if(changeSampleTypeButton.getIcon().equals(MrIcon)) controller.print(controller.pullData("MSamples.dat"));
                    else controller.print(controller.pullData("RSamples.dat"));
                }
                catch(IOException e)
                {
                    JOptionPane.showMessageDialog(frame,"Ошибка открытия файла. Возможно:\n 1. Файл не имеет нужных колонок, или он пуст;\n 2. Выбран некорректный файл.","Ошибка",JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        SaveTableButton.addActionListener(actionListener ->
        {
            if(changeSampleTypeButton.getIcon().equals(MrIcon))controller.printOutputTable("MSamples.dat");
            else controller.printOutputTable("RSamples.dat");
        });

        searchButton.addActionListener(actionListener ->
        {
            if(searchBox.getText().length()>0) {
                SaveTableButton.setEnabled(false);
            }
            controller.print(complexSearcher.searchByName(searchBox.getText(),controller.getTableValue()));;

        });
        searchSettingsButton.addActionListener(actionListener ->
        {
            if(enableChemsButton.getBackground().getGreen() != 255) Sframe.setSize(850, 220);
            else Sframe.setSize(850,350);

        Sframe.setVisible(true);



        });
        refreshButton.addActionListener(actionListener ->
        {

            SaveTableButton.setEnabled(true);
            searchInfoButton.setEnabled(false);
            searchNextButton.setEnabled(false);
            searchBox.setText(null);
            if(changeSampleTypeButton.getIcon().equals(MrIcon)) controller.print(controller.pullData("MSamples.dat"));
            else controller.print(controller.pullData("RSamples.dat"));
        });
        changeSampleTypeButton.addActionListener(actionListener ->
        {
            if(changeSampleTypeButton.getIcon().equals(MrIcon))
            {
                changeSampleTypeButton.setIcon(mRIcon);
                changeSampleTypeButton.setToolTipText("Для отображения выбраны разрывные образцы. Кликните, чтобы отображать маркировочные");
                controller.print(controller.pullData("RSamples.dat"));
                autoSearchButton.setEnabled(true);
            }
            else
            {
                changeSampleTypeButton.setIcon(MrIcon);
                changeSampleTypeButton.setToolTipText("Для отображения выбраны маркировочные образцы. Кликните, чтобы отображать разрывные");
                controller.print(controller.pullData("MSamples.dat"));
                autoSearchButton.setEnabled(false);
                searchNextButton.setEnabled(false);
                searchInfoButton.setEnabled(false);
            }
        });
        deleteButton.addActionListener(actionListener ->
        {
            if(changeSampleTypeButton.getIcon().equals(MrIcon)) controller.delete("MSamples.dat");
            else controller.delete("RSamples.dat");
        });
        addButton.addActionListener(actionListener ->
        {
            addframe.setVisible(true);
        });
        addSampleButton.addActionListener(actionListener ->
        {
            controller.addSampleManually();
            addframe.dispose();
            JOptionPane.showMessageDialog(frame,"Образец добавлен","Добавление",JOptionPane.INFORMATION_MESSAGE);
        });
        searchParamsButton.addActionListener(actionListener ->
        {
            paramsframe.setVisible(true);
        });
        bookmarkButton.addActionListener(actionListener ->
        {
            controller.print(controller.pullData("Bookmarks.dat"));
        });
        deleteBookmarkButton.addActionListener(actionListener ->
        {
            controller.delete("Bookmarks.dat");
        });
        addToBookmarkButton.addActionListener(actionListener ->
        {
            controller.copySelectedInto("Bookmarks.dat");
        });
        changeStorageButton.addActionListener(actionListener ->
        {
            if(changeSampleTypeButton.getIcon().equals(MrIcon))
            {
                controller.copySelectedInto("RSamples.dat");
                controller.delete("MSamples.dat");
            }
            else
            {
                controller.copySelectedInto("MSamples.dat");
                controller.delete("RSamples.dat");
            }
        });

        paramsframe.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                controller.config.setCDevAuto(CDevBox1.getText());
                controller.config.setSiDevAuto(SiDevBox1.getText());
                controller.config.setMnDevAuto(MnDevBox1.getText());
                controller.config.setPDevAuto(PDevBox1.getText());
                controller.config.setSDevAuto(SDevBox1.getText());
                controller.config.setCrDevAuto(CrDevBox1.getText());
                controller.config.setNiDevAuto(NiDevBox1.getText());
                controller.config.setMoDevAuto(MoDevBox1.getText());
                controller.config.setCuDevAuto(CuDevBox1.getText());
                controller.config.setAlDevAuto(AlDevBox1.getText());
                controller.config.setVDevAuto(VDevBox1.getText());
                controller.config.setWDevAuto(WDevBox1.getText());
                controller.config.setTiDevAuto(TiDevBox1.getText());
                controller.config.setCStepAuto(CStepBox.getText());
                controller.config.setSiStepAuto(SiStepBox.getText());
                controller.config.setMnStepAuto(MnStepBox.getText());
                controller.config.setPStepAuto(PStepBox.getText());
                controller.config.setSStepAuto(SStepBox.getText());
                controller.config.setCrStepAuto(CrStepBox.getText());
                controller.config.setNiStepAuto(NiStepBox.getText());
                controller.config.setMoStepAuto(MoStepBox.getText());
                controller.config.setCuStepAuto(CuStepBox.getText());
                controller.config.setAlStepAuto(AlStepBox.getText());
                controller.config.setVStepAuto(VStepBox.getText());
                controller.config.setWStepAuto(WStepBox.getText());
                controller.config.setTiStepAuto(TiStepBox.getText());
                controller.config.setDateCheckBox1(dateCheckBox1.isSelected());
            }
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
                        .CDev(CDevBox.getText())
                        .SiDev(SiDevBox.getText())
                        .MnDev(MnDevBox.getText())
                        .PDev(PDevBox.getText())
                        .SDev(SDevBox.getText())
                        .CrDev(CrDevBox.getText())
                        .NiDev(NiDevBox.getText())
                        .MoDev(MoDevBox.getText())
                        .CuDev(CuDevBox.getText())
                        .AlDev(AlDevBox.getText())
                        .VDev(VDevBox.getText())
                        .WDev(WDevBox.getText())
                        .TiDev(TiDevBox.getText())
                        .CDevAuto(CDevBox1.getText())
                        .SiDevAuto(SiDevBox1.getText())
                        .MnDevAuto(MnDevBox1.getText())
                        .PDevAuto(PDevBox1.getText())
                        .SDevAuto(SDevBox1.getText())
                        .CrDevAuto(CrDevBox1.getText())
                        .NiDevAuto(NiDevBox1.getText())
                        .MoDevAuto(MoDevBox1.getText())
                        .CuDevAuto(CuDevBox1.getText())
                        .AlDevAuto(AlDevBox1.getText())
                        .VDevAuto(VDevBox1.getText())
                        .WDevAuto(WDevBox1.getText())
                        .TiDevAuto(TiDevBox1.getText())
                        .CStepAuto(CStepBox.getText())
                        .SiStepAuto(SiStepBox.getText())
                        .MnStepAuto(MnStepBox.getText())
                        .PStepAuto(PStepBox.getText())
                        .SStepAuto(SStepBox.getText())
                        .CrStepAuto(CrStepBox.getText())
                        .NiStepAuto(NiStepBox.getText())
                        .MoStepAuto(MoStepBox.getText())
                        .CuStepAuto(CuStepBox.getText())
                        .AlStepAuto(AlStepBox.getText())
                        .VStepAuto(VStepBox.getText())
                        .WStepAuto(WStepBox.getText())
                        .TiStepAuto(TiStepBox.getText())
                        .samplesPerStep(stepBox.getText())
                        .samplesPerStepCheckBox(samplesPerStepCheckBox.isSelected())
                        .dateCheckBox1(dateCheckBox1.isSelected())
                        .build();
                controller.setConfig(controller.config);
            }
        });
    }
}