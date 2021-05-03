load("//bazel/build/checkstyle:checkstyle_build.bzl", "checkstyle_build")
load("//bazel/test/oregano:oregano_test.bzl", "oregano_test")

def checkstyle_test(name, label):
    checkstyle_build(
        name = "%s.rgno" % name,
        label = label,
        visibility = ["//visibility:public"],
    )
    oregano_test(
        name = name,
        checks = "%s.rgno" % name,
    )
