load("//bazel:macros.bzl", "cg_java_library")

package(default_visibility = ["//games/spaxel:__subpackages__"])

cg_java_library(
    name = "render_job",
    srcs = ["RenderJob.java"],
    deps = ["//engines/voide/src/math"],
)

cg_java_library(
    name = "master_buffer",
    srcs = ["MasterBuffer.java"],
    deps = [
        ":render_job",
        ":render_layer",
        "//games/spaxel/src/graphics/texture:packed_texture",
    ],
)

cg_java_library(
    name = "layer_fbo",
    srcs = ["LayerFBO.java"],
    deps = [
        ":render_layer",
        "//games/spaxel/src/graphics/postprocess",
    ],
)

cg_java_library(
    name = "render_buffer",
    srcs = ["RenderBuffer.java"],
    deps = [
        ":render_job",
        "//engines/voide/src/buffer:buffer_utils",
    ],
)

cg_java_library(
    name = "render_layer",
    srcs = ["RenderLayer.java"],
)
