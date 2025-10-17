#version 330 core

layout (location = 0) out vec4 color;

in vec2 pass_tex_coord;

uniform sampler2D tex_sampler;
uniform float resolution;
uniform float radius;
uniform int size;
uniform vec2 dir;

void main()
{
    vec4 sum = vec4(0.0);
    float blur = radius/resolution;
    float div = 1.0/size;

    for (int i = -(size/2); i < size/2 + 1; i++){
        vec2 offset = i * blur * dir;
        sum += texture(tex_sampler, pass_tex_coord + offset)*div;
    }

    color = sum;
}