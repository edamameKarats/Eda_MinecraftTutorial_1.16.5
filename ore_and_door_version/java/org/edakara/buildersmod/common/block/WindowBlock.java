package org.edakara.buildersmod.common.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.state.DirectionProperty;
import net.minecraft.state.StateContainer.Builder;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.util.Direction;
import net.minecraft.util.Mirror;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.IBooleanFunction;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;

import java.util.stream.Stream;

public class WindowBlock extends Block {

    public static final DirectionProperty FACING = BlockStateProperties.HORIZONTAL_FACING;

    private static final VoxelShape SHAPE_N = Stream
            .of(Block.box(0,0,0,16,16,16))
            .reduce((v1, v2) -> {
                return VoxelShapes.joinUnoptimized(v1, v2, IBooleanFunction.OR);
            }).get();

    private static final VoxelShape SHAPE_E = Stream
            .of(Block.box(0,0,0,16,16,16))
            .reduce((v1, v2) -> {
                return VoxelShapes.joinUnoptimized(v1, v2, IBooleanFunction.OR);
            }).get();

    private static final VoxelShape SHAPE_W = Stream
            .of(Block.box(0,0,0,16,16,16))
            .reduce((v1, v2) -> {
                return VoxelShapes.joinUnoptimized(v1, v2, IBooleanFunction.OR);
            }).get();

    private static final VoxelShape SHAPE_S = Stream
            .of(Block.box(0,0,0,16,16,16))
            .reduce((v1, v2) -> {
                return VoxelShapes.joinUnoptimized(v1, v2, IBooleanFunction.OR);
            }).get();

    public WindowBlock() {
        super(Properties.of(Material.WOOD, MaterialColor.WOOD).strength(15f)
                .sound(SoundType.WOOD));

        this.registerDefaultState(this.defaultBlockState().setValue(FACING, Direction.NORTH));
    }

    @Override
    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
        switch (state.getValue(FACING)) {
            case EAST:
                return SHAPE_E;
            case SOUTH:
                return SHAPE_S;
            case WEST:
                return SHAPE_W;
            default:
                return SHAPE_N;
        }
    }

    @Override
    public boolean hasTileEntity(BlockState state) {
        return false;
    }

    @SuppressWarnings("deprecation")
    @Override
    public BlockState mirror(BlockState state, Mirror mirrorIn) {
        return state.rotate(mirrorIn.getRotation(state.getValue(FACING)));
    }

    @Override
    public BlockState rotate(BlockState state, IWorld world, BlockPos pos, Rotation direction) {
        return state.setValue(FACING, direction.rotate(state.getValue(FACING)));
    }

    @Override
    public BlockState getStateForPlacement(BlockItemUseContext context) {
        return this.defaultBlockState().setValue(FACING, context.getHorizontalDirection().getOpposite());
    }

    @Override
    protected void createBlockStateDefinition(Builder<Block, BlockState> builder) {
        super.createBlockStateDefinition(builder);
        builder.add(FACING);
    }



}
