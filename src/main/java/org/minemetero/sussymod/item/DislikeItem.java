package org.minemetero.sussymod.item;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroupEntries;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.entity.TntEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Hand;
import net.minecraft.util.Identifier;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.minemetero.sussymod.SussyMod;

public class DislikeItem extends Item {
    public DislikeItem(Settings settings) {
        super(settings);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        // 只在客户端执行
        if (world.isClient) {
            // 获取玩家当前位置前方的位置
            BlockPos pos = user.getBlockPos().offset(user.getHorizontalFacing(), 2);

            // 创建并激活 TNT 实体
            TntEntity tnt = new TntEntity(world, pos.getX(), pos.getY(), pos.getZ(), user);
            tnt.setFuse(80); // 设置引线时间为 80 tick（4 秒）
            world.spawnEntity(tnt);

            // 给玩家物品施加 3 秒（60 ticks）的冷却时间
            user.getItemCooldownManager().set(this, 60);
        }

        // 返回成功的操作结果，并保留当前物品堆叠
        return TypedActionResult.success(user.getStackInHand(hand));
    }

    public static final Item DISLIKE = registerItems("dislike",new DislikeItem(new FabricItemSettings()));

    private static void addItemsToIG(FabricItemGroupEntries fabricItemGroupEntries){
        fabricItemGroupEntries.add(DISLIKE);
    }
    private static Item registerItems(String name,Item item){
        return Registry.register(Registries.ITEM,new Identifier(SussyMod.MOD_ID,name),item);
    }
    public static void registerDislikeItem(){
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.FUNCTIONAL).register(DislikeItem::addItemsToIG);
    }
}