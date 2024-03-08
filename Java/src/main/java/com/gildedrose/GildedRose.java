package com.gildedrose;

import com.gildedrose.enums.SpecialItemEnum;
import com.gildedrose.items.Item;
import com.gildedrose.items.ItemBehaviour;
import com.gildedrose.items.NormalItem;
import com.gildedrose.items.SpecialItem;

import java.util.ArrayList;
import java.util.List;

class GildedRose {

    List<ItemBehaviour> items;

    public GildedRose(Item[] items) {
        this.items = new ArrayList<>(items.length);
        for(Item item: items){
            if(SpecialItemEnum.contains(item.name)){
                this.items.add(new SpecialItem(item.name, item.sellIn, item.quality));
            } else {
                this.items.add(new NormalItem(item.name, item.sellIn, item.quality));
            }
        }
    }

    public void updateQuality() {
        for (ItemBehaviour item : items) {
            item.updateQualityWithNormalProcess();
            item.updateSellIn();
            item.updateQualityWithExpiredProcess();
        }
    }

}
