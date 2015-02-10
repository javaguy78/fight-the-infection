package com.themaskedcrusader.forge.fti.blocks;

import com.themaskedcrusader.forge.fti.helper.EntityHelper;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.BlockAir;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityEnderCrystal;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;

import java.lang.reflect.Method;
import java.util.Random;

public class GenericObelisk extends Block {

    private static final String NAME = "GenericObelisk";

    public GenericObelisk() {
        super(Material.rock);
        setCreativeTab(CreativeTabs.tabBlock);
        setHardness(50.0F);
        setResistance(50.0F);
        setStepSound(Block.soundTypePiston);
        setBlockTextureName(NAME);
        setBlockName(NAME);
        setLightLevel(0.3F);

        try {
            Method method = Block.class.getMethod("setHarvestLevel", java.lang.String.class, int.class);
            method.setAccessible(true);
            setHarvestLevel("pickaxe", 3);
        } catch (Exception ignored) {}
    }

    public void register() {
        GameRegistry.registerBlock(new GenericObelisk(), NAME);
        registerRecipe();
        MinecraftForge.EVENT_BUS.register(new Listener());
    }

    private void registerRecipe() {
        ItemStack obsidian = new ItemStack(Blocks.obsidian);
        ItemStack dragonEgg = new ItemStack(Blocks.dragon_egg);
        GameRegistry.addRecipe(
                new ItemStack(new GenericObelisk()), "aaa", "asa", "aaa",
                'a', obsidian,
                's', dragonEgg
        );
    }

    @Override
    public Item getItemDropped(int metadata, Random random, int fortune) {
        return Item.getItemFromBlock(Blocks.obsidian);
    }

    @Override
    public int quantityDropped(Random unused) {
        return 8;
    }

    @Override
    protected boolean canSilkHarvest() {
        return true;
    }

    @SuppressWarnings("unused")
    public class Listener {

        @SubscribeEvent
        public void createCrystalOnGenericObeliskWithNetherStar(PlayerInteractEvent event) {
            if (
                    event.action == PlayerInteractEvent.Action.RIGHT_CLICK_BLOCK &&
                    event.world.getBlock(event.x, event.y, event.z) instanceof GenericObelisk &&
                    event.entityPlayer.getHeldItem() != null &&
                    event.entityPlayer.getHeldItem().getItem() == Items.nether_star &&
                    event.world.getBlock(event.x, event.y + 1, event.z) instanceof BlockAir
                ) {
                --event.entityPlayer.getHeldItem().stackSize;
                spawnCrystalAt(event.world, event.x, event.y + 1, event.z);
            }
        }

        public void spawnCrystalAt(World world, double x, double y, double z) {
            Entity crystal = new EntityEnderCrystal(world);
            crystal.setPosition(x + 0.5, y, z + 0.5);
            if (!world.isRemote) {
                world.spawnEntityInWorld(crystal);
            }
        }
    }
}