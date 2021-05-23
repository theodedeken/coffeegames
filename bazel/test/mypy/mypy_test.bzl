load("//bazel/build/mypy:mypy_build.bzl", "mypy_build")
load("//bazel/test/oregano:oregano_test.bzl", "oregano_test")

def mypy_test(name, label, configuration_file = None):
    mypy_build(
        name = "%s.rgno" % name,
        label = label,
        configuration_file = configuration_file,
        visibility = ["//visibility:public"],
        tags = ["RGNO", "lang:python"],
    )
    oregano_test(
        name = name,
        checks = "%s.rgno" % name,
    )
