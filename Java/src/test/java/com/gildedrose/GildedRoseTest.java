package com.gildedrose;

import com.gildedrose.enums.SpecialtemEnum;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GildedRoseTest {

    @Test
    void foo() {
        Item[] items = new Item[] { new Item("foo", 0, 0) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals("fixme", app.items[0].name);
    }

    @ParameterizedTest
    @CsvSource({
        "AGED_BRIE,0,50,50,Quality max use case",
        "AGED_BRIE,0,5,6,AGED_BRIE use case",
        "SULFURAS_HAND_OF_RAGNAROS,0,5,6,Sulfuras use case",
        "BACKSTAGE_PASSES_TO_A_TAFKAL_80_ETC_CONCERT,12,5,6,TAFKAL80ETC use case with 12 sellIn",
        "BACKSTAGE_PASSES_TO_A_TAFKAL_80_ETC_CONCERT,8,5,7,TAFKAL80ETC use case with 8 sellIn",
        "BACKSTAGE_PASSES_TO_A_TAFKAL_80_ETC_CONCERT,4,5,8,TAFKAL80ETC use case with 4 sellIn",
        "pop,0,5,4, Normal object use case",
        "pop,0,0,0, Normal object with quality min use case"
    })
    void updateQualityWithNormalProcess(final String name, final int sellIn, final int quality, final int qualityExpected, final String testMessage)
        throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        // Given
        Method method = GildedRose.class.getDeclaredMethod("updateQualityWithNormalProcess", Item.class);
        method.setAccessible(true);

        Optional<String> finalName = SpecialtemEnum.getItemNameByKey(name);
        Item item = new Item(finalName.orElse(name), sellIn, quality);
        GildedRose gildedRose = new GildedRose(new Item[] { item });

        // When
        int qualityResult = (int) method.invoke(gildedRose, item);

        // Then
        assertEquals(qualityExpected, qualityResult, testMessage);
    }

    @ParameterizedTest
    @CsvSource({
        "AGED_BRIE,6,0,5,not Sulfuras 1",
        "pop,4,0,3,not Sulfuras 1",
        "SULFURAS_HAND_OF_RAGNAROS,7,0,7,Sulfuras use case"
    })
    void updateSellIn(final String name, final int sellIn, final int quality, final int sellInExpected, final String testMessage) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        // Given
        Method method = GildedRose.class.getDeclaredMethod("updateSellIn", Item.class);
        method.setAccessible(true);

        Optional<String> finalName = SpecialtemEnum.getItemNameByKey(name);
        Item item = new Item(finalName.orElse(name), sellIn, quality);
        GildedRose gildedRose = new GildedRose(new Item[] { item });

        // When
        int sellInResult = (int) method.invoke(gildedRose, item);

        // Then
        assertEquals(sellInExpected, sellInResult, testMessage);
    }

    @ParameterizedTest
    @CsvSource({
        "AGED_BRIE,-8,50,50,Quality max use case with sellIn<0",
        "AGED_BRIE,5,50,50,Quality max use case with sellIn>0",
        "AGED_BRIE,-1,5,6,AGED_BRIE use case with sellIn<0",
        "AGED_BRIE,6,5,5,AGED_BRIE use case with sellIn>0",
        "SULFURAS_HAND_OF_RAGNAROS,-1,5,0,Sulfuras use case with sellIn<0",
        "SULFURAS_HAND_OF_RAGNAROS,8,5,5,Sulfuras use case with sellIn>0",
        "BACKSTAGE_PASSES_TO_A_TAFKAL_80_ETC_CONCERT,-5,6,0,TAFKAL80ETC with sellIn<0",
        "BACKSTAGE_PASSES_TO_A_TAFKAL_80_ETC_CONCERT,9,8,8,TAFKAL80ETC with sellIn>0",
        "pop,-9,5,4, Normal object use case with sellIn<0",
        "pop,7,5,5, Normal object use case with sellIn>0"
    })
    void updateQualityWithExpiredProcess(final String name, final int sellIn, final int quality, final int sellInExpected, final String testMessage) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        // Given
        Method method = GildedRose.class.getDeclaredMethod("updateQualityWithExpiredProcess", Item.class);
        method.setAccessible(true);

        Optional<String> finalName = SpecialtemEnum.getItemNameByKey(name);
        Item item = new Item(finalName.orElse(name), sellIn, quality);
        GildedRose gildedRose = new GildedRose(new Item[] { item });

        // When
        int qualityResult = (int) method.invoke(gildedRose, item);

        // Then
        assertEquals(sellInExpected, qualityResult, testMessage);
    }

}
