package org.minemetero.sussymod;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import org.minemetero.sussymod.blocks.LingboBlock;
import org.minemetero.sussymod.enchantment.BuildUpBlocksEnchantment;
import org.minemetero.sussymod.item.CoinItem;
import net.fabricmc.api.ModInitializer;
import org.minemetero.sussymod.item.DislikeItem;
import org.minemetero.sussymod.item.LikeItem;
import org.minemetero.sussymod.item.PowerfulStick;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SussyMod implements ModInitializer {
    public static final String MOD_ID = "sussymod";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    public static final Enchantment BUILD_UP_BLOCKS = Registry.register(
            Registries.ENCHANTMENT,
            new Identifier("sussymod", "build_up_blocks"),
            new BuildUpBlocksEnchantment(Enchantment.Rarity.RARE, EquipmentSlot.MAINHAND)
    );

    @Override
    public void onInitialize() {//注册
        LOGGER.info("Sussy,sussy lingbopro");
        SussyModItemGroup.registerSussymodItemGroup();
        CoinItem.registerCoinItem();
        LikeItem.registerLikeItem();
        DislikeItem.registerDislikeItem();
        LingboBlock.registerLingboBlock();
        PowerfulStick.registerPowerfulStick();
    }
}