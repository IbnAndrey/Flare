package Flare;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import Flare.Modules.ImportFromExcel;

import static Flare.Modules.ComplexSearch.searchByName;

public class Controller {
    private final String[] header = new String[]{"Дата","Время","Номер образца", "Марка стали", "Блюм", "Кол-во блюмов","Серия", "C","Si","Mn","P","S","Cr","Ni","Mo","Cu","Al","As","V","W","Ti"};
    private JTable output_table;
    private DefaultTableModel output_table_inner = new DefaultTableModel(header, 0);
    private List<MSample> tableList = new ArrayList<>();
    public boolean saveBlocker = false;
    public JTable createOutputTable() {
        print(pullData());
        output_table = new JTable(output_table_inner);
        output_table.putClientProperty("terminateEditOnFocusLost", true);
        return output_table;
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
            String As = sample.getAs();
            String V = sample.getV();
            String W = sample.getW();
            String Ti = sample.getTi();
            Object[] rowData = {date,time,sample_No,quality,bloom,bloomCount,seria,C,Si,Mn,P,S,Cr,Ni,Mo,Cu,Al,As,V,W,Ti};
            output_table_inner.addRow(rowData);
        });
    }
    public List<MSample> pullData() {
        File f = new File("db.dat");
        if(!f.exists()){
            try {
                f.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        List<MSample> sampleList = new ArrayList<>();
        try (ObjectInputStream fout = new ObjectInputStream(new FileInputStream("db.dat"))) {
            sampleList = ((ArrayList<MSample>) fout.readObject());
        } catch (Exception e) {

        }
        return sampleList;
    }

    public void pushRow(List<MSample> collectedData) throws IOException {
        List<MSample> sampleList = new ArrayList<>();

        sampleList.addAll(pullData());
        sampleList.addAll(collectedData);
        refreshData(sampleList);
        print(pullData());
    }

    public void refreshData(List<MSample> sample) {
        try (ObjectOutputStream fout = new ObjectOutputStream(new FileOutputStream("db.dat"))) {
            fout.writeObject(sample);
            fout.flush();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
    public void printOutputTable() {
        List<MSample> sampleList = new ArrayList<>();
        String[] data = new String[21];
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
                        .As(data[17])
                        .V(data[18])
                        .W(data[19])
                        .Ti(data[20])
                        .build();
                sampleList.add(sample);
            }
            refreshData(sampleList);
            print(pullData());
        }catch (IllegalArgumentException e)
        {
            JOptionPane.showMessageDialog(null, "Заповніть поля коректно");
            print(pullData());
        }
    }
    public void search(String name)
    {
        print(searchByName(name,pullData()));
    }
    public void delete()
    {
        int selectedRow[] = output_table.getSelectedRows();
        List<MSample> deleteList = new ArrayList<>();
        List<MSample> sampleList = new ArrayList<>(pullData());
        String[] data = new String[21];
        for(int i = 0; i < selectedRow.length; i++) {
            for(int j = 0; j < output_table.getColumnCount(); j++)
            {
                data[j] = String.valueOf(output_table_inner.getValueAt(selectedRow[i], j));
                if(data[j]=="null") data[j]=null;
            }
            output_table_inner.removeRow(selectedRow[i]);
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
                    .As(data[17])
                    .V(data[18])
                    .W(data[19])
                    .Ti(data[20])
                    .build();
            deleteList.add(sample);
        }
        sampleList.removeAll(deleteList);
        refreshData(sampleList);

    }
}
