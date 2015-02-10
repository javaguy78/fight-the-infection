package com.themaskedcrusader.forge.fti.items;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemEgg;
import net.minecraft.item.ItemStack;

public class AbnormalEgg extends ItemEgg {

    private String name = "AbnormalEgg";

    // TODO: Set texture: Ender pearl with endstone texture

    public AbnormalEgg() {
        setTextureName(name);
        setUnlocalizedName(name);
        setMaxStackSize(4);
    }

    public void register() {
        GameRegistry.registerItem(new AbnormalEgg(), name);
        registerRecipe();
    }

    private void registerRecipe(){
        ItemStack obsidian = new ItemStack(Blocks.obsidian);
        ItemStack chickenEgg = new ItemStack(Items.egg);
        ItemStack enderPearl = new ItemStack(Items.ender_pearl);
        GameRegistry.addRecipe(new ItemStack(new AbnormalEgg()), "aba", "aca", "bab",
                    'a', obsidian,
                    'b', enderPearl,
                    'c', chickenEgg
        );
    }
}
