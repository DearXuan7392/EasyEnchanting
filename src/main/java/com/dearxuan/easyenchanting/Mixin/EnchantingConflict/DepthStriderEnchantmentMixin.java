package com.dearxuan.easyenchanting.Mixin.EnchantingConflict;

import net.minecraft.enchantment.DepthStriderEnchantment;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.entity.EquipmentSlot;
import org.spongepowered.asm.mixin.Mixin;

/**
 * 深海探索者
 */
@Mixin(DepthStriderEnchantment.class)
public class DepthStriderEnchantmentMixin extends Enchantment{

    public DepthStriderEnchantmentMixin(Enchantment.Rarity weight, EquipmentSlot... slotTypes) {
        super(weight, EnchantmentTarget.ARMOR_FEET, slotTypes);
    }

    /**
     * 深海探索者 与 冰霜行者 不再冲突
     */
    @Override
    public boolean canAccept(Enchantment other) {
        return super.canAccept(other);
    }
}
