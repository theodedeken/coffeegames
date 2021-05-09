load("//bazel/build/checkstyle:checkstyle_build.bzl", _checkstyle_build = "checkstyle_build")
load("//bazel/build/svelte:svelte.bzl", _svelte_component = "svelte_component")
load("//bazel/build/oregano:oregano_merge.bzl", _oregano_merge = "oregano_merge")
load("//bazel/build/oregano:oregano.bzl", _oregano = "oregano")

checkstyle_build = _checkstyle_build
svelte_component = _svelte_component
oregano_merge = _oregano_merge
oregano = _oregano
