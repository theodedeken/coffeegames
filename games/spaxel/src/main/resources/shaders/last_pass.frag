#version 330 core

layout (location = 0) out vec4 color;

in vec2 pass_tex_coord;

uniform sampler2D tex_sampler;

void main()
{
    color = texture(tex_sampler, pass_tex_coord);
    if (color == vec4(0.0,0.0,0.0,1.0)) {
        color.w = 0.0;
    }
}