package org.minemetero.sussymod;

import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import org.minemetero.sussymod.blocks.LingboBlock;
import org.minemetero.sussymod.item.CoinItem;
import org.minemetero.sussymod.item.DislikeItem;
import org.minemetero.sussymod.item.LikeItem;

public class SussymodItemGroup {
    public static final ItemGroup TUTORIAL_GROUP = Registry.register(Registries.ITEM_GROUP,
            new Identifier(SussyMod.MOD_ID,"sussymod_group"),
            FabricItemGroup.builder().displayName(Text.translatable("itemGroup.lingbos_sussy_mod"))
                    .icon(()-> new ItemStack(LingboBlock.LINGBO_BLOCK)).entries((displayContext, entries) -> {
                        entries.add(CoinItem.COIN);
                        entries.add(DislikeItem.DISLIKE);
                        entries.add(LikeItem.LIKE);
                        entries.add(LingboBlock.LINGBO_BLOCK);
                    }).build());
    public static void registerSussymodItemGroup(){

    }
}