package Flare;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import Flare.Modules.ImportFromExcel;

public class Controller {
    private final String[] header = new String[]{"Номер образца", "Марка стали", "Блюм","Код 1","Код 2","Код 3","Код 4", "Кол-во блюмов", "C","Si","Mn","P","S","Cr","Ni","Mo","Cu","Al","V","W","Ti"};
    private JTable output_table;
    private DefaultTableModel output_table_inner = new DefaultTableModel(header, 0);
    private List<MSample> tableList = new ArrayList<>();
    public JTable createOutputTable() {
        print();
        output_table = new JTable(output_table_inner);
        output_table.putClientProperty("terminateEditOnFocusLost", true);
        return output_table;
    }

    public void print() {
        output_table_inner.setRowCount(0);
        tableList = pullData();
        tableList.forEach(sample ->
        {
            String sample_No = sample.getSampleNo();
            String quality = sample.getQuality();
            String bloom = sample.getBloom();
            String codes[] = new String[]{sample.getCod_1(),sample.getCod_2(),sample.getCod_3(),sample.getCod_4()};
            int bloomCount = 0;
            String[] bloomDivide;
            for(int i = 0; i<4;++i) {
                try {
                    bloomDivide = codes[i].split("\\*");
                    bloomCount += Integer.parseInt(bloomDivide[0]);
                } catch (Exception e) {
                }
            }
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
            Object[] rowData = {sample_No,quality,bloom,codes[0],codes[1],codes[2],codes[3],bloomCount,C,Si,Mn,P,S,Cr,Ni,Mo,Cu,Al,V,W,Ti};
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
        print();
    }

    public void refreshData(List<MSample> sample) {
        try (ObjectOutputStream fout = new ObjectOutputStream(new FileOutputStream("db.dat"))) {
            fout.writeObject(sample);
            fout.flush();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
}
