package com.gildedrose.items;

import com.gildedrose.enums.SpecialItemEnum;
import com.gildedrose.items.SpecialItem;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

class SpecialItemTest {
    @ParameterizedTest
    @CsvSource({
        "AGED_BRIE,0,50,50,Quality max use case",
        "AGED_BRIE,0,5,6,AGED_BRIE use case",
        "SULFURAS_HAND_OF_RAGNAROS,0,80,80,Sulfuras use case",
        "BACKSTAGE_PASSES_TO_A_TAFKAL_80_ETC_CONCERT,12,5,6,TAFKAL80ETC use case with 12 sellIn",
        "BACKSTAGE_PASSES_TO_A_TAFKAL_80_ETC_CONCERT,8,5,7,TAFKAL80ETC use case with 8 sellIn",
        "BACKSTAGE_PASSES_TO_A_TAFKAL_80_ETC_CONCERT,4,5,8,TAFKAL80ETC use case with 4 sellIn"
    })
    void updateQualityWithNormalProcess(final String name, final int sellIn, final int quality, final int qualityExpected, final String testMessage) {
        // Given
        String finalName = SpecialItemEnum.getItemNameByKey(name).get();
        SpecialItem item = new SpecialItem(finalName, sellIn, quality);

        // When
        item.updateQualityWithNormalProcess();

        // Then
        assertEquals(qualityExpected, item.quality, testMessage);
    }

    @ParameterizedTest
    @CsvSource({
        "AGED_BRIE,6,0,5,not Sulfuras 1",
        "SULFURAS_HAND_OF_RAGNAROS,7,0,7,Sulfuras use case"
    })
    void updateSellIn(final String name, final int sellIn, final int quality, final int sellInExpected, final String testMessage) {
        // Given
        String finalName = SpecialItemEnum.getItemNameByKey(name).get();
        SpecialItem item = new SpecialItem(finalName, sellIn, quality);

        // When
        item.updateSellIn();

        // Then
        assertEquals(sellInExpected, item.sellIn, testMessage);
    }

    @ParameterizedTest
    @CsvSource({
        "AGED_BRIE,-8,50,50,Quality max use case with sellIn<0",
        "AGED_BRIE,5,50,50,Quality max use case with sellIn>0",
        "AGED_BRIE,-1,5,6,AGED_BRIE use case with sellIn<0",
        "AGED_BRIE,6,5,5,AGED_BRIE use case with sellIn>0",
        "SULFURAS_HAND_OF_RAGNAROS,-1,80,80,Sulfuras use case with sellIn<0",
        "SULFURAS_HAND_OF_RAGNAROS,8,80,80,Sulfuras use case with sellIn>0",
        "BACKSTAGE_PASSES_TO_A_TAFKAL_80_ETC_CONCERT,-5,6,0,TAFKAL80ETC with sellIn<0",
        "BACKSTAGE_PASSES_TO_A_TAFKAL_80_ETC_CONCERT,9,8,8,TAFKAL80ETC with sellIn>0"
    })
    void updateQualityWithExpiredProcess(final String name, final int sellIn, final int quality, final int sellInExpected, final String testMessage) {
        // Given
        String finalName = SpecialItemEnum.getItemNameByKey(name).get();
        SpecialItem item = new SpecialItem(finalName, sellIn, quality);

        // When
        item.updateQualityWithExpiredProcess();

        // Then
        assertEquals(sellInExpected, item.quality, testMessage);
    }

}
