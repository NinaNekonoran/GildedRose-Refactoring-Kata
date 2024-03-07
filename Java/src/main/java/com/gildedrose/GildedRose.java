package com.gildedrose;

import com.gildedrose.enums.SpecialtemEnum;

class GildedRose {

    public static final int MAX_QUALITY = 50;

    Item[] items;

    public GildedRose(Item[] items) {
        this.items = items;
    }

    public void updateQuality() {
        for (Item item : items) {
            item.quality = updateQualityWithNormalProcess(item);
            item.sellIn = updateSellIn(item);
            item.quality = updateQualityWithExpiredProcess(item);
        }
    }

    private int updateQualityWithNormalProcess(final Item item) {
        int quality = item.quality;
        if (SpecialtemEnum.contains(item.name)) {
            if(item.quality < MAX_QUALITY) {
                quality += 1;
                if (SpecialtemEnum.BACKSTAGE_PASSES_TO_A_TAFKAL_80_ETC_CONCERT.getItemName().equals(item.name)){
                    if (item.sellIn < 11 && item.quality < MAX_QUALITY) {
                        quality += 1;
                    }
                    if (item.sellIn < 6 && item.quality < MAX_QUALITY) {
                        quality += 1;
                    }
                }

            }
        } else if ( item.quality > 0) {
            quality -=  1;
        }
        return quality;
    }

    private int updateSellIn(final Item item) {
        int sellIn = item.sellIn;
        if (!SpecialtemEnum.SULFURAS_HAND_OF_RAGNAROS.getItemName().equals(item.name)) {
            sellIn -= 1;
        }
        return sellIn;
    }

    private int updateQualityWithExpiredProcess(final Item item) {
        int quality = item.quality;
        if (item.sellIn < 0) {
            if (item.name.equals(SpecialtemEnum.AGED_BRIE.getItemName()))  {
                if (item.quality < MAX_QUALITY) {
                    quality += 1;
                }
            } else {
                if (!SpecialtemEnum.contains(item.name) && item.quality > 0 ) {
                    quality = item.quality - 1;
                } else {
                    quality = 0;
                }
            }
        }
        return quality;
    }
}
