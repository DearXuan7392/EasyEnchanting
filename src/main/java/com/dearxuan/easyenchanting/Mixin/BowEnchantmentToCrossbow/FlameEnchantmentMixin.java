package com.dearxuan.easyenchanting.Mixin.BowEnchantmentToCrossbow;

import net.minecraft.enchantment.FlameEnchantment;
import net.minecraft.item.BowItem;
import net.minecraft.item.CrossbowItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(FlameEnchantment.class)
public class FlameEnchantmentMixin {

    public boolean isAcceptableItem(ItemStack stack){
        Item item = stack.getItem();
        return item instanceof BowItem || item instanceof CrossbowItem;
    }
}
