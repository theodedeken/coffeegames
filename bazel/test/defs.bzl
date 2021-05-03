load("//bazel/test/checkstyle:checkstyle_test.bzl", _checkstyle_test = "checkstyle_test")
load("//bazel/test/oregano:oregano_test.bzl", _oregano_test = "oregano_test")

oregano_test = _oregano_test
checkstyle_test = _checkstyle_test
