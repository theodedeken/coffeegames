load("//bazel/checkstyle:checkstyle_lint.bzl", "checkstyle_test")

def cg_java_binary(**kwargs):
    native.java_binary(**kwargs)

def cg_java_library(**kwargs):
    name = kwargs["name"]
    checkstyle_test(
        name = "%s.checkstyle" % name,
        label = name,
    )
    native.java_library(**kwargs)
