load("//bazel:macros.bzl", "cg_java_library")

package(default_visibility = ["//games/spaxel:__subpackages__"])

cg_java_library(
    name = "elements",
    srcs = glob(["*.java"]),
    deps = [
        "//games/spaxel/src/entity",
        "//games/spaxel/src/ui/elements:element",
    ],
)

filegroup(
    name = "files",
    srcs = glob(["*.java"]),
)
