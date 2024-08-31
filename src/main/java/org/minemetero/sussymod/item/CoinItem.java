package org.minemetero.sussymod.item;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroupEntries;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.ItemGroups;
import org.minemetero.sussymod.SussyMod;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class CoinItem {
    public static final Item COIN = registerItems("coin",new Item(new FabricItemSettings()));

    private static void addItemsToIG(FabricItemGroupEntries fabricItemGroupEntries){
        fabricItemGroupEntries.add(COIN);
    }
    private static Item registerItems(String name,Item item){
        return Registry.register(Registries.ITEM,new Identifier(SussyMod.MOD_ID,name),item);
    }
    public static void registerCoinItem(){
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.FOOD_AND_DRINK).register(CoinItem::addItemsToIG);
    }
}
