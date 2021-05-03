load("@build_bazel_rules_nodejs//:index.bzl", "nodejs_binary")

nodejs_binary(
    name = "svelte_compile",
    data = [
        ":svelte_compile.js",
        "@npm//@bazel/worker",
        "@npm//svelte",
        "@npm//svelte-preprocess",
    ],
    entry_point = ":svelte_compile.js",
    visibility = ["//visibility:public"],
)
