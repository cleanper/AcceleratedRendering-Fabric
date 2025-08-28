package com.github.argon4w.acceleratedrendering.compat.immediatelyfast.mixins;

import lombok.EqualsAndHashCode;
import net.minecraft.client.renderer.RenderType;
import net.raphimc.immediatelyfast.feature.batching.BatchingBuffers;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Pseudo;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Pseudo
@EqualsAndHashCode
@Mixin(BatchingBuffers.WrappedRenderLayer.class)
public class WrappedRenderLayerMixin {

    @Unique private RenderType original;

    @Inject(
            method	= "<init>",
            at		= @At("TAIL")
    )
    public void saveOriginal(
            RenderType		renderLayer,
            Runnable		additionalStartAction,
            Runnable		additionalEndAction,
            CallbackInfo	ci
    ) {
        this.original = renderLayer;
    }
}
