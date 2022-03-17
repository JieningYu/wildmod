package frozenblock.wild.mod.blocks;

import frozenblock.wild.mod.registry.RegisterSounds;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.*;
import net.minecraft.block.enums.Thickness;
import net.minecraft.item.ItemStack;
import net.minecraft.loot.context.LootContext;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;

import java.util.Collections;
import java.util.List;
import java.util.Random;

import static net.minecraft.block.PointedDripstoneBlock.THICKNESS;
import static net.minecraft.block.PointedDripstoneBlock.VERTICAL_DIRECTION;

public class MudBlock extends Block {
    public MudBlock() {
        super(FabricBlockSettings
                .of(Material.SOIL, MapColor.GRAY)
                .sounds(new BlockSoundGroup(1.0f, 1.0f,
                        RegisterSounds.BLOCK_MUD_BREAK,
                        RegisterSounds.BLOCK_MUD_STEP,
                        RegisterSounds.BLOCK_MUD_PLACE,
                        RegisterSounds.BLOCK_MUD_HIT,
                        RegisterSounds.BLOCK_MUD_STEP
                ))
                .strength(0.5f, 0.5f)
                .ticksRandomly()
        );
    }

//please dont kill me i know this is a mess but its the only way i could get it working lol -merp

    @Override
    public void randomTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        int x = pos.getX();
        int y = pos.getY();
        int z = pos.getZ();
        BlockState blockState = Blocks.POINTED_DRIPSTONE.getDefaultState();
        if (blockState.with(VERTICAL_DIRECTION, Direction.UP).with(THICKNESS, Thickness.TIP_MERGE) != world.getBlockState(new BlockPos(x, y - 2, z))) {
            if (blockState.with(THICKNESS, Thickness.BASE).with(VERTICAL_DIRECTION, Direction.DOWN) == world.getBlockState(new BlockPos(x, y - 2, z))) {
                world.setBlockState(new BlockPos(pos), Blocks.CLAY.getDefaultState());
                if (!world.isClient) {
                    world.playSound(
                            null,
                            pos,
                            SoundEvents.ITEM_HONEYCOMB_WAX_ON,
                            SoundCategory.BLOCKS,
                            1f,
                            1f
                    );
                    (world).spawnParticles(ParticleTypes.SPLASH,
                            x + 0.5,
                            y + 0.5,
                            z + 0.5,
                            100,
                            0.2,
                            0.2,
                            0.2,
                            10
                    );
                }
            } else {
                if (blockState.with(THICKNESS, Thickness.FRUSTUM).with(VERTICAL_DIRECTION, Direction.DOWN) == world.getBlockState(new BlockPos(x, y - 2, z))) {
                    world.setBlockState(new BlockPos(pos), Blocks.CLAY.getDefaultState());
                    if (!world.isClient) {
                        world.playSound(
                                null,
                                pos,
                                SoundEvents.ITEM_HONEYCOMB_WAX_ON,
                                SoundCategory.BLOCKS,
                                1f,
                                1f
                        );
                        (world).spawnParticles(ParticleTypes.SPLASH,
                                x + 0.5,
                                y + 0.5,
                                z + 0.5,
                                100,
                                0.2,
                                0.2,
                                0.2,
                                10
                        );
                    }
                } else {
                    if (blockState.with(THICKNESS, Thickness.TIP).with(VERTICAL_DIRECTION, Direction.DOWN) == world.getBlockState(new BlockPos(x, y - 2, z))) {
                        world.setBlockState(new BlockPos(pos), Blocks.CLAY.getDefaultState());
                        if (!world.isClient) {
                            world.playSound(
                                    null,
                                    pos,
                                    SoundEvents.ITEM_HONEYCOMB_WAX_ON,
                                    SoundCategory.BLOCKS,
                                    1f,
                                    1f
                            );
                            (world).spawnParticles(ParticleTypes.SPLASH,
                                    x + 0.5,
                                    y + 0.5,
                                    z + 0.5,
                                    100,
                                    0.2,
                                    0.2,
                                    0.2,
                                    10
                            );
                        }
                    }
                }
            }
        }
    }

    @Override
    public List<ItemStack> getDroppedStacks(BlockState state, LootContext.Builder builder) {
        List<ItemStack> dropsOriginal = super.getDroppedStacks(state, builder);
        if (!dropsOriginal.isEmpty())
            return dropsOriginal;
        return Collections.singletonList(new ItemStack(this, 1));
    }
}
