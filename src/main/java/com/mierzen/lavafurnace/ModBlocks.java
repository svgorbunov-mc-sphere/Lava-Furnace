package com.mierzen.lavafurnace;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class ModBlocks
{
    public static Block fuelBlock;

    public static final void init()
    {
        GameRegistry.registerBlock(fuelBlock = new FuelBlock("fuelBlock", Material.iron), "fuelBlock");
        GameRegistry.registerTileEntity(FuelTileEntity.class, "fuelBlockTileEntity");
    }

}
