load("//bazel:macros.bzl", "cg_java_library")

package(default_visibility = ["//games/spaxel:__subpackages__"])

cg_java_library(
    name = "collision",
    srcs = glob(["*.java"]),
    deps = [
        "//games/spaxel/src/math",
        "@maven//:com_fasterxml_jackson_core_jackson_annotations",
    ],
)
