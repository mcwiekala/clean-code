package io.mcwiekala.cleancode;

import static java.util.stream.Collectors.groupingBy;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

class TextFinder {

    private final TextOrder textOrder;
    private final NumberOrder numberOrder;
    private final List<Character> charactersToCheck;

    TextFinder(String textToCheck, String valuesToFind, TextOrder textOrder, NumberOrder numberOrder) {
        this.textOrder = textOrder;
        this.numberOrder = numberOrder;
        List<Character> charactersToCheck = mapStringToCharacterList(textToCheck);
        List<Character> charactersToFind = mapStringToCharacterList(valuesToFind);
        List<Character> filteredCharactersToCheck = charactersToCheck.stream()
            .filter(charactersToFind::contains)
            .toList();
        this.charactersToCheck = filteredCharactersToCheck;
    }

    private static List<Character> mapStringToCharacterList(String text) {
        return text.chars()
            .mapToObj(c -> (char) c)
            .toList();
    }

    public List<Character> find() {
        Map<Character, Long> charactersByNumberOfOccurrence = charactersToCheck.stream()
            .collect(groupingBy(character -> character, Collectors.counting()));

        Comparator<? super Entry<Character, Long>> comparator = createSortingStrategy();

        return charactersByNumberOfOccurrence.entrySet().stream()
            .sorted(comparator)
            .map(Entry::getKey)
            .toList();
    }

    private Comparator createSortingStrategy() {
        Comparator compareByNumber = Comparator.comparingLong(Entry<String, Long>::getValue);
        Comparator compareByString = Comparator.comparing(Entry<String, Long>::getKey);
        compareByNumber = numberOrder == NumberOrder.ASCENDING ? compareByNumber : compareByNumber.reversed();
        compareByString = textOrder == TextOrder.ALPHABETICAL ? compareByString : compareByString.reversed();
        return compareByNumber.thenComparing(compareByString);
    }

    enum TextOrder {
        ALPHABETICAL, NON_ALPHABETICAL
    }

    enum NumberOrder {
        ASCENDING, DESCENDING
    }

}
