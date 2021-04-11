load("//bazel:macros.bzl", "cg_java_library")

package(default_visibility = ["//games/spaxel:__subpackages__"])

cg_java_library(
    name = "sound",
    srcs = glob(["*.java"]),
    deps = [
        "//games/spaxel/src/engine:gamestate",
        "//games/spaxel/src/util:spaxel_random",
    ],
)
