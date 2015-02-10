package com.themaskedcrusader.forge.fti.items;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.init.Items;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;

public class CookedZombieFlesh extends ItemFood {

    private String name = "CookedZombieFlesh";

    public CookedZombieFlesh() {
        super(3, 0.3f, true);
        setTextureName(name);
        setUnlocalizedName(name);
        setMaxStackSize(16);

    }

    public void register() {
        GameRegistry.registerItem(new CookedZombieFlesh(), name);
        GameRegistry.addSmelting(Items.rotten_flesh, new ItemStack(new CookedZombieFlesh()), 0.3f);
    }
}
