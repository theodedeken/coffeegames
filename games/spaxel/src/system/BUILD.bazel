load("//bazel:macros.bzl", "cg_java_library")

package(default_visibility = ["//games/spaxel:__subpackages__"])

#TODO refactor
cg_java_library(
    name = "system",
    srcs = glob(["*.java"]),
    deps = [
        "//games/spaxel/src/engine",
        "//games/spaxel/src/entity",
    ],
)

cg_java_library(
    name = "system_type",
    srcs = ["SystemType.java"],
)

filegroup(
    name = "files",
    srcs = glob(["*.java"]),
)
