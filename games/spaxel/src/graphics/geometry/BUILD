load("//bazel:macros.bzl", "cg_java_library")

package(default_visibility = ["//games/spaxel:__subpackages__"])

cg_java_library(
    name = "geometry",
    srcs = glob(["*.java"]),
    deps = [
        "//games/spaxel/src/util:buffer_utils",
        "@maven//:org_lwjgl_lwjgl_opengl",
    ],
)
