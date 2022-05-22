package Flare.Modules;

import Flare.Controller;
import Flare.MSample;

import java.util.ArrayList;
import java.util.List;

public class ComplexSearch {
    public static List<MSample> searchByName(String name,List<MSample> searchList)
    {
        List<MSample> result = new ArrayList<>();
        for(int i = 0; i<searchList.size();++i)
        {
            if(searchList.get(i).getSampleNo().contains(name)) result.add(searchList.get(i));
        }
        return result;
    }
}
