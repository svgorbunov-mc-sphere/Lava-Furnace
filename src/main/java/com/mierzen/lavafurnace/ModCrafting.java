package com.mierzen.lavafurnace;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

public class ModCrafting
{
    public static void init()
    {
        GameRegistry.addRecipe(new ItemStack(ModBlocks.fuelBlock), "i i", "iLi", "iii", 'i', Items.iron_ingot, 'L', Items.lava_bucket);
    }
}
