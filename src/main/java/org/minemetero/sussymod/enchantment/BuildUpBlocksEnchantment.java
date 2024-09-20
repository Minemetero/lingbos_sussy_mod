package org.minemetero.sussymod.enchantment;

import net.minecraft.block.Blocks;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.entity.*;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.BlockPos;
import org.jetbrains.annotations.NotNull;

public class BuildUpBlocksEnchantment extends Enchantment {
    public BuildUpBlocksEnchantment(Rarity weight, EquipmentSlot... slotTypes) {
        super(weight, EnchantmentTarget.WEAPON, slotTypes);
    }

    public static void onAttack(@NotNull LivingEntity attacker, int level) {
        if (!attacker.getWorld().isClient) {
            if (attacker instanceof PlayerEntity) {
                BlockPos pos = attacker.getBlockPos();
                double attackerY = attacker.getY();

                if (attacker.getWorld().getBlockState(pos).isOf(Blocks.COBBLESTONE)) {
                    return;
                }

                if (level > 0) {
                    int blockAmount = 2;
                    for (int i = 1; i < level; i++) {
                        blockAmount = (int) Math.ceil(blockAmount * 1.25);
                    }

                    int topY = (int) (attackerY + blockAmount);
                    for (int i = 0; i < blockAmount; i++) {
                        int headY = (int) (attackerY + i + 2);
                        BlockPos headPos = new BlockPos(pos.getX(), headY, pos.getZ());
                        if (!attacker.getWorld().getBlockState(headPos).isAir()) {
                            topY = (int) (attackerY + i);
                            break; // Exit loop when a block is found
                        }
                    }

                    attacker.teleport(attacker.getX(), topY + 1, attacker.getZ());
                    for (int i = 0; i < blockAmount; i++) {
                        int y = (int) (attackerY + i);
                        BlockPos blockPos = new BlockPos(pos.getX(), y, pos.getZ());
                        attacker.getWorld().setBlockState(blockPos, Blocks.COBBLESTONE.getDefaultState());
                    }
                }

                ((PlayerEntity) attacker).getItemCooldownManager().set(attacker.getMainHandStack().getItem(), 10);
            }
        }
    }

    @Override
    public void onTargetDamaged(@NotNull LivingEntity user, @NotNull Entity target, int level) {
        onAttack(user, level);
    }

    @Override
    public int getMaxLevel() {
        return 3;
    }
}
