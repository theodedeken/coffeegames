load("//bazel/checkstyle:checkstyle_lint.bzl", "checkstyle_two_stage")

def cg_java_binary(**kwargs):
    native.java_binary(**kwargs)

def cg_java_library(**kwargs):
    name = kwargs["name"]
    checkstyle_two_stage(
        name = "%s.checkstyle" % name,
        label = name,
    )
    native.java_library(**kwargs)
