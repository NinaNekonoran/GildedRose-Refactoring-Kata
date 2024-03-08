package com.gildedrose.enums;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Optional;

@Getter
@AllArgsConstructor
public enum SpecialItemEnum {
    AGED_BRIE("Aged Brie"),
    BACKSTAGE_PASSES_TO_A_TAFKAL_80_ETC_CONCERT("Backstage passes to a TAFKAL80ETC concert"),
    SULFURAS_HAND_OF_RAGNAROS("Sulfuras, Hand of Ragnaros");

    String itemName;

    public static boolean contains(final String test) {
        for (SpecialItemEnum item : SpecialItemEnum.values()) {
            if (item.itemName.equals(test)) {
                return true;
            }
        }
        return false;
    }
    public static Optional<String> getItemNameByKey(String test) {
        for (SpecialItemEnum item : SpecialItemEnum.values()) {
            if (item.name().equals(test)) {
                return Optional.of(item.getItemName());
            }
        }
        return Optional.empty();
    }
}
