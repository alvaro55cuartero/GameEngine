#version 330 core

layout (location = 0) in vec3 position;
layout (location = 1) in vec2 textcoord;

uniform mat4 viewmatrix;
uniform mat4 transformationmatrix;
uniform mat4 projectionmatrix;

out vec2 passTextureCoords;

void main() {

	gl_Position = projectionmatrix * viewmatrix * transformationmatrix * vec4(position, 1.0);
	passTextureCoords = textcoord;
	

}