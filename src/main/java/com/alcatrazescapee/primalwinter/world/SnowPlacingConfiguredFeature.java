/*
 * Part of the Primal Winter by AlcatrazEscapee
 * Work under Copyright. See the project LICENSE.md for details.
 */

package com.alcatrazescapee.primalwinter.world;

import java.util.Random;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.GenerationSettings;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.IFeatureConfig;

/**
 * Ensures that the conditions that {@link net.minecraft.world.gen.feature.IceSpikeFeature} and {@link net.minecraft.world.gen.feature.IcePathFeature} both pass in non-ice spikes biomes.
 */
public class SnowPlacingConfiguredFeature<FC extends IFeatureConfig, F extends Feature<FC>> extends ConfiguredFeature<FC, F>
{
    public SnowPlacingConfiguredFeature(F featureIn, FC configIn)
    {
        super(featureIn, configIn);
    }

    @Override
    public boolean place(IWorld worldIn, ChunkGenerator<? extends GenerationSettings> generator, Random rand, BlockPos pos)
    {
        while (worldIn.isAirBlock(pos) && pos.getY() > 2)
        {
            pos = pos.down();
        }
        BlockState originalState = worldIn.getBlockState(pos);
        if (!BlockTags.LEAVES.contains(originalState.getBlock()) && !BlockTags.LOGS.contains(originalState.getBlock()))
        {
            worldIn.setBlockState(pos, Blocks.SNOW_BLOCK.getDefaultState(), 2);
        }
        return super.place(worldIn, generator, rand, pos);
    }
}
