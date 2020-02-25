#version 330 core

layout(location = 0) out vec4 color; 

in vec2 o_texcoord;

uniform sampler2D u_Texture;

void main() {

	color = texture(u_Texture, o_texcoord);
	//color = vec4(1,0,1,1);
}