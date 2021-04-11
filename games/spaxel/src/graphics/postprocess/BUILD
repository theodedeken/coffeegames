load("//bazel:macros.bzl", "cg_java_library")

package(default_visibility = ["//games/spaxel:__subpackages__"])

cg_java_library(
    name = "postprocess",
    srcs = glob(["*.java"]),
    deps = [
        "//games/spaxel/src:constants",
        "//games/spaxel/src/graphics/geometry",
        "//games/spaxel/src/graphics/shaders",
        "//games/spaxel/src/math",
        "@maven//:org_lwjgl_lwjgl_opengl",
    ],
)
