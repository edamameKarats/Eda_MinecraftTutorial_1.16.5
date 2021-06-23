package org.edakara.buildersmod.core.itemgroup;

import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import org.edakara.buildersmod.core.init.ItemInit;

public class BuildersModItemGroup extends ItemGroup {
    public static final BuildersModItemGroup BUILDERS_MOD = new BuildersModItemGroup(ItemGroup.TABS.length,
            "builders_mod");

    public BuildersModItemGroup(int index, String label) {
        super(index, label);
    }

    @Override
    public ItemStack makeIcon() {
        return new ItemStack(ItemInit.MAME_INGOT.get());
    }

}
