load("//bazel/test/checkstyle:checkstyle_test.bzl", _checkstyle_test = "checkstyle_test")
load("//bazel/test/oregano:oregano_test.bzl", _oregano_test = "oregano_test")
load("//bazel/test/mypy:mypy_test.bzl", _mypy_test = "mypy_test")

oregano_test = _oregano_test
checkstyle_test = _checkstyle_test
mypy_test = _mypy_test
