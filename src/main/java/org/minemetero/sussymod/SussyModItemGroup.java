package org.minemetero.sussymod;

import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.enchantment.EnchantmentLevelEntry;
import net.minecraft.item.EnchantedBookItem;
import net.minecraft.item.Items;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registry;
import net.minecraft.registry.Registries;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import org.minemetero.sussymod.blocks.LingboBlock;
import org.minemetero.sussymod.item.CoinItem;
import org.minemetero.sussymod.item.DislikeItem;
import org.minemetero.sussymod.item.LikeItem;
import org.minemetero.sussymod.item.PowerfulStick;

public class SussyModItemGroup {
    public static final ItemGroup SUSSYMOD_GROUP = Registry.register(
            Registries.ITEM_GROUP,
            new Identifier(SussyMod.MOD_ID, "sussymod_group"),
            FabricItemGroup.builder()
                    .displayName(Text.translatable("itemGroup.lingbos_sussy_mod"))
                    .icon(() -> new ItemStack(LingboBlock.LINGBO_BLOCK))
                    .entries((displayContext, entries) -> {
                        // Your existing items
                        entries.add(CoinItem.COIN);
                        entries.add(DislikeItem.DISLIKE);
                        entries.add(LikeItem.LIKE);
                        entries.add(LingboBlock.LINGBO_BLOCK);
                        entries.add(PowerfulStick.POWERFULSTICK);

                        // Enchanted book with custom enchantment
                        ItemStack enchantedBook = new ItemStack(Items.ENCHANTED_BOOK);
                        EnchantedBookItem.addEnchantment(
                                enchantedBook,
                                new EnchantmentLevelEntry(SussyMod.BUILD_UP_BLOCKS, SussyMod.BUILD_UP_BLOCKS.getMaxLevel())
                        );
                        entries.add(enchantedBook);
                    })
                    .build()
    );

    public static void registerSussymodItemGroup() {
        // Any additional initialization code
    }
}
