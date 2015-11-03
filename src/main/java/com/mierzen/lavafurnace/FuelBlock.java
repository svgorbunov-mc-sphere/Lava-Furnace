package com.mierzen.lavafurnace;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class FuelBlock extends Block
{
    protected FuelBlock(String unlocalizedName, Material material)
    {
        super(material);
        this.setBlockName(unlocalizedName);
        this.setBlockTextureName(Reference.MODID + ":" + unlocalizedName);
        this.setCreativeTab(CreativeTabs.tabDecorations);
        this.setHardness(4.0F);
        this.setResistance(6.0F);
        this.setLightLevel(10.0F);
        this.setHarvestLevel("pickaxe", 2);
        this.setStepSound(soundTypeMetal);
    }

    @Override
    public boolean hasTileEntity(int metadata)
    {
        return true;
    }

    @Override
    public TileEntity createTileEntity(World world, int metadata)
    {
        return new FuelTileEntity();
    }
}
