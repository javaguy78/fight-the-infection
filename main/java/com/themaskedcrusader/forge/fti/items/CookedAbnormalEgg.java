package com.themaskedcrusader.forge.fti.items;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.item.ItemEnderPearl;
import net.minecraft.item.ItemStack;

public class CookedAbnormalEgg extends ItemEnderPearl {

    private String name= "CookedAbnormalEgg";

    public CookedAbnormalEgg() {
        setTextureName(name);
        setUnlocalizedName(name);
        setMaxStackSize(4);
    }

    //TODO: Add texture of enderpearl with texture of bluish end stone;

    public void register() {
        GameRegistry.registerItem(new CookedAbnormalEgg(), name);
        ItemStack ingredient = new ItemStack(new AbnormalEgg());
        ItemStack product = new ItemStack(new CookedAbnormalEgg());
        GameRegistry.addSmelting(ingredient, product, 0.4F);
    }

    //TODO: Add listener: Same effect as ender pearl, but changes filter to end filter for 30 seconds;
    // TODO: Override OnItemRightClick
}
