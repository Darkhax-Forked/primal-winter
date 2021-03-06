/*
 * Part of the Primal Winter by AlcatrazEscapee
 * Work under Copyright. See the project LICENSE.md for details.
 */

package com.alcatrazescapee.primalwinter.common;

import net.minecraft.block.BlockState;
import net.minecraft.block.VineBlock;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorldReader;

public class WinterVineBlock extends VineBlock
{
    public WinterVineBlock(Properties properties)
    {
        super(properties);
    }

    @Override
    public boolean canBeReplacedByLogs(BlockState state, IWorldReader world, BlockPos pos)
    {
        return true;
    }
}
