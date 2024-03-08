package com.gildedrose.items;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ConjuredItemTest {
    @ParameterizedTest
    @CsvSource({
        "Conjured pop,0,5,3, Normal object use case",
        "Conjured pop,0,0,0, Normal object with quality min use case"
    })
    void updateQualityWithNormalProcess(final String name, final int sellIn, final int quality, final int qualityExpected, final String testMessage) {
        // Given
        ConjuredItem item = new ConjuredItem(name, sellIn, quality);

        // When
        item.updateQualityWithNormalProcess();

        // Then
        assertEquals(qualityExpected, item.quality, testMessage);
    }

    @ParameterizedTest
    @CsvSource({
        "Conjured pop,4,0,3,not Sulfuras 1"
    })
    void updateSellIn(final String name, final int sellIn, final int quality, final int sellInExpected, final String testMessage) {
        // Given
        ConjuredItem item = new ConjuredItem(name, sellIn, quality);

        // When
        item.updateSellIn();

        // Then
        assertEquals(sellInExpected, item.sellIn, testMessage);
    }

    @ParameterizedTest
    @CsvSource({
        "Conjured pop,-9,5,3, Normal object use case with sellIn<0",
        "Conjured pop,7,5,5, Normal object use case with sellIn>0"
    })
    void updateQualityWithExpiredProcess(final String name, final int sellIn, final int quality, final int sellInExpected, final String testMessage) {
        // Given
        ConjuredItem item = new ConjuredItem(name, sellIn, quality);

        // When
        item.updateQualityWithExpiredProcess();

        // Then
        assertEquals(sellInExpected, item.quality, testMessage);
    }

}
