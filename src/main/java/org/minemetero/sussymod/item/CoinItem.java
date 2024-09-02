package org.minemetero.sussymod.item;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroupEntries;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.FoodComponent;
import net.minecraft.item.ItemGroups;
import org.minemetero.sussymod.SussyMod;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

//CoinItem的属性
class CoinFoodComponents{
    public static final FoodComponent COIN = new FoodComponent.Builder().hunger(3).saturationModifier(0.3f)
            .statusEffect(new StatusEffectInstance(StatusEffects.REGENERATION,100,1),1.0f)
            .statusEffect(new StatusEffectInstance(StatusEffects.INSTANT_DAMAGE,600,0),1.0f)
            .statusEffect(new StatusEffectInstance(StatusEffects.FIRE_RESISTANCE,600,0),1.0f)
            .statusEffect(new StatusEffectInstance(StatusEffects.ABSORPTION,400,3),1.0f)
            .build();

}

//定义主类CoinItem
public class CoinItem {
    public static final Item COIN = registerItems("coin",new Item(new FabricItemSettings().food(CoinFoodComponents.COIN)));

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
