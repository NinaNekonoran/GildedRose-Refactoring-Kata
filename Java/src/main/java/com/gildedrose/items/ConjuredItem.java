package com.gildedrose.items;

public class ConjuredItem extends NormalItem{

    public static final String KEYWORD = "Conjured";

    public ConjuredItem(String name, int sellIn, int quality) {
        super(name, sellIn, quality);
    }

    @Override
    public void updateQualityWithNormalProcess() {
        if ( this.quality > 0) {
            this.quality -=  2;
        }
    }

    @Override
    public void updateQualityWithExpiredProcess() {
        if (this.sellIn < 0 && this.quality > 0 ) {
            this.quality -= 2;
        }
    }
}
