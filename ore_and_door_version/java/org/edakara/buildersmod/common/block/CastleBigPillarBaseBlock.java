package org.edakara.buildersmod.common.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.state.DirectionProperty;
import net.minecraft.state.StateContainer;
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

public class CastleBigPillarBaseBlock extends Block {

    public static final DirectionProperty FACING = BlockStateProperties.HORIZONTAL_FACING;

    private static final VoxelShape SHAPE_N = Stream
            .of(Block.box(6,0,1,10,5,32),Block.box(0,0,0,6,5,32),Block.box(0,5,2,5,8,32),Block.box(0,8,6,5,16,32),Block.box(5,8,7,9,16,32),Block.box(9,8,8,11,16,32),Block.box(11,8,9,13,16,32),Block.box(13,8,10,14,16,32),Block.box(14,8,11,16,16,32),Block.box(16,8,12,17,16,32),Block.box(17,8,13,18,16,32),Block.box(18,8,14,19,16,32),Block.box(19,8,15,20,16,32),Block.box(20,8,16,21,16,32),Block.box(21,8,18,22,16,32),Block.box(23,8,21,24,16,32),Block.box(24,8,23,25,16,32),Block.box(25,8,27,26,16,32),Block.box(22,8,19,23,16,32),Block.box(5,5,3,9,8,32),Block.box(9,5,4,12,8,32),Block.box(12,5,5,14,8,32),Block.box(14,5,6,16,8,32),Block.box(16,5,7,17,8,32),Block.box(17,5,8,18,8,32),Block.box(18,5,9,20,8,32),Block.box(20,5,10,21,8,32),Block.box(21,5,11,22,8,32),Block.box(22,5,12,23,8,32),Block.box(23,5,14,24,8,32),Block.box(24,5,15,25,8,32),Block.box(25,5,16,26,8,32),Block.box(26,5,18,27,8,32),Block.box(27,5,20,28,8,32),Block.box(28,5,23,29,8,32),Block.box(29,5,27,30,8,32),Block.box(10,0,2,12,5,32),Block.box(12,0,3,14,5,32),Block.box(14,0,4,16,5,32),Block.box(16,0,5,18,5,32),Block.box(18,0,6,19,5,32),Block.box(19,0,7,20,5,32),Block.box(20,0,8,22,5,32),Block.box(24,0,12,25,5,32),Block.box(25,0,13,26,5,32),Block.box(26,0,14,27,5,32),Block.box(27,0,16,28,5,32),Block.box(29,0,20,30,5,32),Block.box(30,0,22,31,5,32),Block.box(31,0,26,32,5,32),Block.box(28,0,18,29,5,32),Block.box(23,0,10,24,5,32),Block.box(22,0,9,23,5,32))
            .reduce((v1, v2) -> {
                return VoxelShapes.joinUnoptimized(v1, v2, IBooleanFunction.OR);
            }).get();

    private static final VoxelShape SHAPE_E = Stream
            .of(Block.box(15,0,6,-16,5,5),Block.box(16,0,0,-16,5,5),Block.box(14,5,0,-16,8,8),Block.box(10,8,0,-16,16,16),Block.box(9,8,5,-16,16,16),Block.box(8,8,9,-16,16,16),Block.box(7,8,11,-16,16,16),Block.box(6,8,13,-16,16,16),Block.box(5,8,14,-16,16,16),Block.box(4,8,16,-16,16,16),Block.box(3,8,17,-16,16,16),Block.box(2,8,18,-16,16,16),Block.box(1,8,19,-16,16,16),Block.box(0,8,20,-16,16,16),Block.box(-2,8,21,-16,16,16),Block.box(-5,8,23,-16,16,16),Block.box(-7,8,24,-16,16,16),Block.box(-11,8,25,-16,16,16),Block.box(-3,8,22,-16,16,16),Block.box(13,5,5,-16,8,8),Block.box(12,5,9,-16,8,8),Block.box(11,5,12,-16,8,8),Block.box(10,5,14,-16,8,8),Block.box(9,5,16,-16,8,8),Block.box(8,5,17,-16,8,8),Block.box(7,5,18,-16,8,8),Block.box(6,5,20,-16,8,8),Block.box(5,5,21,-16,8,8),Block.box(4,5,22,-16,8,8),Block.box(2,5,23,-16,8,8),Block.box(1,5,24,-16,8,8),Block.box(0,5,25,-16,8,8),Block.box(-2,5,26,-16,8,8),Block.box(-4,5,27,-16,8,8),Block.box(-7,5,28,-16,8,8),Block.box(-11,5,29,-16,8,8),Block.box(14,0,10,-16,5,5),Block.box(13,0,12,-16,5,5),Block.box(12,0,14,-16,5,5),Block.box(11,0,16,-16,5,5),Block.box(10,0,18,-16,5,5),Block.box(9,0,19,-16,5,5),Block.box(8,0,20,-16,5,5),Block.box(4,0,24,-16,5,5),Block.box(3,0,25,-16,5,5),Block.box(2,0,26,-16,5,5),Block.box(0,0,27,-16,5,5),Block.box(-4,0,29,-16,5,5),Block.box(-6,0,30,-16,5,5),Block.box(-10,0,31,-16,5,5),Block.box(-2,0,28,-16,5,5),Block.box(6,0,23,-16,5,5),Block.box(7,0,22,-16,5,5))
            .reduce((v1, v2) -> {
                return VoxelShapes.joinUnoptimized(v1, v2, IBooleanFunction.OR);
            }).get();

    private static final VoxelShape SHAPE_W = Stream
            .of(Block.box(1,0,10,32,5,6),Block.box(0,0,16,32,5,10),Block.box(2,5,16,32,8,11),Block.box(6,8,16,32,16,11),Block.box(7,8,11,32,16,7),Block.box(8,8,7,32,16,5),Block.box(9,8,5,32,16,3),Block.box(10,8,3,32,16,2),Block.box(11,8,2,32,16,0),Block.box(12,8,0,32,16,-1),Block.box(13,8,-1,32,16,-2),Block.box(14,8,-2,32,16,-3),Block.box(15,8,-3,32,16,-4),Block.box(16,8,-4,32,16,-5),Block.box(18,8,-5,32,16,-6),Block.box(21,8,-7,32,16,-8),Block.box(23,8,-8,32,16,-9),Block.box(27,8,-9,32,16,-10),Block.box(19,8,-6,32,16,-7),Block.box(3,5,11,32,8,7),Block.box(4,5,7,32,8,4),Block.box(5,5,4,32,8,2),Block.box(6,5,2,32,8,0),Block.box(7,5,0,32,8,-1),Block.box(8,5,-1,32,8,-2),Block.box(9,5,-2,32,8,-4),Block.box(10,5,-4,32,8,-5),Block.box(11,5,-5,32,8,-6),Block.box(12,5,-6,32,8,-7),Block.box(14,5,-7,32,8,-8),Block.box(15,5,-8,32,8,-9),Block.box(16,5,-9,32,8,-10),Block.box(18,5,-10,32,8,-11),Block.box(20,5,-11,32,8,-12),Block.box(23,5,-12,32,8,-13),Block.box(27,5,-13,32,8,-14),Block.box(2,0,6,32,5,4),Block.box(3,0,4,32,5,2),Block.box(4,0,2,32,5,0),Block.box(5,0,0,32,5,-2),Block.box(6,0,-2,32,5,-3),Block.box(7,0,-3,32,5,-4),Block.box(8,0,-4,32,5,-6),Block.box(12,0,-8,32,5,-9),Block.box(13,0,-9,32,5,-10),Block.box(14,0,-10,32,5,-11),Block.box(16,0,-11,32,5,-12),Block.box(20,0,-13,32,5,-14),Block.box(22,0,-14,32,5,-15),Block.box(26,0,-15,32,5,-16),Block.box(18,0,-12,32,5,-13),Block.box(10,0,-7,32,5,-8),Block.box(9,0,-6,32,5,-7))
            .reduce((v1, v2) -> {
                return VoxelShapes.joinUnoptimized(v1, v2, IBooleanFunction.OR);
            }).get();

    private static final VoxelShape SHAPE_S = Stream
            .of(Block.box(10,0,15,6,5,-16),Block.box(16,0,16,10,5,-16),Block.box(16,5,14,11,8,-16),Block.box(16,8,10,11,16,-16),Block.box(11,8,9,7,16,-16),Block.box(7,8,8,5,16,-16),Block.box(5,8,7,3,16,-16),Block.box(3,8,6,2,16,-16),Block.box(2,8,5,0,16,-16),Block.box(0,8,4,-1,16,-16),Block.box(-1,8,3,-2,16,-16),Block.box(-2,8,2,-3,16,-16),Block.box(-3,8,1,-4,16,-16),Block.box(-4,8,0,-5,16,-16),Block.box(-5,8,-2,-6,16,-16),Block.box(-7,8,-5,-8,16,-16),Block.box(-8,8,-7,-9,16,-16),Block.box(-9,8,-11,-10,16,-16),Block.box(-6,8,-3,-7,16,-16),Block.box(11,5,13,7,8,-16),Block.box(7,5,12,4,8,-16),Block.box(4,5,11,2,8,-16),Block.box(2,5,10,0,8,-16),Block.box(0,5,9,-1,8,-16),Block.box(-1,5,8,-2,8,-16),Block.box(-2,5,7,-4,8,-16),Block.box(-4,5,6,-5,8,-16),Block.box(-5,5,5,-6,8,-16),Block.box(-6,5,4,-7,8,-16),Block.box(-7,5,2,-8,8,-16),Block.box(-8,5,1,-9,8,-16),Block.box(-9,5,0,-10,8,-16),Block.box(-10,5,-2,-11,8,-16),Block.box(-11,5,-4,-12,8,-16),Block.box(-12,5,-7,-13,8,-16),Block.box(-13,5,-11,-14,8,-16),Block.box(6,0,14,4,5,-16),Block.box(4,0,13,2,5,-16),Block.box(2,0,12,0,5,-16),Block.box(0,0,11,-2,5,-16),Block.box(-2,0,10,-3,5,-16),Block.box(-3,0,9,-4,5,-16),Block.box(-4,0,8,-6,5,-16),Block.box(-8,0,4,-9,5,-16),Block.box(-9,0,3,-10,5,-16),Block.box(-10,0,2,-11,5,-16),Block.box(-11,0,0,-12,5,-16),Block.box(-13,0,-4,-14,5,-16),Block.box(-14,0,-6,-15,5,-16),Block.box(-15,0,-10,-16,5,-16),Block.box(-12,0,-2,-13,5,-16),Block.box(-7,0,6,-8,5,-16),Block.box(-6,0,7,-7,5,-16))
            .reduce((v1, v2) -> {
                return VoxelShapes.joinUnoptimized(v1, v2, IBooleanFunction.OR);
            }).get();

    public CastleBigPillarBaseBlock() {
        super(Properties.of(Material.STONE, MaterialColor.STONE).strength(15f)
                .sound(SoundType.STONE));

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
    protected void createBlockStateDefinition(StateContainer.Builder<Block, BlockState> builder) {
        super.createBlockStateDefinition(builder);
        builder.add(FACING);
    }

}
