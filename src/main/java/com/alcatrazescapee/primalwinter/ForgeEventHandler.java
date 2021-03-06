/*
 * Part of the Primal Winter by AlcatrazEscapee
 * Work under Copyright. See the project LICENSE.md for details.
 */

package com.alcatrazescapee.primalwinter;

import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import net.minecraft.command.Commands;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.GameRules;
import net.minecraft.world.WorldType;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.dimension.DimensionType;
import net.minecraft.world.server.ChunkHolder;
import net.minecraft.world.server.ServerChunkProvider;
import net.minecraft.world.server.ServerWorld;
import net.minecraft.world.storage.WorldInfo;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.world.WorldEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.server.FMLServerStartingEvent;

import com.alcatrazescapee.primalwinter.util.VanillaHacks;

import static com.alcatrazescapee.primalwinter.PrimalWinter.MOD_ID;

@Mod.EventBusSubscriber(modid = MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public final class ForgeEventHandler
{
    @SubscribeEvent
    public static void onServerStarting(FMLServerStartingEvent event)
    {
        if (Config.COMMON.disableWeatherCommand.get())
        {
            // Vanilla weather command... NOT ALLOWED
            event.getCommandDispatcher().getRoot().getChildren().removeIf(node -> node.getName().equals("weather"));
            event.getCommandDispatcher().register(Commands.literal("weather").executes(source -> {
                source.getSource().sendFeedback(new StringTextComponent("Not even a command can overcome this storm... (This command is disabled by Primal Winter)"), false);
                return 0;
            }));
        }
    }

    @SubscribeEvent
    public static void onWorldLoad(WorldEvent.Load event)
    {
        if (event.getWorld() instanceof ServerWorld && event.getWorld().getDimension().getType() == DimensionType.OVERWORLD)
        {
            ServerWorld world = (ServerWorld) event.getWorld();
            world.getGameRules().get(GameRules.DO_WEATHER_CYCLE).set(false, world.getServer());
            world.getWorldInfo().setClearWeatherTime(0);
            world.getWorldInfo().setRainTime(Integer.MAX_VALUE);
            world.getWorldInfo().setThunderTime(Integer.MAX_VALUE);
            world.getWorldInfo().setRaining(true);
            world.getWorldInfo().setThundering(true);
        }
    }

    /**
     * Large sections are duplicated logic from {@link ServerChunkProvider#tickChunks()}
     */
    @SubscribeEvent
    public static void onWorldPostTick(TickEvent.WorldTickEvent event)
    {
        if (event.phase == TickEvent.Phase.END && event.world instanceof ServerWorld)
        {
            // Simulate ice and snow
            ServerWorld world = (ServerWorld) event.world;
            WorldInfo worldInfo = world.getWorldInfo();
            if (worldInfo.getGenerator() != WorldType.DEBUG_ALL_BLOCK_STATES)
            {
                ServerChunkProvider chunkProvider = world.getChunkProvider();
                VanillaHacks.getLoadedChunksIterable(chunkProvider.chunkManager).forEach(chunkHolder -> {
                    Optional<Chunk> optional = chunkHolder.getEntityTickingFuture().getNow(ChunkHolder.UNLOADED_CHUNK).left();
                    if (optional.isPresent())
                    {
                        Chunk chunk = optional.get();
                        chunkHolder.sendChanges(chunk);
                        world.getProfiler().endSection();
                        ChunkPos chunkPos = chunkHolder.getPosition();
                        if (!VanillaHacks.isOutsideSpawningRadius(chunkProvider.chunkManager, chunkPos))
                        {
                            VanillaHacks.tickRainAndSnow(world, chunk);
                        }
                    }
                });
            }
        }
    }

    private final Logger LOGGER = LogManager.getLogger();
}
