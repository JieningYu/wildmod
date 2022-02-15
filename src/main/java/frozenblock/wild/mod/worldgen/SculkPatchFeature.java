package frozenblock.wild.mod.worldgen;

import com.mojang.serialization.Codec;
import frozenblock.wild.mod.blocks.SculkVeinBlock;
import frozenblock.wild.mod.fromAccurateSculk.SculkTags;
import frozenblock.wild.mod.registry.RegisterBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.tag.Tag;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.noise.PerlinNoiseSampler;
import net.minecraft.world.StructureWorldAccess;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.util.FeatureContext;
import net.minecraft.world.gen.random.SimpleRandom;

import java.util.ArrayList;
import java.util.Random;

public class SculkPatchFeature extends Feature<DefaultFeatureConfig> {

    Random random = new Random();
    /** NOISE VARIABLES */
    public static final double multiplier = 0.20; //Lowering this makes the noise bigger, raising it makes it smaller (more like static)
    public static final double minThreshold = -0.3; //The value that outer Sculk's noise must be ABOVE in order to grow
    public static final double maxThreshold = 0.3; //The value that outer Sculk's noise must be BELOW in order to grow
    public static long seed = 1; //This gets set to the current world's seed in generate()
    public static final int thresholdTransition=40; //When this is lower, the min&max thresholds for Sculk placement will quickly fluctuate based on location. When higher, the min&max thresholds will have a longer, but smoother transition.
    public static PerlinNoiseSampler sample = new PerlinNoiseSampler(new SimpleRandom(seed));

    public SculkPatchFeature(Codec<DefaultFeatureConfig> configCodec) {
        super(configCodec);
    }

    @Override
    public boolean generate(FeatureContext<DefaultFeatureConfig> context) {
        if (seed!=context.getWorld().getSeed()) {
            seed=context.getWorld().getSeed();
            sample = new PerlinNoiseSampler(new SimpleRandom(seed));
        }
        context.getWorld().setBlockState(context.getOrigin().up(), RegisterBlocks.SCULK_CATALYST.getDefaultState(), 0);
        if (context.getWorld().getBlockEntity(context.getOrigin())==null) {
            context.getWorld().setBlockState(context.getOrigin(), RegisterBlocks.SCULK.getDefaultState(), 0);
        }
        double average = (context.getOrigin().getX() + context.getOrigin().getZ())*0.5;
        int radius = (int) (8*Math.cos(((average)*Math.PI)/24) + 16);
        double minThresh = 0.1 * Math.cos(((average)*Math.PI)/thresholdTransition);
        double maxThresh = 0.15 * Math.sin(((average)*Math.PI)/thresholdTransition);
        placePatch(context, context.getOrigin(), radius, minThreshold+minThresh, maxThreshold+maxThresh);
        return true;
    }


    public void placePatch(FeatureContext<DefaultFeatureConfig> context, BlockPos pos, int r, double min, double max) {
        StructureWorldAccess world = context.getWorld();
        //Place Sculk
        for (BlockPos blockpos : blockTagsInSphere(pos, r, SculkTags.BLOCK_REPLACEABLE, world)) {
            double sampled = sample.sample(blockpos.getX()*multiplier, blockpos.getY()*multiplier,blockpos.getZ()*multiplier);
            if (sampled>min && sampled<max) {
                placeSculkOptim(blockpos, world);
            }
        }

        //Place Veins
        for (BlockPos blockpos : solidInSphere(pos, r, world)) {
            double sampled = sample.sample(blockpos.getX()*multiplier, blockpos.getY()*multiplier,blockpos.getZ()*multiplier);
            if (sampled<min && sampled>min-0.16) {
                veins(blockpos, world);
            }
        }
        for (BlockPos blockpos : solidInSphere(pos, r, world)) {
            double sampled = sample.sample(blockpos.getX()*multiplier, blockpos.getY()*multiplier,blockpos.getZ()*multiplier);
            if (sampled>max && sampled<max+0.16) {
                veins(blockpos, world);
            }
        }
        for (BlockPos blockpos : hollowedSphere(pos, r+1, world)) {
            veins(blockpos, world);
        }

        //Place Activators
        for (BlockPos blockpos : blocksInSphere(pos, r, RegisterBlocks.SCULK, world)) {
            double sampled = sample.sample(blockpos.getX()*1.5, blockpos.getY()*1.5,blockpos.getZ()*1.5);
            if (SculkTags.SCULK_VEIN_REPLACEABLE.contains(world.getBlockState(blockpos.up()).getBlock())) {
                Block activator = null;
                if (sampled<0.55 && sampled>0.41) {
                    activator=SculkTags.COMMON_ACTIVATORS.getRandom(random);
                }
                if (sampled<1 && sampled>0.6) {
                    activator=SculkTags.RARE_ACTIVATORS.getRandom(random);
                }
                if (activator!=null) {
                    if (SculkTags.GROUND_ACTIVATORS.contains(activator)) {
                        world.setBlockState(blockpos.up(), activator.getDefaultState(), 0);
                    } else {
                        if ((world.getBlockState(blockpos.up()).contains(waterLogged) && world.getBlockState(blockpos.up()).get(waterLogged)) || world.getBlockState(blockpos.up()) == water) {
                            world.setBlockState(blockpos.up(), activator.getDefaultState().with(waterLogged, true), 0);
                        } else {
                            world.setBlockState(blockpos.up(), activator.getDefaultState(), 0);
                        }
                    }
                }
            }
        }
    }


    public static void placeSculkOptim(BlockPos blockPos, StructureWorldAccess world) { //Place Sculk & Remove Veins
        world.setBlockState(blockPos, RegisterBlocks.SCULK.getDefaultState(), 0);
        for (Direction direction : Direction.values()) {
            BlockPos pos = blockPos.offset(direction);
            BlockState state = world.getBlockState(pos);
            Block block = state.getBlock();
            if (block==veinBlock) {
                if (state.get(waterLogged)) { //If Vein Is Waterlogged
                    if (state.with(getOpposite(direction), false)==brokenWaterVein) {
                        world.setBlockState(pos, Blocks.WATER.getDefaultState(),0);
                    } else {
                        world.setBlockState(pos, state.with(getOpposite(direction), false),0);
                    }
                } else { // If Vein Isn't Waterlogged
                    if (state.with(getOpposite(direction), false)==brokenVein) {
                        world.setBlockState(pos, Blocks.AIR.getDefaultState(),0);
                    } else {
                        world.setBlockState(pos, state.with(getOpposite(direction), false),0);
                    }
                }
            }
            if (direction==Direction.UP) {
                if (SculkTags.SCULK_VEIN_REPLACEABLE.contains(block) && block!=waterBlock && !state.isAir()) {
                    if (SculkTags.ALWAYS_WATER.contains(block) || (state.contains(waterLogged) && state.get(waterLogged))) {
                        world.setBlockState(pos, Blocks.WATER.getDefaultState(),0);
                    } else {
                        world.setBlockState(pos, Blocks.AIR.getDefaultState(),0);
                    }
                }
            }
        }
    }

    public static void veins(BlockPos blockpos, StructureWorldAccess world) {
        for (Direction direction : Direction.values()) {
            BlockPos pos1 = blockpos.offset(direction);
            BlockState state = world.getBlockState(pos1);
            Block block = state.getBlock();
            if (SculkTags.ALWAYS_WATER.contains(block) || state == Blocks.WATER.getDefaultState()) {
                world.setBlockState(pos1, RegisterBlocks.SCULK_VEIN.getDefaultState().with(waterLogged, true).with(getOpposite(direction), true),0);
            } else if (block != waterBlock) {
                if (block == veinBlock) {
                    world.setBlockState(pos1, state.with(getOpposite(direction), true),0);
                } else if (SculkTags.SCULK_VEIN_REPLACEABLE.contains(block) || state.isAir()) {
                    world.setBlockState(pos1, RegisterBlocks.SCULK_VEIN.getDefaultState().with(getOpposite(direction), true),0);
                }
            }
        }
    }

    public static BooleanProperty getOpposite(Direction direction) {
        if (direction==Direction.UP) { return Properties.DOWN; }
        if (direction==Direction.DOWN) { return Properties.UP; }
        if (direction==Direction.NORTH) { return Properties.SOUTH; }
        if (direction==Direction.SOUTH) { return Properties.NORTH; }
        if (direction==Direction.EAST) { return Properties.WEST; }
        if (direction==Direction.WEST) { return Properties.EAST; }
        return Properties.DOWN;
    }

    public static final BlockState brokenVein = SculkVeinBlock.SCULK_VEIN.getDefaultState().with(Properties.DOWN, false);
    public static final BlockState water = Blocks.WATER.getDefaultState();
    public static final BlockState air = Blocks.AIR.getDefaultState();
    public static final Block waterBlock = Blocks.WATER;
    public static final Block veinBlock = SculkVeinBlock.SCULK_VEIN;
    public static final BooleanProperty waterLogged = Properties.WATERLOGGED;
    public static final BlockState brokenWaterVein = SculkVeinBlock.SCULK_VEIN.getDefaultState().with(Properties.DOWN, false).with(waterLogged, true);

    public static ArrayList<BlockPos> blockTagsInSphere(BlockPos pos, int radius, Tag<Block> tag, StructureWorldAccess world) {
        int bx = pos.getX();
        int by = pos.getY();
        int bz = pos.getZ();
        ArrayList<BlockPos> blocks = new ArrayList<>();
        for(int x = bx - radius; x <= bx + radius; x++) {
            for(int y = by - radius; y <= by + radius; y++) {
                for(int z = bz - radius; z <= bz + radius; z++) {
                    double distance = ((bx-x) * (bx-x) + ((bz-z) * (bz-z)) + ((by-y) * (by-y)));
                    if(distance < radius * radius) {
                        BlockPos l = new BlockPos(x, y, z);
                        if (tag.contains(world.getBlockState(l).getBlock())) {
                            blocks.add(l);
                        }
                    }
                }
            }
        }
        return blocks;
    }

    public static ArrayList<BlockPos> solidInSphere(BlockPos pos, int radius, StructureWorldAccess world) {
        int bx = pos.getX();
        int by = pos.getY();
        int bz = pos.getZ();
        ArrayList<BlockPos> blocks = new ArrayList<>();
        for(int x = bx - radius; x <= bx + radius; x++) {
            for(int y = by - radius; y <= by + radius; y++) {
                for(int z = bz - radius; z <= bz + radius; z++) {
                    double distance = ((bx-x) * (bx-x) + ((bz-z) * (bz-z)) + ((by-y) * (by-y)));
                    if(distance < radius * radius) {
                        BlockPos l = new BlockPos(x, y, z);
                        if (world.getBlockState(l).isFullCube(world, l) && !SculkTags.SCULK.contains(world.getBlockState(l).getBlock())) {
                            blocks.add(l);
                        }
                    }
                }
            }
        }
        return blocks;
    }
    public static ArrayList<BlockPos> blocksInSphere(BlockPos pos, int radius, Block block, StructureWorldAccess world) {
        ArrayList<BlockPos> blocks = new ArrayList<>();
        int bx = pos.getX();
        int by = pos.getY();
        int bz = pos.getZ();
        for(int x = bx - radius; x <= bx + radius; x++) {
            for(int y = by - radius; y <= by + radius; y++) {
                for(int z = bz - radius; z <= bz + radius; z++) {
                    double distance = ((bx-x) * (bx-x) + ((bz-z) * (bz-z)) + ((by-y) * (by-y)));
                    if(distance < radius * radius) {
                        BlockPos l = new BlockPos(x, y, z);
                        if (world.getBlockState(l).getBlock() == block) {
                            blocks.add(l);
                        }
                    }
                }
            }
        }
        return blocks;
    }

    public static ArrayList<BlockPos> hollowedSphere(BlockPos pos, int radius, StructureWorldAccess world) {
        ArrayList<BlockPos> blocks = new ArrayList<>();
        int bx = pos.getX();
        int by = pos.getY();
        int bz = pos.getZ();
        for (int x = bx - radius; x <= bx + radius; x++) {
            for (int y = by - radius; y <= by + radius; y++) {
                for (int z = bz - radius; z <= bz + radius; z++) {
                    double distance = ((bx - x) * (bx - x) + ((bz - z) * (bz - z)) + ((by - y) * (by - y)));
                    if (distance < radius * radius && !(distance < (radius - 1) * (radius - 1))) {
                        BlockPos l = new BlockPos(x, y, z);
                        if (world.getBlockState(l).isFullCube(world, l) && !SculkTags.SCULK.contains(world.getBlockState(l).getBlock())) {
                            blocks.add(l);
                        }
                    }
                }
            }
        }
        return blocks;
    }
}