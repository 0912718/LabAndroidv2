package ictlab.app1.Booking;

/**
 * Created by edgar on 23-3-2018.
 */

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ExpandableListDataPump {
    public static HashMap<String, List<String>> getData() {
        HashMap<String, List<String>> expandableListDetail = new HashMap<>();

        List<String> Wijnhaven = new ArrayList<>();
        Wijnhaven.add("1st floor");
        Wijnhaven.add("2nd floor");
        Wijnhaven.add("3rd floor");
        Wijnhaven.add("4th floor");
        Wijnhaven.add("5th floor");

        List<String> Museumpark = new ArrayList<>();
        Museumpark.add("1st floor");
        Museumpark.add("2nd floor");
        Museumpark.add("3rd floor");

        List<String> Blaak = new ArrayList<>();
        Blaak.add("1st floor");
        Blaak.add("2nd floor");
        Blaak.add("3rd floor");
        Blaak.add("4th floor");


        expandableListDetail.put("HR Location Wijnhaven", Wijnhaven);
        expandableListDetail.put("HR Location Museumpark", Museumpark);
        expandableListDetail.put("HR Location Blaak", Blaak);
        return expandableListDetail;
    }
}