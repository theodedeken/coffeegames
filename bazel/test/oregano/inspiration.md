# Warning output formats

## prettier

- line caret
- line number in bright black
- syntax highlighting

```
games/spaxel/resources/java/shaders/last_pass.frag[error] games/spaxel/resources/java/shaders/last_pass.frag: SyntaxError: Unexpected token (5:1)
[error]   3 | layout (location = 0) out vec4 color;
[error]   4 |
[error] > 5 | in vec2 pass_tex_coord;
[error]     | ^
[error]   6 |
[error]   7 | uniform sampler2D tex_sampler;
[error]   8 |
```
