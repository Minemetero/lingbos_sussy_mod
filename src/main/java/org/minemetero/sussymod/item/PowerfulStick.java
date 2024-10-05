package org.minemetero.sussymod.item;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroupEntries;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.item.Item;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.ItemGroups;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;
import org.minemetero.sussymod.SussyMod;

import java.util.List;
import java.util.UUID;

import static org.minemetero.sussymod.SussyMod.MOD_ID;

public class PowerfulStick extends Item {

    public PowerfulStick(Settings settings) {
        super(settings);
    }

    private static final UUID MAX_HEALTH_UUID = UUID.randomUUID();
    private static final UUID ATTACK_DAMAGE_UUID = UUID.randomUUID();
    private static final UUID ATTACK_SPEED_UUID = UUID.randomUUID();
    private static final UUID KNOCKBACK_RESISTANCE_UUID = UUID.randomUUID();
    // 添加更多UUID以避免冲突

    @Override
    public Multimap<EntityAttribute, EntityAttributeModifier> getAttributeModifiers(EquipmentSlot slot) {
        Multimap<EntityAttribute, EntityAttributeModifier> modifiers = HashMultimap.create();

        if (slot == EquipmentSlot.MAINHAND) {
            modifiers.put(EntityAttributes.GENERIC_MAX_HEALTH,
                    new EntityAttributeModifier(MAX_HEALTH_UUID, "Max Health", 255, EntityAttributeModifier.Operation.ADDITION));
            modifiers.put(EntityAttributes.GENERIC_ATTACK_DAMAGE,
                    new EntityAttributeModifier(ATTACK_DAMAGE_UUID, "Attack Damage", 255, EntityAttributeModifier.Operation.ADDITION));
            modifiers.put(EntityAttributes.GENERIC_ATTACK_SPEED,
                    new EntityAttributeModifier(ATTACK_SPEED_UUID, "Attack Speed", 255.0, EntityAttributeModifier.Operation.ADDITION));
            modifiers.put(EntityAttributes.GENERIC_KNOCKBACK_RESISTANCE,
                    new EntityAttributeModifier(KNOCKBACK_RESISTANCE_UUID, "Knockback Resistance", 1.0, EntityAttributeModifier.Operation.ADDITION));
        }

        return modifiers;
    }

    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        tooltip.add(Text.translatable("tooltip.sussymod.powerfulstick"));
        super.appendTooltip(stack, world, tooltip, context);
    }

    public static final Item POWERFULSTICK = registerItems("powerfulstick",new PowerfulStick(new FabricItemSettings()));

    private static void addItemsToIG(FabricItemGroupEntries fabricItemGroupEntries){
        fabricItemGroupEntries.add(POWERFULSTICK);
    }
    private static Item registerItems(String name,Item item){
        return Registry.register(Registries.ITEM,new Identifier(SussyMod.MOD_ID,name),item);
    }
    public static void registerPowerfulStick(){
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.FUNCTIONAL).register(PowerfulStick::addItemsToIG);
    }
}
