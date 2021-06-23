package org.edakara.buildersmod;

import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.RegistryObject;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.edakara.buildersmod.core.init.BlockInit;
import org.edakara.buildersmod.core.init.FeatureInit;
import org.edakara.buildersmod.core.init.ItemInit;
import org.edakara.buildersmod.core.itemgroup.BuildersModItemGroup;

@Mod("buildersmod")
@Mod.EventBusSubscriber(modid = BuildersMod.MOD_ID, bus = Bus.MOD)
public class BuildersMod {

	public static final Logger LOGGER = LogManager.getLogger();
	public static final String MOD_ID = "buildersmod";

	public BuildersMod() {
		IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();

		BlockInit.BLOCKS.register(bus);
		ItemInit.ITEMS.register(bus);

		MinecraftForge.EVENT_BUS.addListener(EventPriority.HIGH, FeatureInit::addOres);
		MinecraftForge.EVENT_BUS.register(this);
	}

	@SubscribeEvent
	public static void onRegisterItems(final RegistryEvent.Register<Item> event) {
		BlockInit.BLOCKS.getEntries().stream().map(RegistryObject::get).forEach(block -> {
			event.getRegistry().register(new BlockItem(block, new Item.Properties().tab(BuildersModItemGroup.BUILDERS_MOD))
					.setRegistryName(block.getRegistryName()));
		});
	}
}
