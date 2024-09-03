package org.minemetero.sussymod;

import org.minemetero.sussymod.blocks.LingboBlock;
import org.minemetero.sussymod.item.CoinItem;
import net.fabricmc.api.ModInitializer;
import org.minemetero.sussymod.item.DislikeItem;
import org.minemetero.sussymod.item.LikeItem;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SussyMod implements ModInitializer {
    public static final String MOD_ID = "sussymod";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    @Override
    public void onInitialize() {//注册
        LOGGER.info("Hello Fabric world!");
        CoinItem.registerCoinItem();
        LikeItem.registerLikeItem();
        DislikeItem.registerDislikeItem();
        LingboBlock.registerLingboBlock();
    }
}