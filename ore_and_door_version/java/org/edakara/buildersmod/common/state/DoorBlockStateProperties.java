package org.edakara.buildersmod.common.state;

import net.minecraft.state.EnumProperty;
import net.minecraft.state.properties.*;

public class DoorBlockStateProperties extends BlockStateProperties {
    public static final EnumProperty<BlockPart2x2> BLOCK_PART_2X2 = EnumProperty.create("part2x2", BlockPart2x2.class);
}