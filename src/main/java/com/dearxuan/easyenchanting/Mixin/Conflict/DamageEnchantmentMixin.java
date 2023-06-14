package com.dearxuan.easyenchanting.Mixin.Conflict;

import net.minecraft.enchantment.DamageEnchantment;
import net.minecraft.enchantment.Enchantment;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

/**
 * 锋利, 亡灵杀手, 节肢杀手
 */
@Mixin(DamageEnchantment.class)
public class DamageEnchantmentMixin {

    @Shadow @Final public int typeIndex;

    /**
     * 锋利, 亡灵杀手, 节肢杀手 不再冲突
     */
    public boolean canAccept(Enchantment other){
        if(other instanceof DamageEnchantment){
            DamageEnchantment damageEnchantment = (DamageEnchantment) other;
            return damageEnchantment.typeIndex != this.typeIndex;
        }else{
            return true;
        }
    }
}
