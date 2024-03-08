package com.gildedrose.items;

public class NormalItem extends Item implements ItemBehaviour{
    public NormalItem(final String name, final int sellIn, final int quality) {
        super(name, sellIn, quality);
    }

    public NormalItem(final Item item) {
        super(item.name, item.sellIn, item.quality);
    }
    @Override
    public void updateSellIn() {
        this.sellIn -= 1;
    }

    @Override
    public void updateQualityWithNormalProcess() {
        if ( this.quality > 0) {
            this.quality -=  1;
        }
    }

    @Override
    public void updateQualityWithExpiredProcess() {
        if (this.sellIn < 0 && this.quality > 0 ) {
                    this.quality -= 1;
        }
    }
}
