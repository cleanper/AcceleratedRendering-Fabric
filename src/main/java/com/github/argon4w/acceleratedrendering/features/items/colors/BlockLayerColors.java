package com.github.argon4w.acceleratedrendering.features.items.colors;

import com.github.argon4w.acceleratedrendering.features.items.mixins.accessors.BlockColorsAccessor;
import net.minecraft.client.Minecraft;
import net.minecraft.client.color.block.BlockColor;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.block.state.BlockState;

public class BlockLayerColors implements ILayerColors {

    private final BlockState blockState;
    private final BlockColor blockColor;

    public BlockLayerColors(BlockState blockState) {
        this.blockState = blockState;

        int blockId = BuiltInRegistries.BLOCK.getId(blockState.getBlock());
        BlockColor color = ((BlockColorsAccessor) Minecraft.getInstance().getBlockColors())
                .getBlockColors()
                .byId(blockId);

        this.blockColor = color != null ? color : EmptyBlockColor.INSTANCE;
    }

    @Override
    public int getColor(int layer) {
        return blockColor.getColor(blockState, null, null, layer);
    }
}
