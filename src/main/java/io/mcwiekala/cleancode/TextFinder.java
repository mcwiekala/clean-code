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
        List<Character> unfilteredCharactersToCheck = mapStringToCharacterList(textToCheck);
        List<Character> charactersToFind = mapStringToCharacterList(valuesToFind);
        this.charactersToCheck = unfilteredCharactersToCheck.stream()
            .filter(charactersToFind::contains)
            .toList();
    }

    private static List<Character> mapStringToCharacterList(String text) {
        return text.chars()
            .mapToObj(c -> (char) c)
            .toList();
    }

    List<Character> findCharacters() {
        Comparator<? super Entry<Character, Long>> comparatorByNumberAndText = createSortingStrategy();
        return sortCharactersByOrder(comparatorByNumberAndText);
    }

    private List<Character> sortCharactersByOrder(Comparator<? super Entry<Character, Long>> comparator) {
        Map<Character, Long> charactersByNumberOfOccurrence = charactersToCheck.stream()
            .collect(groupingBy(character -> character, Collectors.counting()));
        return charactersByNumberOfOccurrence.entrySet().stream()
            .sorted(comparator)
            .map(Entry::getKey)
            .toList();
    }

    private Comparator<? super Entry<Character, Long>> createSortingStrategy() {
        Comparator<Entry<Character, Long>> compareByNumber = Comparator.comparingLong(Entry::getValue);
        Comparator<Entry<Character, Long>> compareByString = Comparator.comparing(Entry::getKey);
        compareByNumber = numberOrder == NumberOrder.ASCENDING ? compareByNumber : compareByNumber.reversed();
        compareByString = textOrder == TextOrder.ALPHABETICAL ? compareByString : compareByString.reversed();
        return compareByNumber.thenComparing(compareByString);
    }

    enum TextOrder {
        ALPHABETICAL, REVERSE_ALPHABETICAL
    }

    enum NumberOrder {
        ASCENDING, DESCENDING
    }

}
