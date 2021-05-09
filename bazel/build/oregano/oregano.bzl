load("//bazel/build/oregano:oregano_merge.bzl", "oregano_merge")

def oregano_run(name, deps):
    oregano_merge(
        name = name + ".data",
        srcs = deps,
        visibility = ["//visibility:public"],
    )

    # TODO more configureable
    native.alias(name = name, actual = "//tools/oregano/front:serve_oregano")

def oregano_runner(name, autoupdate):
    pass

def oregano_autoupdate(name):
    pass

def oregano(name, deps = [], autoupdate = True):
    oregano_runner(
        name = "%s.runner",
        autoupdate = autoupdate,
    )
    if autoupdate:
        oregano_autoupdate(
            name = "%s.autoupdate",
        )
    oregano_run(
        name = name + ".run",
        deps = deps,
    )
    """
    native.sh_binary(
        name = name,
        srcs = ["%s.runner"],
    )"""
