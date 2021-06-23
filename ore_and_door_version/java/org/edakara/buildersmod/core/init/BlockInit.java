package org.edakara.buildersmod.core.init;

import net.minecraft.block.Blocks;
import net.minecraft.block.DoorBlock;
import org.edakara.buildersmod.BuildersMod;

import net.minecraft.block.Block;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import org.edakara.buildersmod.common.block.*;

public class BlockInit {
	public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS,
			BuildersMod.MOD_ID);

	public static final RegistryObject<Block> MAME_BLOCK = BLOCKS
			.register("mame_block",
					() -> new MameBlock());
	public static final RegistryObject<Block> MAME_ORE = BLOCKS
			.register("mame_ore",
					() -> new MameOre());
	public static final RegistryObject<Block> WINDOW = BLOCKS
			.register("window",
					() -> new WindowBlock());
	public static final RegistryObject<Block> CASTLE_BIG_PILLAR_BASE = BLOCKS
			.register("castle_big_pillar_base",
					() -> new CastleBigPillarBaseBlock());
	public static final RegistryObject<Block> DUAL_OAK_DOOR = BLOCKS
			.register("dual_oak_door",
					() -> new DualDoorBlock());

}
