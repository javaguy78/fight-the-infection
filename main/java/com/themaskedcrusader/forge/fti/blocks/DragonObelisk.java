package com.themaskedcrusader.forge.fti.blocks;

import com.themaskedcrusader.forge.fti.items.AbnormalEgg;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.boss.EntityDragon;
import net.minecraft.entity.boss.EntityDragonPart;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ChatComponentText;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.world.BlockEvent;

import java.lang.reflect.Method;
import java.util.Random;

public class DragonObelisk extends Block {

    private static String NAME = "DragonObelisk";

    public DragonObelisk() {
        super(Material.rock);
        setBlockTextureName(NAME);
        setCreativeTab(CreativeTabs.tabBlock);
        setHardness(50.0F);
        setResistance(50.0F);
        setStepSound(Block.soundTypePiston);
        setBlockName(NAME);
        setLightLevel(0.8F);

        try {
            Method method = Block.class.getMethod("setHarvestLevel", java.lang.String.class, int.class);
            method.setAccessible(true);
            setHarvestLevel("pickaxe", 3);
        } catch (Exception ignored) {}
    }

    public void register() {
        GameRegistry.registerBlock(new DragonObelisk(), NAME);
        registerRecipe();
        MinecraftForge.EVENT_BUS.register(new Listener());
    }

    private void registerRecipe() {
        ItemStack genericObelisk = new ItemStack(new GenericObelisk());
        ItemStack dragonEgg = new ItemStack(Blocks.dragon_egg);
        ItemStack enderPearl = new ItemStack(Items.ender_pearl);
        ItemStack enderEye = new ItemStack(Items.ender_eye);
        ItemStack blazeRod = new ItemStack(Items.blaze_rod);
        GameRegistry.addRecipe(
                new ItemStack(new DragonObelisk()), "dbd", "cec", "bab",
                'a', genericObelisk,
                'b', dragonEgg,
                'c', enderPearl,
                'd', enderEye,
                'e', blazeRod
        );
    }

    @Override
    public Item getItemDropped(int metadata, Random random, int fortune) {
        return new AbnormalEgg();
    }

    @Override
    protected boolean canSilkHarvest() {
        return true;
    }

    @SuppressWarnings("unused")
    public class Listener {
        @SubscribeEvent
        public void summonDragonWithDragonObelisk(BlockEvent.PlaceEvent event) {
            if (
                    event.block instanceof DragonObelisk &&
                    event.world.getBlock(event.x, event.y-1, event.z) instanceof GenericObelisk &&
                    event.world.getBiomeGenForCoords(event.x, event.z) == BiomeGenBase.sky &&
                    !worldAlreadyContainsADragon(event.world)
                    ) {
                new Thread(new SpawnDragonAtObelisk(event.x, event.y, event.z, event.world, event.player.getDisplayName())).start();
            }
        }
    }

    private boolean worldAlreadyContainsADragon(World world) {
        for (Object entity : world.loadedEntityList) {
            if (entity instanceof EntityDragon || entity instanceof EntityDragonPart) {
                return true;
            }
        }
        return false;
    }

    public class SpawnDragonAtObelisk implements Runnable {
        private int x, y, z;
        private World world;
        private String playerName;

        public SpawnDragonAtObelisk(int x, int y, int z, World world, String playerName) {
            this.x = x;
            this.y = y;
            this.z = z;
            this.world = world;
            this.playerName = playerName;
        }

        @Override
        public void run() {
            removeObelisk(x, y, z, world, playerName);
            try { Thread.sleep(30000); } catch (Exception ignored) {}
            spawnDragonIntoWorld(x, z, world);
        }
    }

    @SuppressWarnings("unchecked")
    private void removeObelisk(int x, int y, int z, World world, String player) {
        world.setBlock(x, y, z, Blocks.air);
        world.setBlock(x, y - 1, z, Blocks.air);
        String message = player + " has spawned a new Ender Dragon in the END!!";
        world.playerEntities.forEach((Object p) -> ((EntityPlayer) p).addChatMessage(new ChatComponentText(message)));
    }

    private void spawnDragonIntoWorld(int x, int z, World world) {
        Random random = new Random(world.getSeed() + x + z);
        x = (random.nextBoolean()) ? x + random.nextInt(35) : x - random.nextInt(35);
        z = (random.nextBoolean()) ? z + random.nextInt(35) : z - random.nextInt(35);
        int y = 64 + random.nextInt(100);
        Entity dragon = new EntityDragon(world);
        dragon.setPosition(x, y, z);
        if (!world.isRemote) {
            world.spawnEntityInWorld(dragon);
        }
    }
}
