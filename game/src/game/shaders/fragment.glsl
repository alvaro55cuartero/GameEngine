#version 330 core

in vec2 passTextureCoords;

uniform sampler2D textureSampler;

out vec4 out_color;

void main() {
	vec4 out_color = texture(textureSampler, passTextureCoords);
}