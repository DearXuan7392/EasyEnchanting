package com.dearxuan.easyenchanting.Mixin.BowEnchantmentToCrossbow;

import net.minecraft.enchantment.Enchantments;
import net.minecraft.enchantment.PunchEnchantment;
import net.minecraft.item.BowItem;
import net.minecraft.item.CrossbowItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(PunchEnchantment.class)
public class PunchEnchantmentMixin {

    public boolean isAcceptableItem(ItemStack stack){
        Item item = stack.getItem();
        return item instanceof BowItem || item instanceof CrossbowItem;
    }
}
