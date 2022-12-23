package ru.stazaev.agency;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Test {
    public static void main(String[] args) {
        Tt tt = new Tt("1","2");
        Tt tt1 = new Tt("1","2");
        System.out.println(tt.equals(tt1));
        Map<Tt, List<String>> map = new HashMap<>();

        List<String> list = new ArrayList<>();
        list.add("s");
        map.put(tt,list);
        System.out.println(map.get(tt1).add("ewq"));
        System.out.println(map.get(tt));
    }
}
