package io.mcwiekala.cleancode;

import static java.util.stream.Collectors.groupingBy;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map.Entry;
import java.util.stream.Collectors;

public class TextFunctionalProcessor {

    public List<Character> process(String x1, String from, Boolean numbers, Boolean letters) {
        return Arrays.stream(x1.split(""))
            .collect(groupingBy(c -> c, Collectors.counting())).entrySet().stream()
            .filter(e -> Arrays.stream(from.split("")).anyMatch(s -> s.equals(e.getKey())))
            .sorted((e1, e2) -> {
                var entryComparator1 = Comparator.comparingLong(Entry<String, Long>::getValue);
                entryComparator1 = numbers ? entryComparator1.reversed() : entryComparator1;
                var entryComparator2 = Comparator.comparing(Entry<String, Long>::getKey);
                entryComparator2 = letters ? entryComparator2.reversed() : entryComparator2;
                var entryComparator3 = entryComparator1.thenComparing(entryComparator2);
                return entryComparator3.compare(e1, e2);
            })
            .map(e -> e.getKey().charAt(0))
            .toList();
    }
}
