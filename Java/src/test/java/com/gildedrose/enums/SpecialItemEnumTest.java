package com.gildedrose.enums;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

class SpecialItemEnumTest {

    @ParameterizedTest
    @CsvSource({
        "Aged Brie,true",
        "Backstage passes to a TAFKAL80ETC concert,true",
        "Sulfuras, Hand of Ragnaros,true",
        "pop,false"
    })
    void contains(final String test, final boolean expected) {
        assertEquals(expected, SpecialItemEnum.contains(test));
    }


}
