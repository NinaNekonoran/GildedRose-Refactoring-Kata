package com.gildedrose.items;

import com.gildedrose.enums.SpecialItemEnum;

public class SpecialItem extends Item implements ItemBehaviour {
    public static final int MAX_QUALITY = 50;

    public SpecialItem(String name, int sellIn, int quality) {
        super(name, sellIn, quality);
    }

    @Override
    public void updateSellIn() {
        if (!SpecialItemEnum.SULFURAS_HAND_OF_RAGNAROS.getItemName().equals(this.name)) {
            sellIn -= 1;
        }
    }

    @Override
    public void updateQualityWithNormalProcess() {
        //"Sulfuras" is a legendary item and as such its `Quality` is `80` and it never alters.
        if (!SpecialItemEnum.SULFURAS_HAND_OF_RAGNAROS.getItemName().equals(this.name)
                && this.quality < MAX_QUALITY){
            quality += 1;
            if (SpecialItemEnum.BACKSTAGE_PASSES_TO_A_TAFKAL_80_ETC_CONCERT.getItemName().equals(this.name)) {
                if (this.sellIn < 11 && this.quality < MAX_QUALITY) {
                    quality += 1;
                }
                if (this.sellIn < 6 && this.quality < MAX_QUALITY) {
                    quality += 1;
                }
            }
        }
    }

    @Override
    public void updateQualityWithExpiredProcess() {
        //"Sulfuras" is a legendary item and as such its `Quality` is `80` and it never alters.
        if (!SpecialItemEnum.SULFURAS_HAND_OF_RAGNAROS.getItemName().equals(this.name)
            && this.sellIn < 0) {
            if (this.name.equals(SpecialItemEnum.AGED_BRIE.getItemName())) {
                if (this.quality < MAX_QUALITY) {
                    quality += 1;
                }
            } else {
                quality = 0;
            }
        }
    }
}
