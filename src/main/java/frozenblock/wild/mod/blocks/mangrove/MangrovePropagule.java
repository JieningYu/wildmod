package frozenblock.wild.mod.blocks.mangrove;


import frozenblock.wild.mod.liukrastapi.MangroveSaplingGenerator;
import frozenblock.wild.mod.registry.MangroveWoods;
import net.minecraft.block.*;
import net.minecraft.block.piston.PistonBehavior;
import net.minecraft.entity.ai.pathing.NavigationType;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.tag.BlockTags;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;
import net.minecraft.world.WorldView;
import org.jetbrains.annotations.Nullable;

import java.util.Random;

public class MangrovePropagule extends PlantBlock implements Waterloggable, Fertilizable {
    public static final BooleanProperty HANGING = BooleanProperty.of("hanging");
    public static final BooleanProperty WATERLOGGED = BooleanProperty.of("waterlogged");
    private final MangroveSaplingGenerator generator;


    public MangrovePropagule(Settings settings) {
        super(settings);
        setDefaultState(getStateManager().getDefaultState()
                .with(WATERLOGGED, false)
                .with(HANGING, false)
        );
        this.generator = new MangroveSaplingGenerator();
    }

    protected static Direction attachedDirection(BlockState state) {
        return state.get(HANGING) ? Direction.DOWN : Direction.UP;
    }

    @Nullable
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        FluidState fluidState = ctx.getWorld().getFluidState(ctx.getBlockPos());
        Direction[] var3 = ctx.getPlacementDirections();
        int var4 = var3.length;

        for (int var5 = 0; var5 < var4; ++var5) {
            Direction direction = var3[var5];
            if (direction.getAxis() == Direction.Axis.Y) {
                BlockState blockState = (BlockState) this.getDefaultState().with(HANGING, direction == Direction.UP);
                if (blockState.canPlaceAt(ctx.getWorld(), ctx.getBlockPos())) {
                    return (BlockState) blockState.with(WATERLOGGED, fluidState.getFluid() == Fluids.WATER);
                }
            }
        }

        return null;
    }

    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(HANGING, WATERLOGGED);
    }

    public boolean canPlaceAt(BlockState state, WorldView world, BlockPos pos) {
        if (state.getBlock()==MangroveWoods.MANGROVE_PROPAGULE) {
            Direction direction = attachedDirection(state).getOpposite();
            if (direction == Direction.UP) {
                return world.getBlockState(new BlockPos(pos.up())).getMaterial() == Material.LEAVES;
            } else {
                BlockPos blockPos = pos.down();
                return world.getBlockState(new BlockPos(blockPos)).isIn(BlockTags.DIRT);
            }
        }
        return false;
    }

    public PistonBehavior getPistonBehavior(BlockState state) {
        return PistonBehavior.DESTROY;
    }

    public BlockState getStateForNeighborUpdate(BlockState state, Direction direction, BlockState neighborState, WorldAccess world, BlockPos pos, BlockPos neighborPos) {
        if (state.get(WATERLOGGED)) {
            world.createAndScheduleFluidTick(pos, Fluids.WATER, Fluids.WATER.getTickRate(world));
        }

        return attachedDirection(state).getOpposite() == direction && !state.canPlaceAt(world, pos) ? Blocks.AIR.getDefaultState() : super.getStateForNeighborUpdate(state, direction, neighborState, world, pos, neighborPos);
    }

    public FluidState getFluidState(BlockState state) {
        return (Boolean) state.get(WATERLOGGED) ? Fluids.WATER.getStill(false) : super.getFluidState(state);
    }

    public boolean canPathfindThrough(BlockState state, BlockView world, BlockPos pos, NavigationType type) {
        return false;
    }


    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        Vec3d offset = state.getModelOffset(world, pos);
        if (!state.get(HANGING)) {
            return VoxelShapes.union(createCuboidShape(4, 0, 4, 12, 8, 12)).offset(offset.x, offset.y, offset.z);
        } else {
            return VoxelShapes.union(createCuboidShape(4, 8, 4, 12, 16, 12)).offset(offset.x, offset.y, offset.z);
        }
    }

    public void randomTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        if (world.getLightLevel(pos.up()) >= 9 && random.nextInt(7) == 0) {
            this.generate(world, pos, state, random);
        }

    }

    public boolean isFertilizable(BlockView world, BlockPos pos, BlockState state, boolean isClient) {
        return true;
    }

    public boolean canGrow(World world, Random random, BlockPos pos, BlockState state) {
        return (double)world.random.nextFloat() < 0.45D;
    }

    public void grow(ServerWorld world, Random random, BlockPos pos, BlockState state) {
        this.generate(world, pos, state, random);
    }

    public void generate(ServerWorld world, BlockPos pos, BlockState state, Random random) {
        if(!state.get(HANGING)) {
            world.setBlockState(pos, Blocks.AIR.getDefaultState());
            int x = pos.getX();
            int y = pos.getY();
            int z = pos.getZ();
            this.generator.generate(world, world.getChunkManager().getChunkGenerator(), new BlockPos(x, y, z), state, random);
        }
    }
}
