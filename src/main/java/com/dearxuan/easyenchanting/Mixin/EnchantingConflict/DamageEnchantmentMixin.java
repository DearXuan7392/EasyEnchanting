package com.dearxuan.easyenchanting.Mixin.EnchantingConflict;

import net.minecraft.enchantment.DamageEnchantment;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.entity.EquipmentSlot;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

/**
 * 锋利, 亡灵杀手, 节肢杀手
 */
@Mixin(DamageEnchantment.class)
public class DamageEnchantmentMixin extends Enchantment{

    @Shadow @Final public int typeIndex;

    protected DamageEnchantmentMixin(Rarity weight, EnchantmentTarget target, EquipmentSlot[] slotTypes) {
        super(weight, target, slotTypes);
    }

    /**
     * 锋利, 亡灵杀手, 节肢杀手 不再冲突
     */
    @Override
    public boolean canAccept(Enchantment other){
        if(other instanceof DamageEnchantment){
            DamageEnchantment damageEnchantment = (DamageEnchantment) other;
            return damageEnchantment.typeIndex != this.typeIndex;
        }else{
            return true;
        }
    }
}
