#version 330 core

layout (location = 0) in vec4 position;
layout (location = 1) in vec2 tex_coord;

out vec2 pass_tex_coord;

uniform mat4 projection_matrix;
uniform mat3 transformation_matrix = mat3(1.0);

void main()
{
	pass_tex_coord = tex_coord;
    vec3 temp_pos = mat3(2, 0,0,0,2,0,0,0,1) * vec3(position.xyw);

	gl_Position = vec4(temp_pos.xy, 0, 1.0);
}