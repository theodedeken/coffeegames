load("//bazel:macros.bzl", "cg_java_library")

package(default_visibility = ["//games/spaxel:__subpackages__"])

cg_java_library(
    name = "debug_renderer",
    srcs = [
        "DebugRenderer.java",
    ],
    deps = [
        "//engines/voide/src/math",
        "//games/spaxel/src:constants",
        "//games/spaxel/src/engine",
        "//games/spaxel/src/engine:resources",
        "//games/spaxel/src/entity",
        "//games/spaxel/src/graphics/buffer:master_buffer",
        "//games/spaxel/src/graphics/buffer:render_job",
        "//games/spaxel/src/graphics/buffer:render_layer",
        "//games/spaxel/src/graphics/texture:renderable",
        "//games/spaxel/src/logger",
        "//games/spaxel/src/system",
        "//games/spaxel/src/ui/elements:element",
    ],
)

cg_java_library(
    name = "texture_util",
    srcs = [
        "TextureUtil.java",
    ],
    deps = [
        "//engines/voide/src/buffer:buffer_utils",
        "@maven//:org_lwjgl_lwjgl_opengl",
    ],
)

cg_java_library(
    name = "gl_util",
    srcs = [
        "GLUtil.java",
    ],
    deps = [
        "//games/spaxel/src:constants",
        "@maven//:org_lwjgl_lwjgl",
        "@maven//:org_lwjgl_lwjgl_glfw",
        "@maven//:org_lwjgl_lwjgl_opengl",
    ],
)

cg_java_library(
    name = "spaxel_random",
    srcs = [
        "SpaxelRandom.java",
    ],
)

cg_java_library(
    name = "matrix_util",
    srcs = [
        "MatrixUtil.java",
    ],
    deps = [
        "//engines/voide/src/math",
    ],
)

cg_java_library(
    name = "shader_utils",
    srcs = [
        "ShaderUtils.java",
    ],
    deps = [
        ":file_to_string_loader",
        "@maven//:org_lwjgl_lwjgl_opengl",
    ],
)

cg_java_library(
    name = "file_to_string_loader",
    srcs = [
        "FileToStringLoader.java",
    ],
)

cg_java_library(
    name = "sprite_data_util",
    srcs = [
        "SpriteDataUtil.java",
    ],
    deps = [
        ":spaxel_random",
        "//engines/voide/src/math",
        "//games/spaxel/src/graphics/texture",
        "//games/spaxel/src/graphics/texture:texture_part",
    ],
)
