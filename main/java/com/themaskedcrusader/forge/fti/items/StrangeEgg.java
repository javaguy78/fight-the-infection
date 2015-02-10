package com.themaskedcrusader.forge.fti.items;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.init.Items;
import net.minecraft.item.ItemEgg;
import net.minecraft.item.ItemEnderPearl;
import net.minecraft.item.ItemStack;

public class StrangeEgg extends ItemEnderPearl {

    private String name = "StrangeEgg";

    public StrangeEgg() {
        setTextureName(name);
        setUnlocalizedName(name);
        setMaxStackSize(4);
    }

    public void register() {
        GameRegistry.registerItem(new StrangeEgg(), name);
        registerRecipe();
    }

    private void registerRecipe() {
        ItemStack cookedAbnormalEgg = new ItemStack(new CookedAbnormalEgg());
        ItemStack lavaBucket = new ItemStack(Items.lava_bucket);
        ItemStack blazeRod = new ItemStack(Items.blaze_rod);
        ItemStack enderPearl = new ItemStack(Items.ender_pearl);
        GameRegistry.addRecipe(
                new ItemStack(new StrangeEgg()), "aba", "cdc", "aba",
                'a', blazeRod,
                'b', enderPearl,
                'c', lavaBucket,
                'd', cookedAbnormalEgg
        );
    }

    // TODO: Add pearl ability: Teleport, Ender Filter, random teleport on hit for next 30 seconds;
}