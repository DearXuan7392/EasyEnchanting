package com.dearxuan.easyenchanting.Mixin.EnchantingConflict;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.enchantment.MultishotEnchantment;
import net.minecraft.entity.EquipmentSlot;
import org.spongepowered.asm.mixin.Mixin;

/**
 * 多重射击
 */
@Mixin(MultishotEnchantment.class)
public class MultishotEnchantmentMixin extends Enchantment {

    public MultishotEnchantmentMixin(Enchantment.Rarity weight, EquipmentSlot... slotTypes) {
        super(weight, EnchantmentTarget.CROSSBOW, slotTypes);
    }

    /**
     * 多重射击 与 穿透 不再冲突
     */
    @Override
    public boolean canAccept(Enchantment other) {
        return super.canAccept(other);
    }
}
