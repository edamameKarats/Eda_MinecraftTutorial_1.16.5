package org.edakara.buildersmod.core.init;

import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.*;
import org.edakara.buildersmod.BuildersMod;

import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import org.edakara.buildersmod.common.material.CustomArmorMaterial;
import org.edakara.buildersmod.common.material.CustomToolMaterial;
import org.edakara.buildersmod.core.itemgroup.BuildersModItemGroup;

public class ItemInit {
	public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS,
			BuildersMod.MOD_ID);

	public static final RegistryObject<Item> MAME_INGOT = ITEMS.register("mame_ingot",
			() -> new Item(new Item.Properties().tab(BuildersModItemGroup.BUILDERS_MOD)));

	public static final RegistryObject<Item> MAME_SWORD = ITEMS.register("mame_sword",
			() -> new SwordItem(CustomToolMaterial.MAME_TOOL, 5, -1f,
					new Item.Properties().tab(BuildersModItemGroup.BUILDERS_MOD)));

	public static final RegistryObject<Item> MAME_AXE = ITEMS.register("mame_axe",
			() -> new AxeItem(CustomToolMaterial.MAME_TOOL, 4, -2f,
					new Item.Properties().tab(BuildersModItemGroup.BUILDERS_MOD)));

	public static final RegistryObject<Item> MAME_PICKAXE = ITEMS.register("mame_pickaxe",
			() -> new PickaxeItem(CustomToolMaterial.MAME_TOOL, 3, -3f,
					new Item.Properties().tab(BuildersModItemGroup.BUILDERS_MOD)));

	public static final RegistryObject<Item> MAME_SHOVEL = ITEMS.register("mame_shovel",
			() -> new ShovelItem(CustomToolMaterial.MAME_TOOL, 2, -4f,
					new Item.Properties().tab(BuildersModItemGroup.BUILDERS_MOD)));

	public static final RegistryObject<Item> MAME_HOE = ITEMS.register("mame_hoe",
			() -> new HoeItem(CustomToolMaterial.MAME_TOOL, 1, -5f,
					new Item.Properties().tab(BuildersModItemGroup.BUILDERS_MOD)));

	public static final RegistryObject<Item> MAME_HELMET = ITEMS.register("mame_helmet",
			() -> new ArmorItem(CustomArmorMaterial.MAME_ARMOR, EquipmentSlotType.HEAD,
					new Item.Properties().tab(BuildersModItemGroup.BUILDERS_MOD)));

	public static final RegistryObject<Item> MAME_CHESTPLATE = ITEMS.register("mame_chestplate",
			() -> new ArmorItem(CustomArmorMaterial.MAME_ARMOR, EquipmentSlotType.CHEST,
					new Item.Properties().tab(BuildersModItemGroup.BUILDERS_MOD)));

	public static final RegistryObject<Item> MAME_LEGGINGS = ITEMS.register("mame_leggings",
			() -> new ArmorItem(CustomArmorMaterial.MAME_ARMOR, EquipmentSlotType.LEGS,
					new Item.Properties().tab(BuildersModItemGroup.BUILDERS_MOD)));

	public static final RegistryObject<Item> MAME_BOOTS = ITEMS.register("mame_boots",
			() -> new ArmorItem(CustomArmorMaterial.MAME_ARMOR, EquipmentSlotType.FEET,
					new Item.Properties().tab(BuildersModItemGroup.BUILDERS_MOD)));

}
