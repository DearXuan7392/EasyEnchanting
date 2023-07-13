package com.dearxuan.easyenchanting.Mixin.EnchantingConflict;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.enchantment.RiptideEnchantment;
import net.minecraft.entity.EquipmentSlot;
import org.spongepowered.asm.mixin.Mixin;

/**
 * 激流
 */
@Mixin(RiptideEnchantment.class)
public class RiptideEnchantmentMixin extends Enchantment {

    public RiptideEnchantmentMixin(Enchantment.Rarity weight, EquipmentSlot... slotTypes) {
        super(weight, EnchantmentTarget.TRIDENT, slotTypes);
    }

    /**
     * 激流 不再与 忠诚, 引雷 冲突
     */
    @Override
    public boolean canAccept(Enchantment other) {
        return super.canAccept(other);
    }
}
