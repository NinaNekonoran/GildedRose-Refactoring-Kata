package com.gildedrose.items;

import com.gildedrose.items.NormalItem;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

class NormalItemTest {
    @ParameterizedTest
    @CsvSource({
        "pop,0,5,4, Normal object use case",
        "pop,0,0,0, Normal object with quality min use case"
    })
    void updateQualityWithNormalProcess(final String name, final int sellIn, final int quality, final int qualityExpected, final String testMessage) {
        // Given
        NormalItem item = new NormalItem(name, sellIn, quality);

        // When
        item.updateQualityWithNormalProcess();

        // Then
        assertEquals(qualityExpected, item.quality, testMessage);
    }

    @ParameterizedTest
    @CsvSource({
        "pop,4,0,3,not Sulfuras 1"
    })
    void updateSellIn(final String name, final int sellIn, final int quality, final int sellInExpected, final String testMessage) {
        // Given
        NormalItem item = new NormalItem(name, sellIn, quality);

        // When
        item.updateSellIn();

        // Then
        assertEquals(sellInExpected, item.sellIn, testMessage);
    }

    @ParameterizedTest
    @CsvSource({
        "pop,-9,5,4, Normal object use case with sellIn<0",
        "pop,7,5,5, Normal object use case with sellIn>0"
    })
    void updateQualityWithExpiredProcess(final String name, final int sellIn, final int quality, final int sellInExpected, final String testMessage) {
        // Given
        NormalItem item = new NormalItem(name, sellIn, quality);

        // When
        item.updateQualityWithExpiredProcess();

        // Then
        assertEquals(sellInExpected, item.quality, testMessage);
    }

}
