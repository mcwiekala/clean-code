package io.mcwiekala.cleancode;


import static org.assertj.core.api.Assertions.assertThat;

import io.mcwiekala.cleancode.TextFinder.NumberOrder;
import io.mcwiekala.cleancode.TextFinder.TextOrder;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

@Slf4j
class FindLettersTest {

    @Test
    void checkProcess1() {
        TextFinder textFinder = new TextFinder("aabbcc", "aba", TextOrder.NON_ALPHABETICAL, NumberOrder.ASCENDING);
        List<Character> result1 = textFinder.find();
        assertThat(result1).hasSize(2);
        assertThat(result1.get(0)).isEqualTo('b');
        assertThat(result1.get(1)).isEqualTo('a');
    }

    @Test
    void checkProcess2() {
        TextFinder textFinder = new TextFinder("123aabc", "abcdef", TextOrder.NON_ALPHABETICAL, NumberOrder.ASCENDING);
        List<Character> result = textFinder.find();
        assertThat(result).hasSize(3);
        assertThat(result.get(0)).isEqualTo('c');
        assertThat(result.get(1)).isEqualTo('b');
        assertThat(result.get(2)).isEqualTo('a');
    }

    @Test
    void checkProcess3() {
        TextFinder textFinder = new TextFinder("AaBbbCCc", "a", TextOrder.NON_ALPHABETICAL, NumberOrder.DESCENDING);
        List<Character> result = textFinder.find();
        assertThat(result).hasSize(1);
        assertThat(result.get(0)).isEqualTo('a');
    }

    @Test
    void checkProcess4() {
        TextFinder textFinder = new TextFinder("abcabc123", "ab", TextOrder.ALPHABETICAL, NumberOrder.DESCENDING);
        List<Character> result = textFinder.find();
        assertThat(result).hasSize(2);
        assertThat(result.get(0)).isEqualTo('a');
        assertThat(result.get(1)).isEqualTo('b');
    }

    @Test
    void checkProcess5() {
        TextFinder textFinder = new TextFinder("abc123", "123", TextOrder.ALPHABETICAL, NumberOrder.ASCENDING);
        List<Character> result = textFinder.find();
        assertThat(result).hasSize(3);
        assertThat(result.get(0)).isEqualTo('1');
        assertThat(result.get(1)).isEqualTo('2');
        assertThat(result.get(2)).isEqualTo('3');
    }

    @Test
    void checkProcess6() {
        TextFinder textFinder = new TextFinder("ccddaabbb123", "bcd", TextOrder.ALPHABETICAL, NumberOrder.DESCENDING);
        List<Character> result = textFinder.find();
        assertThat(result).hasSize(3);
        assertThat(result.get(0)).isEqualTo('b');
        assertThat(result.get(1)).isEqualTo('c');
        assertThat(result.get(2)).isEqualTo('d');
    }

    @Test
    void checkProcess7() {
        TextFinder textFinder = new TextFinder("ccddaabbb123", "bcd", TextOrder.NON_ALPHABETICAL, NumberOrder.ASCENDING);
        List<Character> result = textFinder.find();
        assertThat(result).hasSize(3);
        assertThat(result.get(0)).isEqualTo('d');
        assertThat(result.get(1)).isEqualTo('c');
        assertThat(result.get(2)).isEqualTo('b');
    }

}