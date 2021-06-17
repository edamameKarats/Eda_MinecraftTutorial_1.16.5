package org.edakara.buildersmod.common.material;

import java.util.function.Supplier;

import net.minecraft.item.IItemTier;
import net.minecraft.item.crafting.Ingredient;
import org.edakara.buildersmod.core.init.ItemInit;

public enum CustomToolMaterial implements IItemTier {

	MAME_TOOL(4, 4000, 15f, 2f, 17, () -> Ingredient.of(ItemInit.MAME_INGOT.get()));

	private final int level;
	private final int uses;
	private final float speed;
	private final float attackDamageBonus;
	private final int enchantmentValue;
	private final Ingredient repairIngredient;

	CustomToolMaterial(int level, int uses, float speed, float attackDamageBonus, int enchantmentValue,
			Supplier<Ingredient> repairIngredient) {
		this.level = level;
		this.uses = uses;
		this.speed = speed;
		this.attackDamageBonus = attackDamageBonus;
		this.enchantmentValue = enchantmentValue;
		this.repairIngredient = repairIngredient.get();
	}

	@Override
	public int getUses() { return this.uses; }

	@Override
	public float getSpeed() {
		return this.speed;
	}

	@Override
	public float getAttackDamageBonus() {
		return this.attackDamageBonus;
	}

	@Override
	public int getLevel() {
		return this.level;
	}

	@Override
	public int getEnchantmentValue() {
		return this.enchantmentValue;
	}

	@Override
	public Ingredient getRepairIngredient() {
		return this.repairIngredient;
	}

}
