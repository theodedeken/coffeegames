load("//bazel:macros.bzl", "cg_java_library")

package(default_visibility = ["//games/spaxel:__subpackages__"])

cg_java_library(
    name = "packed_texture",
    srcs = ["PackedTexture.java"],
    deps = [
        ":renderable",
        ":texture",
        ":texture_node",
        "//games/spaxel/src:constants",
        "//games/spaxel/src/graphics/buffer:render_job",
        "//games/spaxel/src/math",
        "//games/spaxel/src/util:texture_util",
    ],
)

cg_java_library(
    name = "renderable",
    srcs = ["Renderable.java"],
    deps = [
        "//games/spaxel/src/graphics/buffer:render_job",
        "//games/spaxel/src/math",
    ],
)

cg_java_library(
    name = "texture",
    srcs = ["Texture.java"],
    deps = [
        ":renderable",
        "//games/spaxel/src:constants",
        "//games/spaxel/src/graphics/buffer:render_job",
        "//games/spaxel/src/math",
    ],
)

cg_java_library(
    name = "color_box",
    srcs = ["ColorBox.java"],
    deps = [
        ":renderable",
        "//games/spaxel/src/graphics/buffer:render_job",
        "//games/spaxel/src/math",
        "@maven//:com_fasterxml_jackson_core_jackson_annotations",
    ],
)

cg_java_library(
    name = "texture_part",
    srcs = ["TexturePart.java"],
    deps = [
        ":texture",
        "//games/spaxel/src/math",
    ],
)

cg_java_library(
    name = "texture_node",
    srcs = ["TextureNode.java"],
    deps = [
        ":texture",
        "//games/spaxel/src/math",
    ],
)
