package com.themaskedcrusader.forge.fti.blocks;

import com.themaskedcrusader.forge.fti.items.StrangeEgg;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;

public class DragonEgg {

    public void registerRecipe() {
        ItemStack ingredient = new ItemStack(new StrangeEgg());
        ItemStack product = new ItemStack(Blocks.dragon_egg);
        float experience = 0.9f;
        GameRegistry.addSmelting(ingredient, product, experience);
    }

}
