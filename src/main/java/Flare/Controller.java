package Flare;

import Flare.Modules.ComplexSearch;
import Flare.Modules.Config;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import lombok.*;

@Getter


public class Controller {
    private final String[] header = new String[]{"Дата","Время","Номер образца", "Марка стали", "Блюм", "Кол-во блюмов","Серия", "C","Si","Mn","P","S","Cr","Ni","Mo","Cu","Al","V","W","Ti"};
    private JTable output_table,input_table;
    private DefaultTableModel output_table_inner = new DefaultTableModel(header, 0);
    private DefaultTableModel input_table_inner = new DefaultTableModel(header, 3);
    private List<MSample> tableList = new ArrayList<>();
    private ComplexSearch searcher = new ComplexSearch();
    private MSample bufferSample;
    public Config config;


    public JTable createOutputTable() {
        print(pullData("MSamples.dat"));
        output_table = new JTable(output_table_inner);
        output_table.putClientProperty("terminateEditOnFocusLost", true);
        return output_table;
    }
    public JTable createInputTable()
    {
        input_table = new JTable(input_table_inner);
        input_table.putClientProperty("terminateEditOnFocusLost", true);
        input_table_inner.setRowCount(1);
        return input_table;
    }

    public Controller()
    {
        getConfig();
    }
    public void print(List<MSample> tableList) {
        output_table_inner.setRowCount(0);
        tableList.forEach(sample ->
        {
            String date = sample.getDate();
            String time = sample.getTime();
            String sample_No = sample.getSampleNo();
            String quality = sample.getQuality();
            String bloom = sample.getBloom();
            String bloomCount = sample.getCodes();
            String seria = sample.getSeria();
            String C = sample.getC();
            String Si = sample.getSi();
            String Mn = sample.getMn();
            String P = sample.getP();
            String S = sample.getS();
            String Cr = sample.getCr();
            String Ni = sample.getNi();
            String Mo = sample.getMo();
            String Cu = sample.getCu();
            String Al = sample.getAl();
            String V = sample.getV();
            String W = sample.getW();
            String Ti = sample.getTi();
            Object[] rowData = {date,time,sample_No,quality,bloom,bloomCount,seria,C,Si,Mn,P,S,Cr,Ni,Mo,Cu,Al,V,W,Ti};
            output_table_inner.addRow(rowData);
        });
    }

    public List<MSample> pullData(String target) {
        File f = new File(target);

        if(!f.exists()){
            try {
                f.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        List<MSample> sampleList = new ArrayList<>();
        try (ObjectInputStream fout = new ObjectInputStream(new FileInputStream(target))) {
            sampleList = ((ArrayList<MSample>) fout.readObject());
        } catch (Exception e) {

        }
        return sampleList;
    }

    public void pushData(List<MSample> collectedData){
        List<MSample> sampleMList = new ArrayList<>();
        List<MSample> sampleRList = new ArrayList<>();
        for (int i = 0; i<collectedData.size();++i)
        {
            if(collectedData.get(i).getSampleNo().contains("M"))
            {
                sampleMList.add(collectedData.get(i));
            }
            else
            {
                if(collectedData.get(i).getSampleNo().contains("R")||
                        collectedData.get(i).getSampleNo().contains("P")||
                        collectedData.get(i).getSampleNo().contains("T")||
                        collectedData.get(i).getSampleNo().contains("C")||
                        collectedData.get(i).getSampleNo().contains("U")
                )sampleRList.add(collectedData.get(i));
                else sampleMList.add(collectedData.get(i));
            }
        }

        sampleMList.addAll(pullData("MSamples.dat"));
        sampleRList.addAll(pullData("RSamples.dat"));
        refreshData(sampleMList,"MSamples.dat");
        refreshData(sampleRList,"RSamples.dat");
    }

    public void refreshData(List<MSample> sample,String target) {
        try (ObjectOutputStream fout = new ObjectOutputStream(new FileOutputStream(target))) {
            fout.writeObject(sample);
            fout.flush();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
    public void addSampleManually()
    {
        List<MSample> sampleList = new ArrayList<>();
        String[] data = new String[20];
            for (int j = 0; j < input_table.getColumnCount(); j++) {
                data[j] = String.valueOf(input_table_inner.getValueAt(0, j));
                if (data[j] == "null") data[j] = null;
            }
            MSample sample = MSample.builder()
                    .date(data[0])
                    .time(data[1])
                    .sampleNo(data[2])
                    .quality(data[3])
                    .bloom(data[4])
                    .codes(data[5])
                    .seria(data[6])
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
        pushData(sampleList);

    }
    public void printOutputTable(String target) {
        List<MSample> sampleList = new ArrayList<>();
        String[] data = new String[20];
        try
        {
            for (int i = 0; i < output_table.getRowCount(); i++)
            {
                for (int j = 0; j < output_table.getColumnCount(); j++) {
                    data[j] = String.valueOf(output_table_inner.getValueAt(i, j));
                    if(data[j]=="null") data[j]=null;
                }
                MSample sample = MSample.builder()
                        .date(data[0])
                        .time(data[1])
                        .sampleNo(data[2])
                        .quality(data[3])
                        .bloom(data[4])
                        .codes(data[5])
                        .seria(data[6])
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
            refreshData(sampleList,target);
            print(pullData(target));
        }catch (IllegalArgumentException e)
        {
            JOptionPane.showMessageDialog(null, "Заполните поля корректно");
            print(pullData(target));
        }
    }

    public List<MSample> getTableValue()
    {
        String[] data = new String[20];
        List<MSample> result = new ArrayList<>();
        for(int i = 0; i < output_table.getRowCount(); i++) {
            for (int j = 0; j < output_table.getColumnCount(); j++) {
                data[j] = String.valueOf(output_table_inner.getValueAt(i, j));
                if (data[j] == "null") data[j] = null;
            }

            MSample sample = MSample.builder()
                    .date(data[0])
                    .time(data[1])
                    .sampleNo(data[2])
                    .quality(data[3])
                    .bloom(data[4])
                    .codes(data[5])
                    .seria(data[6])
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
            result.add(sample);
        }
        return result;
    }
    public void delete(String target)
    {
        int selectedRow[] = output_table.getSelectedRows();
        List<MSample> deleteList = new ArrayList<>();
        List<MSample> sampleList = new ArrayList<>(pullData(target));
        String[] data = new String[20];
        for(int i = 0; i < selectedRow.length; i++) {
            for(int j = 0; j < output_table.getColumnCount(); j++)
            {
                data[j] = String.valueOf(output_table_inner.getValueAt(selectedRow[i], j));
                if(data[j]=="null") data[j]=null;
            }

            MSample sample = MSample.builder()
                    .date(data[0])
                    .time(data[1])
                    .sampleNo(data[2])
                    .quality(data[3])
                    .bloom(data[4])
                    .codes(data[5])
                    .seria(data[6])
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
            deleteList.add(sample);
        }

        sampleList.removeAll(deleteList);
        refreshData(sampleList,target);
        for(int i = 0; i < selectedRow.length; i++) {
            output_table_inner.removeRow(selectedRow[selectedRow.length-1-i]);
        }

    }
    public void copySelectedInto(String target)
    {
        int selectedRow[] = output_table.getSelectedRows();
        List<MSample> addList = new ArrayList<>();
        List<MSample> sampleList = new ArrayList<>(pullData(target));
        String[] data = new String[20];
        for(int i = 0; i < selectedRow.length; i++) {
            for(int j = 0; j < output_table.getColumnCount(); j++)
            {
                data[j] = String.valueOf(output_table_inner.getValueAt(selectedRow[i], j));
                if(data[j]=="null") data[j]=null;
            }

            MSample sample = MSample.builder()
                    .date(data[0])
                    .time(data[1])
                    .sampleNo(data[2])
                    .quality(data[3])
                    .bloom(data[4])
                    .codes(data[5])
                    .seria(data[6])
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
           addList.add(sample);
        }

        sampleList.addAll(addList);
        refreshData(sampleList,target);


    }


    public void startAutosearch()
    {
        int selectedRow = output_table.getSelectedRow();
        String[] data = new String[20];
        for(int j = 0; j < output_table.getColumnCount(); j++)
        {
            data[j] = String.valueOf(output_table_inner.getValueAt(selectedRow, j));
            if(data[j]=="null") data[j]=null;
        }
        MSample sample = MSample.builder()
                .date(data[0])
                .time(data[1])
                .sampleNo(data[2])
                .quality(data[3])
                .bloom(data[4])
                .codes(data[5])
                .seria(data[6])
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
        bufferSample=sample;
        print(searcher.autosearch(sample,pullData("MSamples.dat"),config));
    }
    public void startStepsearch(String firstDate, String secondDate)
    {
        print(searcher.stepsearch(bufferSample,pullData("MSamples.dat"),config,firstDate,secondDate));
    }
    public void stopStepsearch()
    {
        searcher.setStepNo(1);
        searcher.setIterationNo(1);
    }
    public void getConfig()
    {
        File f = new File("config.cfg");
        if(!f.exists()){
            try {
                f.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        try (ObjectInputStream fout = new ObjectInputStream(new FileInputStream("config.cfg"))) {
            config = ((Config) fout.readObject());
        } catch (Exception e) {
            config = Config.builder()
                    .dateCheckBox(false)
                    .datePeriodCheckBox(false)
                    .qualityCheckBox(false)
                    .bloomCheckBox(false)
                    .chemsButton(null)
                    .seriaCheckBox(false)
                    /*.CDev(null)
                    .SiDev(null)
                    .MnDev(null)
                    .PDev(null)
                    .SDev(null)
                    .CrDev(null)
                    .NiDev(null)
                    .MoDev(null)
                    .CuDev(null)
                    .AlDev(null)
                    .VDev(null)
                    .WDev(null)
                    .TiDev(null)
                    .CDevAuto(null)
                    .SiDevAuto(null)
                    .MnDevAuto(null)
                    .PDevAuto(null)
                    .SDevAuto(null)
                    .CrDevAuto(null)
                    .NiDevAuto(null)
                    .MoDevAuto(null)
                    .CuDevAuto(null)
                    .AlDevAuto(null)
                    .VDevAuto(null)
                    .WDevAuto(null)
                    .TiDevAuto(null)
                    */
                    .build();
        }
    }
    public void setConfig(Config config)
    {

        try (ObjectOutputStream fout = new ObjectOutputStream(new FileOutputStream("config.cfg"))) {
            fout.writeObject(config);
            fout.flush();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
}
