package com.themaskedcrusader.forge.fti.items;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemEgg;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.EnderTeleportEvent;

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

    @Override
    public ItemStack onItemRightClick(ItemStack egg, World world, EntityPlayer player) {
        if (player.rayTrace(200, 1.0F) != null) {
            int x = player.rayTrace(200, 1.0F).blockX;
            int y = player.rayTrace(200, 1.0F).blockY;
            int z = player.rayTrace(200, 1.0F).blockZ;
            EnderTeleportEvent event = new EnderTeleportEvent(player, x, y, z, 3.0F);
            MinecraftForge.EVENT_BUS.post(event);
            if (!event.isCanceled()) {
                --egg.stackSize;
            }
        }
        return egg;
    }
}
