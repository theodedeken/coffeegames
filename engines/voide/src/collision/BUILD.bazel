load("//bazel:macros.bzl", "cg_java_library")

package(default_visibility = ["//visibility:public"])

cg_java_library(
    name = "collision",
    srcs = glob(["*.java"]),
    deps = [
        "//engines/voide/src/math",
        "@maven//:com_fasterxml_jackson_core_jackson_annotations",
    ],
)
