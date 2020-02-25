#version 330 core

layout(location = 0) in vec3 a_Position;

uniform mat4 transformationMatrix;
uniform mat4 projectionMatrix;
uniform mat4 viewMatrix;

out vec3 v_Position;

void main() {

	v_Position = a_Position;
	gl_Position = projectionMatrix *  viewMatrix  * transformationMatrix * vec4(a_Position, 1.0);

}