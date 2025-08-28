package com.github.argon4w.acceleratedrendering.core.mixins;

import com.github.argon4w.acceleratedrendering.core.CoreBuffers;
import com.github.argon4w.acceleratedrendering.core.CoreFeature;
import com.github.argon4w.acceleratedrendering.core.buffers.EmptyAcceleratedBufferSources;
import com.github.argon4w.acceleratedrendering.core.buffers.accelerated.IAcceleratedBufferSource;
import com.github.argon4w.acceleratedrendering.core.buffers.accelerated.IAccelerationHolder;
import com.github.argon4w.acceleratedrendering.core.buffers.accelerated.builders.AcceleratedBufferBuilder;
import com.github.argon4w.acceleratedrendering.core.buffers.accelerated.builders.IAcceleratedVertexConsumer;
import com.github.argon4w.acceleratedrendering.core.buffers.accelerated.renderers.IAcceleratedRenderer;
import com.github.argon4w.acceleratedrendering.core.programs.ComputeShaderProgramLoader;
import com.mojang.blaze3d.vertex.BufferBuilder;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.renderer.RenderType;
import org.joml.Matrix3f;
import org.joml.Matrix4f;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;

@Mixin(BufferBuilder.class)
public class BufferBuilderMixin implements IAccelerationHolder, IAcceleratedVertexConsumer {

	@Unique private IAcceleratedBufferSource	bufferSources = EmptyAcceleratedBufferSources.INSTANCE;
	@Unique private RenderType					renderType;
	@Unique private AcceleratedBufferBuilder	acceleration;

	@Unique
	@Override
	public VertexConsumer initAcceleration(RenderType renderType) {
		if (ComputeShaderProgramLoader.isProgramsLoaded()) {
			this.bufferSources	= renderType.isOutline() ? CoreBuffers.OUTLINE : CoreBuffers.getCoreBufferSources();
			this.renderType		= renderType;
			this.acceleration	= null;
		}

		return (VertexConsumer) this;
	}

	@Unique
	@Override
	public boolean isAccelerated() {
		return getAccelerated() != null;
	}

	@Unique
	@Override
	public <T> void doRender(
			IAcceleratedRenderer<T>	renderer,
			T						context,
			Matrix4f				transform,
			Matrix3f				normal,
			int						light,
			int						overlay,
			int						color
	) {
		getAccelerated().doRender(
				renderer,
				context,
				transform,
				normal,
				light,
				overlay,
				color
		);
	}

	@Unique
	public AcceleratedBufferBuilder getAccelerated() {
		if (		acceleration == null
				||	acceleration.isOutdated()
		) {
			acceleration = bufferSources.getBuffer(
					renderType,
					CoreFeature.getDefaultLayerBeforeFunction	(),
					CoreFeature.getDefaultLayerAfterFunction	(),
					CoreFeature.getDefaultLayer					()
			);
		}

		return acceleration;
	}
}
