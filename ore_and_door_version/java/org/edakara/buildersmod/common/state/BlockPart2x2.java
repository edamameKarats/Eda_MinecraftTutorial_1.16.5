package org.edakara.buildersmod.common.state;

import net.minecraft.util.IStringSerializable;

public enum BlockPart2x2 implements IStringSerializable {
    CO_11,CO_12,
    C_21,C_22,
    O_21,O_22;

    public String toString() { return this.getSerializedName(); }

    public String getSerializedName() { return this.name().toLowerCase(); }


}
