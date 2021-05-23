load("//bazel/test:defs.bzl", "mypy_test")

def rgno_py_binary(name, **kwargs):
    native.py_binary(
        name = name,
        **kwargs
    )
    mypy_test(
        name = "%s.mypy" % name,
        label = name,
        configuration_file = "//bazel:mypy.ini",
    )
