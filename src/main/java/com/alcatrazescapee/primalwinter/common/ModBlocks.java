/*
 * Part of the Primal Winter by AlcatrazEscapee
 * Work under Copyright. See the project LICENSE.md for details.
 */

package com.alcatrazescapee.primalwinter.common;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.function.Supplier;

import com.google.common.collect.ImmutableMap;
import net.minecraft.block.*;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import static com.alcatrazescapee.primalwinter.PrimalWinter.MOD_ID;

public final class ModBlocks
{
    public static final DeferredRegister<Block> BLOCKS = new DeferredRegister<>(ForgeRegistries.BLOCKS, MOD_ID);

    public static final RegistryObject<Block> SNOWY_DIRT = register("snowy_dirt", () -> new Block(Block.Properties.from(Blocks.DIRT)));
    public static final RegistryObject<Block> SNOWY_COARSE_DIRT = register("snowy_coarse_dirt", () -> new Block(Block.Properties.from(Blocks.COARSE_DIRT)));
    public static final RegistryObject<Block> SNOWY_SAND = register("snowy_sand", () -> new SandBlock(0xdbd3a0, Block.Properties.from(Blocks.SAND)));
    public static final RegistryObject<Block> SNOWY_RED_SAND = register("snowy_red_sand", () -> new SandBlock(0xa95821, Block.Properties.from(Blocks.RED_SAND)));
    public static final RegistryObject<Block> SNOWY_GRAVEL = register("snowy_gravel", () -> new GravelBlock(Block.Properties.from(Blocks.GRAVEL)));
    public static final RegistryObject<Block> SNOWY_STONE = register("snowy_stone", () -> new Block(Block.Properties.from(Blocks.STONE)));
    public static final RegistryObject<Block> SNOWY_GRANITE = register("snowy_granite", () -> new Block(Block.Properties.from(Blocks.GRANITE)));
    public static final RegistryObject<Block> SNOWY_ANDESITE = register("snowy_andesite", () -> new Block(Block.Properties.from(Blocks.ANDESITE)));
    public static final RegistryObject<Block> SNOWY_DIORITE = register("snowy_diorite", () -> new Block(Block.Properties.from(Blocks.DIORITE)));

    public static final RegistryObject<Block> SNOWY_OAK_LOG = register("snowy_oak_log", () -> new Block(Block.Properties.from(Blocks.OAK_LOG)));
    public static final RegistryObject<Block> SNOWY_BIRCH_LOG = register("snowy_birch_log", () -> new Block(Block.Properties.from(Blocks.BIRCH_LOG)));
    public static final RegistryObject<Block> SNOWY_SPRUCE_LOG = register("snowy_spruce_log", () -> new Block(Block.Properties.from(Blocks.SPRUCE_LOG)));
    public static final RegistryObject<Block> SNOWY_JUNGLE_LOG = register("snowy_jungle_log", () -> new Block(Block.Properties.from(Blocks.JUNGLE_LOG)));
    public static final RegistryObject<Block> SNOWY_DARK_OAK_LOG = register("snowy_dark_oak_log", () -> new Block(Block.Properties.from(Blocks.DARK_OAK_LOG)));
    public static final RegistryObject<Block> SNOWY_ACACIA_LOG = register("snowy_acacia_log", () -> new Block(Block.Properties.from(Blocks.ACACIA_LOG)));

    public static final RegistryObject<Block> SNOWY_OAK_LEAVES = register("snowy_oak_leaves", () -> new Block(Block.Properties.from(Blocks.OAK_LEAVES)));
    public static final RegistryObject<Block> SNOWY_BIRCH_LEAVES = register("snowy_birch_leaves", () -> new Block(Block.Properties.from(Blocks.BIRCH_LEAVES)));
    public static final RegistryObject<Block> SNOWY_SPRUCE_LEAVES = register("snowy_spruce_leaves", () -> new Block(Block.Properties.from(Blocks.SPRUCE_LEAVES)));
    public static final RegistryObject<Block> SNOWY_JUNGLE_LEAVES = register("snowy_jungle_leaves", () -> new Block(Block.Properties.from(Blocks.JUNGLE_LEAVES)));
    public static final RegistryObject<Block> SNOWY_DARK_OAK_LEAVES = register("snowy_dark_oak_leaves", () -> new Block(Block.Properties.from(Blocks.DARK_OAK_LEAVES)));
    public static final RegistryObject<Block> SNOWY_ACACIA_LEAVES = register("snowy_acacia_leaves", () -> new Block(Block.Properties.from(Blocks.ACACIA_LEAVES)));

    public static final Map<Block, Supplier<Block>> SNOWY_TERRAIN_BLOCKS = new HashMap<>(new ImmutableMap.Builder<Block, Supplier<Block>>()
        .put(Blocks.DIRT, SNOWY_DIRT)
        .put(Blocks.GRASS_BLOCK, SNOWY_DIRT)
        .put(Blocks.PODZOL, SNOWY_DIRT)
        .put(Blocks.COARSE_DIRT, SNOWY_COARSE_DIRT)
        .put(Blocks.SAND, SNOWY_SAND)
        .put(Blocks.RED_SAND, SNOWY_RED_SAND)
        .put(Blocks.GRAVEL, SNOWY_GRAVEL)
        .put(Blocks.STONE, SNOWY_STONE)
        .put(Blocks.GRANITE, SNOWY_GRANITE)
        .put(Blocks.ANDESITE, SNOWY_ANDESITE)
        .put(Blocks.DIORITE, SNOWY_DIORITE)
        .build()
    );
    public static final Map<Block, Supplier<Block>> SNOWY_TREE_BLOCKS = new HashMap<>(new ImmutableMap.Builder<Block, Supplier<Block>>()
        .put(Blocks.OAK_LOG, SNOWY_OAK_LOG)
        .put(Blocks.BIRCH_LOG, SNOWY_BIRCH_LOG)
        .put(Blocks.SPRUCE_LOG, SNOWY_SPRUCE_LOG)
        .put(Blocks.JUNGLE_LOG, SNOWY_JUNGLE_LOG)
        .put(Blocks.DARK_OAK_LOG, SNOWY_DARK_OAK_LOG)
        .put(Blocks.ACACIA_LOG, SNOWY_ACACIA_LOG)
        .put(Blocks.OAK_LEAVES, SNOWY_OAK_LEAVES)
        .put(Blocks.BIRCH_LEAVES, SNOWY_BIRCH_LEAVES)
        .put(Blocks.SPRUCE_LEAVES, SNOWY_SPRUCE_LEAVES)
        .put(Blocks.JUNGLE_LEAVES, SNOWY_JUNGLE_LEAVES)
        .put(Blocks.DARK_OAK_LEAVES, SNOWY_DARK_OAK_LEAVES)
        .put(Blocks.ACACIA_LEAVES, SNOWY_ACACIA_LEAVES)
        .build()
    );

    private static <T extends Block> RegistryObject<T> register(String name, Supplier<T> blockFactory)
    {
        return register(name, blockFactory, block -> new BlockItem(block, new Item.Properties()));
    }

    private static <T extends Block> RegistryObject<T> register(String name, Supplier<T> blockFactory, Function<T, ? extends BlockItem> blockItemFactory)
    {
        RegistryObject<T> block = BLOCKS.register(name, blockFactory);
        ModItems.ITEMS.register(name, block.lazyMap(blockItemFactory));
        return block;
    }
}