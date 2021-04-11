load("//bazel:macros.bzl", "cg_java_library")

package(default_visibility = ["//games/spaxel:__subpackages__"])

cg_java_library(
    name = "render",
    srcs = glob(["*.java"]),
    deps = [
        "//games/spaxel/src/graphics/animation",
        "//games/spaxel/src/graphics/buffer:master_buffer",
        "//games/spaxel/src/graphics/buffer:render_job",
        "//games/spaxel/src/graphics/buffer:render_layer",
        "//games/spaxel/src/graphics/texture:renderable",
        "//games/spaxel/src/math",
        "//games/spaxel/src/ui/state",
        "//games/spaxel/src/ui/styles",
    ],
)
