package com.themaskedcrusader.forge.fti;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.common.MinecraftForge;

@Mod(modid = Plugin.MODID, name= Plugin.NAME, version = Plugin.VERSION)
public class Plugin {
    public static final String MODID = "tmc-fti";
    public static final String NAME  = "Fight The Infection";
    public static final String VERSION = "1.0";

    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        CustomItemsAndBlocks.register();
    }

    @EventHandler
    public void init(FMLInitializationEvent event) {
		// some example code
    }
}
