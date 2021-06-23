package org.edakara.buildersmod.common.block;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraftforge.common.ToolType;

public class MameBlock extends Block {
    public MameBlock() {
        super(AbstractBlock.Properties
                .of(Material.METAL, MaterialColor.METAL)
                .strength(5f, 6f)
                .harvestTool(ToolType.PICKAXE)
                .harvestLevel(2)
                .sound(SoundType.METAL));
    }
}
