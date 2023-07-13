package com.dearxuan.easyenchanting.Mixin.Mending;

import net.fabricmc.fabric.api.item.v1.FabricItemStack;
import net.minecraft.advancement.criterion.Criteria;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.enchantment.UnbreakingEnchantment;
import net.minecraft.item.ItemStack;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.stat.Stats;
import net.minecraft.util.math.random.Random;
import org.jetbrains.annotations.Nullable;
import org.slf4j.Logger;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

import java.util.function.Consumer;

@Mixin(ItemStack.class)
public abstract class ItemStackMixin implements FabricItemStack {

    @Shadow
    public abstract boolean isDamageable();

    @Shadow @Final private static Logger LOGGER;

    /**
     * @author
     * @reason
     */
    @Overwrite
    public boolean damage(int amount, Random random, @Nullable ServerPlayerEntity player) {
        if (!this.isDamageable()) {
            return false;
        } else {
            ItemStack _this = (ItemStack) (Object)this;
            int i;
            if (amount > 0) {
                i = EnchantmentHelper.getLevel(Enchantments.UNBREAKING, _this);
                int j = 0;

                for(int k = 0; i > 0 && k < amount; ++k) {
                    if (UnbreakingEnchantment.shouldPreventDamage(_this, i, random)) {
                        ++j;
                    }
                }

                amount -= j;
                if (amount <= 0) {
                    return false;
                }
            }

            // 上次损耗值
            int lastDamage = _this.getDamage();
            // 此时 i 是该物品算上本次损耗的总共损耗值
            i = lastDamage + amount;
            // 实际修复值
            int actualRepair = 0;
            if(player != null){
                // 进行经验修补
                if(EnchantmentHelper.getLevel(Enchantments.MENDING, _this) > 0){
                    LOGGER.error(String.valueOf(EnchantmentHelper.getLevel(Enchantments.MENDING, _this)));
                    // 如果经验不足, 则不修复
                    actualRepair = Math.min(i, player.totalExperience * 2);
                    // 实际修复值 > 0
                    if(actualRepair > 0){
                        // 如果经验是单数, 为了与原版匹配, 有 50% 的概率扣除额外一点经验
                        if(actualRepair % 2 == 1 && random.nextBoolean()){
                            actualRepair += 1;
                        }
                        // 扣除经验
                        player.addExperience(- ((actualRepair + 1) / 2));

                    }
                }

                // 经验修补结束
                if(lastDamage != _this.getDamage()){
                    Criteria.ITEM_DURABILITY_CHANGED.trigger(player, _this, _this.getDamage() + amount);
                }
            }
            // 更新损耗值
            _this.setDamage(i - actualRepair);
            return i >= _this.getMaxDamage();
        }
    }
}
