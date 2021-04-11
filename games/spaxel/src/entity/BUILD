load("//bazel:macros.bzl", "cg_java_library")

package(default_visibility = ["//games/spaxel:__subpackages__"])

cg_java_library(
    name = "entity",
    srcs = glob(["**/*.java"]),
    deps = [
        "//games/spaxel/src:constants",
        "//games/spaxel/src/collision",
        "//games/spaxel/src/graphics/buffer:render_job",
        "//games/spaxel/src/graphics/buffer:render_layer",
        "//games/spaxel/src/graphics/texture",
        "//games/spaxel/src/graphics/texture:renderable",
        "//games/spaxel/src/input",
        "//games/spaxel/src/math",
        "//games/spaxel/src/util:spaxel_random",
        "//games/spaxel/src/util:sprite_data_util",
        "@maven//:com_fasterxml_jackson_core_jackson_annotations",
    ],
)

filegroup(
    name = "files",
    srcs = glob(["**/*.java"]),
)
