package com.mierzen.lavafurnace;

import com.google.common.base.Objects;
import net.minecraft.block.Block;
import net.minecraft.block.BlockFurnace;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityFurnace;

import javax.annotation.Nullable;

public class FuelTileEntity extends TileEntity
{
    /**
     * <p>Determine if the given ItemStacks are equal.</p>
     * <p>This method checks the Item, damage value and NBT data of the stack, it does not check stack sizes.</p>
     * <p>Adapted from diesieben07's SevenCommons Modding Framework: https://goo.gl/wKCm2L.</p>
     *
     * @param a an ItemStack
     * @param b an ItemStack
     * @return true if the ItemStacks are equal
     */
    //@Contract("null, null -> true; null, !null -> false; !null, null -> false")
    public static boolean equal(@Nullable ItemStack a, @Nullable ItemStack b)
    {
        return a == b || (a != null && b != null && equalsImpl(a, b));
    }

    private static boolean equalsImpl(ItemStack a, ItemStack b)
    {
        return a.getItem() == b.getItem() && a.getItemDamage() == b.getItemDamage()
                && Objects.equal(a.stackTagCompound, b.stackTagCompound);
    }

    @Override
    public void updateEntity()
    {
        if (!worldObj.isRemote)
        {
            Block block = worldObj.getBlock(xCoord, yCoord + 1, zCoord);
            if (block == Blocks.furnace)
            {
                TileEntity tile = worldObj.getTileEntity(xCoord, yCoord + 1, zCoord);
                TileEntityFurnace furnace = (TileEntityFurnace)tile;

                ItemStack toBurn = furnace.getStackInSlot(0);
                if (toBurn != null)
                {
                    ItemStack toBurnSmeltResult = FurnaceRecipes.smelting().getSmeltingResult(toBurn);
                    ItemStack currentOutput = furnace.getStackInSlot(2);

                    if (toBurnSmeltResult != null && (currentOutput == null || equal(currentOutput, toBurnSmeltResult)) && furnace.getBurnTimeRemainingScaled(100) <= 1)
                    {
                        BlockFurnace.updateFurnaceBlockState(true, worldObj, xCoord, yCoord + 1, zCoord);
                        furnace.furnaceBurnTime = 201;
                    }
                }
            }
        }

        super.updateEntity();
    }
}
