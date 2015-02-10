package com.themaskedcrusader.forge.fti;

import com.themaskedcrusader.forge.fti.blocks.DragonEgg;
import com.themaskedcrusader.forge.fti.blocks.DragonObelisk;
import com.themaskedcrusader.forge.fti.blocks.GenericObelisk;
import com.themaskedcrusader.forge.fti.items.AbnormalEgg;
import com.themaskedcrusader.forge.fti.items.CookedAbnormalEgg;
import com.themaskedcrusader.forge.fti.items.CookedZombieFlesh;
import com.themaskedcrusader.forge.fti.items.StrangeEgg;

public class CustomItemsAndBlocks {

    public static void register() {
        // New Custom Blocks
        new DragonEgg().registerRecipe();        // Not new block, just registering a recipe for it.
        new DragonObelisk().register();
        new GenericObelisk().register();

        // New Custom Items
        new AbnormalEgg().register();
        new CookedAbnormalEgg().register();
        new CookedZombieFlesh().register();
        new StrangeEgg().register();
    }
}
