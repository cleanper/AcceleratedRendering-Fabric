package com.github.argon4w.acceleratedrendering.features.items.colors;

import com.github.argon4w.acceleratedrendering.features.items.mixins.accessors.ItemColorsAccessor;
import com.github.argon4w.acceleratedrendering.features.items.mixins.accessors.MinecraftAccessor;
import net.minecraft.client.Minecraft;
import net.minecraft.client.color.item.ItemColor;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

public class ItemLayerColors implements ILayerColors {

    private final ItemStack itemStack;
    private final ItemColor itemColor;

    public ItemLayerColors(ItemStack itemStack) {
        this.itemStack = itemStack;

        var itemColors = ((MinecraftAccessor) Minecraft.getInstance()).getItemColors();
        var mapper = ((ItemColorsAccessor) itemColors).getItemColors();

        int itemId = Item.getId(itemStack.getItem());
        ItemColor color = mapper.byId(itemId);
        this.itemColor = color != null ? color : EmptyItemColor.INSTANCE;
    }

    @Override
    public int getColor(int layer) {
        return itemColor.getColor(itemStack, layer);
    }
}
