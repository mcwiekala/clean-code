package io.mcwiekala.cleancode;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class TextProcessor {

    public List<Character> process(String x1, String from, Boolean numbers, Boolean letters) {
        Map<Character, Integer> map = new HashMap<>();
        char[] chars = from.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            map.put(chars[i], 0);
        }
        for (int i = 0; i < x1.length(); i++) {
            if (map.containsKey(x1.charAt(i))) {
                Integer i2 = map.get(x1.charAt(i));
                map.put(x1.charAt(i), ++i2);
            }
        }
        List<Entry<Character, Integer>> list = new ArrayList<>(map.entrySet());
        Comparator<Entry<Character, Integer>> comparator1 = (e1, e2) -> e1.getValue().compareTo(e2.getValue());
        Comparator<Entry<Character, Integer>> comparator2 = (e1, e2) -> e1.getKey().compareTo(e2.getKey());

        if (numbers) {
            comparator1 = comparator1.reversed();
        }
        if (letters) {
            comparator2 = comparator2.reversed();
        }

        list.sort(comparator1.thenComparing(comparator2));
        List<Character> list2 = new ArrayList<>();
        for (Entry<Character, Integer> e : list) {
            if (e.getValue() > 0) {
                list2.add(e.getKey());
            }
        }
        return list2;
    }
}
