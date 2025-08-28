package com.github.argon4w.acceleratedrendering.core.backends;

import static org.lwjgl.opengl.GL46.*;

public class VertexArray {

	private final int vaoHandle;

	public VertexArray() {
		this.vaoHandle = glCreateVertexArrays();
	}

	public void bindVertexArray() {
		glBindVertexArray(vaoHandle);
	}

	public void unbindVertexArray() {
		glBindVertexArray(0);
	}

	public void delete() {
		glDeleteVertexArrays(vaoHandle);
	}
}
