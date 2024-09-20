package org.minemetero.sussymod.item;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroupEntries;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.TntEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemGroups;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Hand;
import net.minecraft.util.Identifier;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;
import org.minemetero.sussymod.SussyMod;
import org.apache.logging.log4j.Logger; // 确保导入的是 Log4j 的 Logger
import org.apache.logging.log4j.LogManager; // 确保导入 LogManager

import java.util.List;

public class DislikeItem extends Item {
    // 使用 Log4j 的 Logger
    private static final Logger LOGGER = LogManager.getLogger(DislikeItem.class);

    public DislikeItem(Settings settings) {
        super(settings);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        // Extract and log more information about the player and world
        String playerName = user.getName().getString();
        ItemStack itemStack = user.getStackInHand(hand);
        BlockPos playerPos = user.getBlockPos();
        boolean isClient = world.isClient;

        // Log the detailed information
        LOGGER.info("DislikeItem used by player: {}, at position: {}, item in hand: {}, isClient: {}",
                playerName, playerPos, itemStack, isClient);

        // 只在客户端执行
        if (!isClient) {
            // 获取玩家当前位置前方的位置
            BlockPos spawnPos = playerPos.offset(user.getHorizontalFacing(), 2);
            LOGGER.info("Spawn position for TNT: {}", spawnPos);

            // 创建并激活 TNT 实体
            TntEntity tnt = new TntEntity(world, spawnPos.getX(), spawnPos.getY(), spawnPos.getZ(), user);
            int fuseTime = 80; // 设置引线时间为 80 tick（4 秒）
            tnt.setFuse(fuseTime);
            LOGGER.info("TNT entity created with fuse: {} ticks", fuseTime);

            world.spawnEntity(tnt);

            // 给玩家物品施加 3 秒（60 ticks）的冷却时间
            int cooldownTime = 60; // 60 ticks is 3 seconds
            user.getItemCooldownManager().set(this, cooldownTime);
            LOGGER.info("Cooldown applied to item: {} ticks", cooldownTime);
        }

        // 返回成功的操作结果，并保留当前物品堆叠
        return TypedActionResult.success(itemStack);
    }

    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        tooltip.add(Text.translatable("tooltip.sussymod.dislike"));
        super.appendTooltip(stack, world, tooltip, context);
    }

    public static final Item DISLIKE = registerItems("dislike", new DislikeItem(new FabricItemSettings()));

    private static void addItemsToIG(FabricItemGroupEntries fabricItemGroupEntries) {
        fabricItemGroupEntries.add(DISLIKE);
    }

    private static Item registerItems(String name, Item item) {
        return Registry.register(Registries.ITEM, new Identifier(SussyMod.MOD_ID, name), item);
    }

    public static void registerDislikeItem() {
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.FUNCTIONAL).register(DislikeItem::addItemsToIG);
    }
}
