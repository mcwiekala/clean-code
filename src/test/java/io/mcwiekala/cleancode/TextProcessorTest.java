package io.mcwiekala.cleancode;


import static org.assertj.core.api.Assertions.assertThat;

import io.mcwiekala.cleancode.TextFinder.NumberOrder;
import io.mcwiekala.cleancode.TextFinder.TextOrder;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

@Slf4j
class TextProcessorTest {

    TextProcessor processor = new TextProcessor();

    @Test
    void checkProcess1() {
        List<Character> result1 = processor.process("1abcd", "ab", true, false);
        assertThat(result1).hasSize(2);
        assertThat(result1.get(0)).isEqualTo('a');
        assertThat(result1.get(1)).isEqualTo('b');
    }

    @Test
    void checkProcess2() {
        List<Character> result2 = processor.process("1abbbc", "abcd", true, false);
        assertThat(result2).hasSize(3);
        assertThat(result2.get(0)).isEqualTo('b');
        assertThat(result2.get(1)).isEqualTo('a');
        assertThat(result2.get(2)).isEqualTo('c');
    }

    @Test
    void checkProcess3() {
        List<Character> result3 = processor.process("!@abcd", "abcd123", true, false);
        assertThat(result3).hasSize(4);
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
        List<Character> result5 = processor.process("abc123", "abc", false, true);
        assertThat(result5).hasSize(3);
        assertThat(result5.get(0)).isEqualTo('c');
        assertThat(result5.get(1)).isEqualTo('b');
        assertThat(result5.get(2)).isEqualTo('a');
    }

    @Test
    void checkProcess6() {
        List<Character> result5 = processor.process("ccddaabbb123", "bcd", false, true);
        assertThat(result5).hasSize(3);
        assertThat(result5.get(0)).isEqualTo('d');
        assertThat(result5.get(1)).isEqualTo('c');
        assertThat(result5.get(2)).isEqualTo('b');
    }

    @Test
    void checkProcess7() {
        List<Character> result5 = processor.process("ccddaabbb123", "bcd", true, false);
        assertThat(result5).hasSize(3);
        assertThat(result5.get(0)).isEqualTo('b');
        assertThat(result5.get(1)).isEqualTo('c');
        assertThat(result5.get(2)).isEqualTo('d');
    }

}