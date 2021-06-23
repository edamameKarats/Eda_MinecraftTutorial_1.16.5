package org.edakara.buildersmod.common.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.item.ItemStack;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.DirectionProperty;
import net.minecraft.state.EnumProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.shapes.IBooleanFunction;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;
import org.edakara.buildersmod.common.state.BlockPart2x2;
import org.edakara.buildersmod.common.state.DoorBlockStateProperties;

import javax.annotation.Nullable;
import java.util.HashMap;
import java.util.stream.Stream;

public class DualDoorBlock extends Block {

    public static final DirectionProperty FACING = BlockStateProperties.HORIZONTAL_FACING;
    public static final BooleanProperty OPEN = BlockStateProperties.OPEN;
    public static final EnumProperty<BlockPart2x2> PART2X2 = DoorBlockStateProperties.BLOCK_PART_2X2;


    public DualDoorBlock(){
        super(Properties.copy(Blocks.OAK_DOOR));
        this.defaultBlockState().setValue(FACING, Direction.NORTH).setValue(OPEN, Boolean.FALSE).setValue(PART2X2, BlockPart2x2.CO_11);
    }

    private static final VoxelShape SHAPE_N = Stream
            .of(Block.box(15,0,0,16,16,16))
            .reduce((v1, v2) -> {
                return VoxelShapes.joinUnoptimized(v1, v2, IBooleanFunction.OR);
            }).get();

    private static final VoxelShape SHAPE_N_CLOSED = Stream
            .of(Block.box(0,0,0,16,16,1))
            .reduce((v1, v2) -> {
                return VoxelShapes.joinUnoptimized(v1, v2, IBooleanFunction.OR);
            }).get();

    private static final VoxelShape SHAPE_E = Stream
            .of(Block.box(16,0,15,0,16,16))
            .reduce((v1, v2) -> {
                return VoxelShapes.joinUnoptimized(v1, v2, IBooleanFunction.OR);
            }).get();

    private static final VoxelShape SHAPE_E_CLOSED = Stream
            .of(Block.box(16,0,0,15,16,16))
            .reduce((v1, v2) -> {
                return VoxelShapes.joinUnoptimized(v1, v2, IBooleanFunction.OR);
            }).get();

    private static final VoxelShape SHAPE_W = Stream
            .of(Block.box(0,0,1,16,16,0))
            .reduce((v1, v2) -> {
                return VoxelShapes.joinUnoptimized(v1, v2, IBooleanFunction.OR);
            }).get();

    private static final VoxelShape SHAPE_W_CLOSED = Stream
            .of(Block.box(0,0,16,1,16,0))
            .reduce((v1, v2) -> {
                return VoxelShapes.joinUnoptimized(v1, v2, IBooleanFunction.OR);
            }).get();

    private static final VoxelShape SHAPE_S = Stream
            .of(Block.box(1,0,16,0,16,0))
            .reduce((v1, v2) -> {
                return VoxelShapes.joinUnoptimized(v1, v2, IBooleanFunction.OR);
            }).get();

    private static final VoxelShape SHAPE_S_CLOSED = Stream
            .of(Block.box(16,0,16,0,16,15))
            .reduce((v1, v2) -> {
                return VoxelShapes.joinUnoptimized(v1, v2, IBooleanFunction.OR);
            }).get();

    private static final VoxelShape SHAPE_NONE = Stream
            .of(Block.box(0,0,0,0,0,0))
            .reduce((v1, v2) -> {
                return VoxelShapes.joinUnoptimized(v1, v2, IBooleanFunction.OR);
            }).get();

    @SuppressWarnings("deprecation")
    @Override
    //return shapes for direction and open status
    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
        boolean isOpened = state.getValue(OPEN);
        switch (state.getValue(PART2X2)){
            case C_21:
            case C_22:
                switch (state.getValue(FACING)) {
                    case EAST:
                        return isOpened ? SHAPE_NONE : SHAPE_E_CLOSED;
                    case SOUTH:
                        return isOpened ? SHAPE_NONE : SHAPE_S_CLOSED;
                    case WEST:
                        return isOpened ? SHAPE_NONE : SHAPE_W_CLOSED;
                    default:
                        return isOpened ? SHAPE_NONE : SHAPE_N_CLOSED;
                }
            case O_21:
            case O_22:
                switch (state.getValue(FACING)) {
                    case EAST:
                        return isOpened ? SHAPE_E : SHAPE_NONE;
                    case SOUTH:
                        return isOpened ? SHAPE_S : SHAPE_NONE;
                    case WEST:
                        return isOpened ? SHAPE_W : SHAPE_NONE;
                    default:
                        return isOpened ? SHAPE_N : SHAPE_NONE;
                }
            default:
                switch (state.getValue(FACING)) {
                    case EAST:
                        return isOpened ? SHAPE_E : SHAPE_E_CLOSED;
                    case SOUTH:
                        return isOpened ? SHAPE_S : SHAPE_S_CLOSED;
                    case WEST:
                        return isOpened ? SHAPE_W : SHAPE_W_CLOSED;
                    default:
                        return isOpened ? SHAPE_N : SHAPE_N_CLOSED;
                }
        }
    }

    @Nullable
    @Override
    //return state whether block can placed. If not, return null.
        public BlockState getStateForPlacement(BlockItemUseContext context) {
        BlockPos blockpos = context.getClickedPos();

        BlockPos[] positions=getInversePositions(blockpos,context.getPlayer().getDirection());
        if (blockpos.getY() < 255 && context.getLevel().getBlockState(blockpos.above()).canBeReplaced(context)
                && context.getLevel().getBlockState(positions[0]).canBeReplaced(context) && context.getLevel().getBlockState(positions[0].above()).canBeReplaced(context)
                && context.getLevel().getBlockState(positions[1]).canBeReplaced(context) && context.getLevel().getBlockState(positions[1].above()).canBeReplaced(context)) {
            return this.defaultBlockState().setValue(FACING, context.getHorizontalDirection().getOpposite()).setValue(OPEN,false).setValue(PART2X2, BlockPart2x2.CO_11);
        } else {
            return null;
        }
    }


    @Override
    public void setPlacedBy(World worldIn, BlockPos pos, BlockState state, @Nullable LivingEntity placer, ItemStack stack) {
        HashMap<String, BlockPos> positions=getPositions(pos,state);
        worldIn.setBlock(positions.get("co_12"), state.setValue(PART2X2, BlockPart2x2.CO_12), 3);
        worldIn.setBlock(positions.get("o_21"), state.setValue(PART2X2, BlockPart2x2.O_21), 3);
        worldIn.setBlock(positions.get("o_22"), state.setValue(PART2X2, BlockPart2x2.O_22), 3);
        worldIn.setBlock(positions.get("c_21"), state.setValue(PART2X2, BlockPart2x2.C_21), 3);
        worldIn.setBlock(positions.get("c_22"), state.setValue(PART2X2, BlockPart2x2.C_22), 3);
    }

    @Override
    public void playerWillDestroy(World worldIn, BlockPos pos, BlockState state, PlayerEntity player) {
        HashMap<String, BlockPos> positions = getPositions(pos,state);
        super.playerWillDestroy(worldIn, pos, state, player);
        worldIn.setBlock(positions.get("co_11"), Blocks.AIR.defaultBlockState(), 35);
        worldIn.setBlock(positions.get("co_12"), Blocks.AIR.defaultBlockState(), 35);
        worldIn.setBlock(positions.get("o_21"), Blocks.AIR.defaultBlockState(), 35);
        worldIn.setBlock(positions.get("o_22"), Blocks.AIR.defaultBlockState(), 35);
        worldIn.setBlock(positions.get("c_21"), Blocks.AIR.defaultBlockState(), 35);
        worldIn.setBlock(positions.get("c_22"), Blocks.AIR.defaultBlockState(), 35);
    }

    @Override
    public boolean hasTileEntity(BlockState state) {
        return false;
    }

    @Override
    public BlockState mirror(BlockState state, Mirror mirrorIn) {
        return state.rotate(mirrorIn.getRotation(state.getValue(FACING)));
    }

    @Override
    public BlockState rotate(BlockState state, IWorld world, BlockPos pos, Rotation direction) {
        return state.setValue(FACING, direction.rotate(state.getValue(FACING)));
    }


    @Override
    protected void createBlockStateDefinition(StateContainer.Builder<Block, BlockState> builder) {
        super.createBlockStateDefinition(builder);
        builder.add(FACING,OPEN, PART2X2);
    }

    @Override
    public ActionResultType use(BlockState state, World worldIn, BlockPos pos, PlayerEntity player,
                                Hand handIn, BlockRayTraceResult hit) {//
        HashMap<String,BlockPos> positions=getPositions(pos,state);
        state=state.cycle(OPEN);
        worldIn.setBlock(positions.get("co_11"), state.setValue(PART2X2, BlockPart2x2.CO_11), 10); //re-set with new state?
        worldIn.setBlock(positions.get("co_12"), state.setValue(PART2X2, BlockPart2x2.CO_12), 10); //re-set with new state?
        worldIn.setBlock(positions.get("o_21"), state.setValue(PART2X2, BlockPart2x2.O_21), 10); //re-set with new state?
        worldIn.setBlock(positions.get("o_22"), state.setValue(PART2X2, BlockPart2x2.O_22), 10); //re-set with new state?
        worldIn.setBlock(positions.get("c_21"), state.setValue(PART2X2, BlockPart2x2.C_21), 10); //re-set with new state?
        worldIn.setBlock(positions.get("c_22"), state.setValue(PART2X2, BlockPart2x2.C_22), 10); //re-set with new state?
        return ActionResultType.sidedSuccess(worldIn.isClientSide);
    }

    private HashMap<String,BlockPos> getPositions(BlockPos pos, BlockState state){//
        HashMap<String, BlockPos> positions=new HashMap<String, BlockPos>();
        BlockPos stdPos;
        switch (state.getValue(PART2X2)){
            case CO_12:
                stdPos=pos.below();
                break;
            case C_21:
                switch (state.getValue(FACING)) {
                    case EAST:
                        stdPos=pos.relative(Direction.SOUTH);
                        break;
                    case WEST:
                        stdPos=pos.relative(Direction.NORTH);
                        break;
                    case SOUTH:
                        stdPos=pos.relative(Direction.WEST);
                        break;
                    default:
                        stdPos=pos.relative(Direction.EAST);
                }
                break;
            case C_22:
                switch (state.getValue(FACING)) {
                    case EAST:
                        stdPos=pos.relative(Direction.SOUTH).below();
                        break;
                    case WEST:
                        stdPos=pos.relative(Direction.NORTH).below();
                        break;
                    case SOUTH:
                        stdPos=pos.relative(Direction.WEST).below();
                        break;
                    default:
                        stdPos=pos.relative(Direction.EAST).below();
                }
                break;
            case O_21:
                switch (state.getValue(FACING)) {
                    case EAST:
                        stdPos=pos.relative(Direction.EAST);
                        break;
                    case WEST:
                        stdPos=pos.relative(Direction.WEST);
                        break;
                    case SOUTH:
                        stdPos=pos.relative(Direction.SOUTH);
                        break;
                    default:
                        stdPos=pos.relative(Direction.NORTH);
                }
                break;
            case O_22:
                switch (state.getValue(FACING)) {
                    case EAST:
                        stdPos=pos.relative(Direction.EAST).below();
                        break;
                    case WEST:
                        stdPos=pos.relative(Direction.WEST).below();
                        break;
                    case SOUTH:
                        stdPos=pos.relative(Direction.SOUTH).below();
                        break;
                    default:
                        stdPos=pos.relative(Direction.NORTH).below();
                }
                break;
            default:
                stdPos=pos;
        }
        switch (state.getValue(FACING)){
            case EAST:
                positions.put("co_11",stdPos);
                positions.put("o_21",stdPos.relative(Direction.WEST));
                positions.put("c_21",stdPos.relative(Direction.NORTH));
                positions.put("co_12",stdPos.above());
                positions.put("o_22",stdPos.relative(Direction.WEST).above());
                positions.put("c_22",stdPos.relative(Direction.NORTH).above());
                break;
            case WEST:
                positions.put("co_11",stdPos);
                positions.put("o_21",stdPos.relative(Direction.EAST));
                positions.put("c_21",stdPos.relative(Direction.SOUTH));
                positions.put("co_12",stdPos.above());
                positions.put("o_22",stdPos.relative(Direction.EAST).above());
                positions.put("c_22",stdPos.relative(Direction.SOUTH).above());
                break;
            case SOUTH:
                positions.put("co_11",stdPos);
                positions.put("o_21",stdPos.relative(Direction.NORTH));
                positions.put("c_21",stdPos.relative(Direction.EAST));
                positions.put("co_12",stdPos.above());
                positions.put("o_22",stdPos.relative(Direction.NORTH).above());
                positions.put("c_22",stdPos.relative(Direction.EAST).above());
                break;
            default:
                positions.put("co_11",stdPos);
                positions.put("o_21",stdPos.relative(Direction.SOUTH));
                positions.put("c_21",stdPos.relative(Direction.WEST));
                positions.put("co_12",stdPos.above());
                positions.put("o_22",stdPos.relative(Direction.SOUTH).above());
                positions.put("c_22",stdPos.relative(Direction.WEST).above());
        }
        return positions;
    }

    private BlockPos[] getInversePositions(BlockPos pos, Direction state){
        BlockPos[] otherPos=new BlockPos[2];
        switch (state){
            case EAST:
                otherPos[0]=pos.relative(Direction.EAST);
                otherPos[1]=pos.relative(Direction.SOUTH);
                break;
            case WEST:
                otherPos[0]=pos.relative(Direction.WEST);
                otherPos[1]=pos.relative(Direction.NORTH);
                break;
            case SOUTH:
                otherPos[0]=pos.relative(Direction.SOUTH);
                otherPos[1]=pos.relative(Direction.WEST);
                break;
            default:
                otherPos[0]=pos.relative(Direction.NORTH);
                otherPos[1]=pos.relative(Direction.EAST);
        }

        return otherPos;
    }

}
