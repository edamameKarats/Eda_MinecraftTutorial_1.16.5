package org.edakara.buildersmod.core.init;

import org.edakara.buildersmod.BuildersMod;

import net.minecraft.block.Block;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import org.edakara.buildersmod.common.block.MameBlock;
import org.edakara.buildersmod.common.block.MameOre;

public class BlockInit {
	public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS,
			BuildersMod.MOD_ID);

	public static final RegistryObject<Block> MAME_BLOCK = BLOCKS
			.register("mame_block",
					() -> new MameBlock());
	public static final RegistryObject<Block> MAME_ORE = BLOCKS
			.register("mame_ore",
					() -> new MameOre());

}
