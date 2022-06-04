package Flare.Modules;

import Flare.MSample;
import Flare.Program;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class ComplexSearch {
    public  List<MSample> searchByName(String name, List<MSample> searchList) {
        List<MSample> result = new ArrayList<>();
        for (int i = 0; i < searchList.size(); ++i) {
            if (searchList.get(i).getSampleNo().contains(name)) result.add(searchList.get(i));
        }
        return result;
    }
    public  List<MSample> searchByDate(String dateFrom,String dateTo, List<MSample> searchList) {
        List<MSample> result = new ArrayList<>();
        String[] splitFromDate =dateFrom.split("\\.");
            if(dateTo!=null) {
                try {
                    String[] splitToDate = dateTo.split("\\.");
                    if(Integer.parseInt(splitToDate[2])<Integer.parseInt(splitFromDate[2]) || Integer.parseInt(splitToDate[1])>12 || Integer.parseInt(splitToDate[1])<1 || Integer.parseInt(splitToDate[0])<1 || Integer.parseInt(splitToDate[0])>31) throw new Exception();
                    for (int i = 0; i < searchList.size(); ++i) {
                        String[] splitDate;
                        splitDate = searchList.get(i).getDate().split("\\.");
                        if (Integer.parseInt(splitDate[2]) >= Integer.parseInt(splitFromDate[2]) && Integer.parseInt(splitDate[2]) <= Integer.parseInt(splitToDate[2]) &&
                                Integer.parseInt(splitDate[1]) >= Integer.parseInt(splitFromDate[1]) && Integer.parseInt(splitDate[1]) <= Integer.parseInt(splitToDate[1]) &&
                                Integer.parseInt(splitDate[0]) >= Integer.parseInt(splitFromDate[0]) && Integer.parseInt(splitDate[0]) <= Integer.parseInt(splitToDate[0])
                        ) result.add(searchList.get(i));
                    }
                }
                catch (Exception e)
                {
                    JOptionPane.showMessageDialog(null,"Интервал или формат даты введен некорректно","Ошибка",JOptionPane.ERROR_MESSAGE);
                }
            }
            else
            {
                try {
                    if (Integer.parseInt(splitFromDate[1])>12 || Integer.parseInt(splitFromDate[1])<1 || Integer.parseInt(splitFromDate[0])<1 || Integer.parseInt(splitFromDate[0])>31)
                    for (int i = 0; i < searchList.size(); ++i)
                        if (dateFrom.compareTo(searchList.get(i).getDate()) == 0) result.add(searchList.get(i));
                }
                catch (Exception e)
                {
                    JOptionPane.showMessageDialog(null,"Формат даты введен некорректно","Ошибка",JOptionPane.ERROR_MESSAGE);
                }
            }

        return result;
    }
    public  List<MSample> searchByQuality(String name, List<MSample> searchList) {
        List<MSample> result = new ArrayList<>();
        boolean errors = false;
        try {
            if(name.length()<1) throw new IllegalArgumentException();
            for (int i = 0; i < searchList.size(); ++i) {
                try {
                    if (searchList.get(i).getQuality().compareTo(name) == 0) result.add(searchList.get(i));
                } catch (Exception e) {
                    errors = true;
                }
            }
            if (errors)
                JOptionPane.showMessageDialog(null, "При поиске возникли некоторые ошибки. Возможно стоит проверить, все ли ячейки заполнены", "Ошибка", JOptionPane.ERROR_MESSAGE);
        }
        catch (IllegalArgumentException e)
        {
            JOptionPane.showMessageDialog(null, "Поле марки стали пустое", "Ошибка", JOptionPane.ERROR_MESSAGE);
        }
        return result;
    }
    public List<MSample> searchByBloom(String name, List<MSample> searchList)
    {
        List<MSample> result = new ArrayList<>();
        for (int i = 0; i < searchList.size(); ++i) {
            try
            {
                if (searchList.get(i).getBloom().compareTo(name) == 0) result.add(searchList.get(i));
            }
            catch (Exception e)
            {
            }
        }
        return result;
    }
    public  List<MSample> searchBySeria(String seriaFrom,String seriaTo, List<MSample> searchList) {
        List<MSample> result = new ArrayList<>();
        boolean errors = false;
        seriaFrom+="*";
        seriaFrom+=seriaTo;
        for (int i = 0; i < searchList.size(); ++i) {
            try {
                if (searchList.get(i).getSeria().compareTo(seriaFrom) == 0) result.add(searchList.get(i));
            }
            catch (Exception e) {}
        }
        return result;
    }
    public  List<MSample> searchByChems(String C,String Si,String Mn,String P,String S,String Cr,String Ni,String Mo,String Cu,String Al,String V,String W,String Ti,Config config, List<MSample> searchList) {
        List<MSample> result = new ArrayList<>();
        boolean errors = false;
        for (int i = 0; i < searchList.size(); ++i) {
           /* try{*/
            if(
                Double.parseDouble(searchList.get(i).getC())>=Double.parseDouble(C)-Double.parseDouble(config.getCDev())&&
                Double.parseDouble(searchList.get(i).getC())<=Double.parseDouble(C)+Double.parseDouble(config.getCDev())&&
                        Double.parseDouble(searchList.get(i).getSi())>=Double.parseDouble(Si)-Double.parseDouble(config.getSiDev())&&
                        Double.parseDouble(searchList.get(i).getSi())<=Double.parseDouble(Si)+Double.parseDouble(config.getSiDev())&&
                        Double.parseDouble(searchList.get(i).getMn())>=Double.parseDouble(Mn)-Double.parseDouble(config.getMnDev())&&
                        Double.parseDouble(searchList.get(i).getMn())<=Double.parseDouble(Mn)+Double.parseDouble(config.getMnDev())&&
                        Double.parseDouble(searchList.get(i).getP())>=Double.parseDouble(P)-Double.parseDouble(config.getPDev())&&
                        Double.parseDouble(searchList.get(i).getP())<=Double.parseDouble(P)+Double.parseDouble(config.getPDev())&&
                        Double.parseDouble(searchList.get(i).getS())>=Double.parseDouble(S)-Double.parseDouble(config.getSDev())&&
                        Double.parseDouble(searchList.get(i).getS())<=Double.parseDouble(S)+Double.parseDouble(config.getSDev())&&
                        Double.parseDouble(searchList.get(i).getCr())>=Double.parseDouble(Cr)-Double.parseDouble(config.getCrDev())&&
                        Double.parseDouble(searchList.get(i).getCr())<=Double.parseDouble(Cr)+Double.parseDouble(config.getCrDev())&&
                        Double.parseDouble(searchList.get(i).getNi())>=Double.parseDouble(Ni)-Double.parseDouble(config.getNiDev())&&
                        Double.parseDouble(searchList.get(i).getNi())<=Double.parseDouble(Ni)+Double.parseDouble(config.getNiDev())&&
                        Double.parseDouble(searchList.get(i).getMo())>=Double.parseDouble(Mo)-Double.parseDouble(config.getMoDev())&&
                        Double.parseDouble(searchList.get(i).getMo())<=Double.parseDouble(Mo)+Double.parseDouble(config.getMoDev())&&
                        Double.parseDouble(searchList.get(i).getCu())>=Double.parseDouble(Cu)-Double.parseDouble(config.getCuDev())&&
                        Double.parseDouble(searchList.get(i).getCu())<=Double.parseDouble(Cu)+Double.parseDouble(config.getCuDev())&&
                        Double.parseDouble(searchList.get(i).getAl())>=Double.parseDouble(Al)-Double.parseDouble(config.getAlDev())&&
                        Double.parseDouble(searchList.get(i).getAl())<=Double.parseDouble(Al)+Double.parseDouble(config.getAlDev())&&
                        Double.parseDouble(searchList.get(i).getV())>=Double.parseDouble(V)-Double.parseDouble(config.getVDev())&&
                        Double.parseDouble(searchList.get(i).getV())<=Double.parseDouble(V)+Double.parseDouble(config.getVDev())&&
                        Double.parseDouble(searchList.get(i).getW())>=Double.parseDouble(W)-Double.parseDouble(config.getWDev())&&
                        Double.parseDouble(searchList.get(i).getW())<=Double.parseDouble(W)+Double.parseDouble(config.getWDev())&&
                        Double.parseDouble(searchList.get(i).getTi())>=Double.parseDouble(Ti)-Double.parseDouble(config.getTiDev())&&
                        Double.parseDouble(searchList.get(i).getTi())<=Double.parseDouble(Ti)+Double.parseDouble(config.getTiDev())
            ) result.add(searchList.get(i));
            /*}catch (Exception e)
            {
            errors=true;
            }*/
        }
        if(errors)JOptionPane.showMessageDialog(null,"При поиске по хим. составу возникли некоторые ошибки. Возможно стоит проверить, все ли ячейки хим. элементов заполнены","Ошибка",JOptionPane.ERROR_MESSAGE);
        return result;
    }
    public List<MSample> autosearch(MSample input,List<MSample> searchList, Config config) {
        List<MSample> result = new ArrayList<>();
        String report = new String();
        List<MSample> bufferList =new ArrayList<>();
        MSample bufferSample = null;
        Config bufferConfig =Config.builder()
                .CDev(config.getCDevAuto())
                .SiDev(config.getSiDevAuto())
                .MnDev(config.getMnDevAuto())
                .PDev(config.getPDevAuto())
                .SDev(config.getSDevAuto())
                .CrDev(config.getCrDevAuto())
                .NiDev(config.getNiDevAuto())
                .MoDev(config.getMoDevAuto())
                .CuDev(config.getCuDevAuto())
                .AlDev(config.getAlDevAuto())
                .VDev(config.getVDevAuto())
                .WDev(config.getWDevAuto())
                .TiDev(config.getTiDevAuto())
                .build();
        boolean errors = false;
        String splitter[]=input.getSampleNo().split("/");
        try {
            result.add(searchByBloom(splitter[0], searchList).get(0));
            report+="При поиске по номеру блюма был найден образец "+result.get(0).getSampleNo()+", \n";
            bufferList.add(input);
            bufferList=searchByChems(result.get(0).getC(),result.get(0).getSi(),result.get(0).getMn(),
                    result.get(0).getP(),result.get(0).getS(),result.get(0).getCr(),result.get(0).getNi(),
                    result.get(0).getMo(),result.get(0).getCu(),result.get(0).getAl(),result.get(0).getV(),
                    result.get(0).getW(),result.get(0).getTi(),bufferConfig,bufferList);
            if(bufferList.size()!=0) {
                report += " и он совпал по хим. составу с разрывным. Поиск завершён.";
                JOptionPane.showMessageDialog(null,report,"Завершено",JOptionPane.INFORMATION_MESSAGE);
                result.add(input);
                return result;
            }
            else {
                report += " \nи он не совпал по хим. составу с разрывным. В данных допущена ошибка";
                result.add(input);
                JOptionPane.showMessageDialog(null,report,"Завершено",JOptionPane.INFORMATION_MESSAGE);
                return result;
                }
            }
        catch (IndexOutOfBoundsException e)
            {
                report+="\nПри поиске по номеру блюма образец не был найден. ";
                for(int i = 0; i<searchList.size();++i)
                {
                    try {
                        if (Integer.parseInt(splitter[0]) > Integer.parseInt(searchList.get(i).getBloom()) && Integer.parseInt(splitter[0]) < Integer.parseInt(searchList.get(i + 1).getBloom()))
                            bufferSample = searchList.get(i);
                    }catch (Exception ex){}
                }
                try {

                    if (Integer.parseInt(bufferSample.getBloom()) + Integer.parseInt(bufferSample.getCodes()) >= Integer.parseInt(splitter[0])) {
                        report +="\nБыл найден образец с номером блюма "+bufferSample.getBloom()+", который, вероятно, является последним записанным в хранилище блюмом перед искомым и включает в себя искомый по кодам. ";
                        result.clear();
                        result.add(bufferSample);
                        bufferList.add(input);
                        bufferList=searchByChems(result.get(0).getC(),result.get(0).getSi(),result.get(0).getMn(),
                                result.get(0).getP(),result.get(0).getS(),result.get(0).getCr(),result.get(0).getNi(),
                                result.get(0).getMo(),result.get(0).getCu(),result.get(0).getAl(),result.get(0).getV(),
                                result.get(0).getW(),result.get(0).getTi(),bufferConfig,bufferList);
                    }
                    else
                    {
                        report +="\nБыл найден образец с номером блюма "+bufferSample.getBloom()+", который, вероятно, является последним записанным в хранилище блюмом перед искомым,\nно он не включает в себя искомый по кодам. Поиск не дал результатов. ";
                        result.clear();
                        JOptionPane.showMessageDialog(null,report,"Завершено",JOptionPane.INFORMATION_MESSAGE);
                        return result;
                    }
                    if (bufferList.size()!=0)
                    {

                        report +="\nЭтот образец совпал по хим. составу с разрывным. Поиск завершён.";
                        JOptionPane.showMessageDialog(null,report,"Завершено",JOptionPane.INFORMATION_MESSAGE);
                        bufferList.add(input);
                        return bufferList;
                    }
                }catch (NullPointerException ex)
                {
                    report +="\nПоследниx записанныx в хранилище блюмов перед искомым не обнаружено. Поиск не дал результатов";
                    result.clear();
                    JOptionPane.showMessageDialog(null,report,"Завершено",JOptionPane.INFORMATION_MESSAGE);
                    return result;
                }
            }
        return result;
    }
}
