#version 450

layout (location = 0) in vec4 position;
layout (location = 1) in vec2 tex_coord;
layout (location = 2) in vec4 trsc_comp;
layout (location = 3) in vec4 tex_offset_scale;
layout (location = 4) in vec4 sin_cos_alpha;

uniform mat4 projection_matrix;
uniform mat3 transformation_matrix = mat3(1.0);

out vec2 pass_tex_coord;
out vec4 pass_tex_offset_scale;
out vec2 pass_alpha;

void main()
{
    mat3 transformation = mat3(sin_cos_alpha.y, -sin_cos_alpha.x, 0,sin_cos_alpha.x,sin_cos_alpha.y, 0, trsc_comp.x, trsc_comp.y, 1);
    mat3 scale = mat3(trsc_comp.z, 0,0,0,trsc_comp.w,0,0,0,1);
    vec3 temp_pos = transformation *scale * vec3(position.xyw);

	gl_Position = projection_matrix * vec4(temp_pos.xy, 0, 1.0);
	gl_Position += vec4(-1,-1,0,0);
	pass_tex_coord = tex_coord;
	pass_tex_offset_scale = tex_offset_scale;
	pass_alpha = sin_cos_alpha.zw;
}