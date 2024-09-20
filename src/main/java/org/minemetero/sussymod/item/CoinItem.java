package org.minemetero.sussymod.item;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroupEntries;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.FoodComponent;
import net.minecraft.item.ItemGroups;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;
import org.minemetero.sussymod.SussyMod;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

import java.util.List;

// Define the main CoinItem class, extending Item
public class CoinItem extends Item {
    // CoinItem attributes
    static class CoinFoodComponents {
        public static final FoodComponent COIN = new FoodComponent.Builder().hunger(3).saturationModifier(1.0f)
                .statusEffect(new StatusEffectInstance(StatusEffects.REGENERATION, 100, 1), 1.0f)
                .statusEffect(new StatusEffectInstance(StatusEffects.INSTANT_DAMAGE, 600, 0), 1.0f)
                .statusEffect(new StatusEffectInstance(StatusEffects.FIRE_RESISTANCE, 600, 0), 1.0f)
                .statusEffect(new StatusEffectInstance(StatusEffects.ABSORPTION, 400, 3), 1.0f)
                .build();
    }

    // Constructor for CoinItem
    public CoinItem() {
        super(new FabricItemSettings().food(CoinFoodComponents.COIN));
    }

    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        tooltip.add(Text.translatable("tooltip.sussymod.coin"));
        super.appendTooltip(stack, world, tooltip, context);
    }

    public static final Item COIN = registerItems("coin", new CoinItem());

    private static void addItemsToIG(FabricItemGroupEntries fabricItemGroupEntries) {
        fabricItemGroupEntries.add(COIN);
    }

    private static Item registerItems(String name, Item item) {
        return Registry.register(Registries.ITEM, new Identifier(SussyMod.MOD_ID, name), item);
    }

    public static void registerCoinItem() {
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.FOOD_AND_DRINK).register(CoinItem::addItemsToIG);
    }
}
