package com.gildedrose;

import com.gildedrose.enums.SpecialItemEnum;
import com.gildedrose.items.Item;
import com.gildedrose.items.ItemBehaviour;
import com.gildedrose.items.NormalItem;
import com.gildedrose.items.SpecialItem;
import com.gildedrose.items.ConjuredItem;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
class GildedRose {

    List<ItemBehaviour> items;

    public GildedRose(Item[] items) {
        this.items = new ArrayList<>(items.length);
        for(int i = 0; i < items.length; i++){
            Item newItem;
            if(SpecialItemEnum.contains(items[i].name)){
                newItem = new SpecialItem(items[i].name, items[i].sellIn, items[i].quality);
            } else if(items[i].name.toLowerCase().contains(ConjuredItem.KEYWORD.toLowerCase())){
                newItem = new ConjuredItem(items[i].name, items[i].sellIn, items[i].quality);
            }
            else {
                newItem = new NormalItem(items[i].name, items[i].sellIn, items[i].quality);
            }
            items[i] = newItem;
            this.items.add((ItemBehaviour) newItem);
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
