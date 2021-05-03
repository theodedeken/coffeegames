load("@npm//@bazel/rollup:index.bzl", "rollup_bundle")
load("@npm//http-server:index.bzl", "http_server")
load("@build_bazel_rules_nodejs//:index.bzl", "pkg_web")

rollup_bundle(
    name = "oregano_bundle",
    config_file = "rollup.config.js",
    entry_point = "//tools/oregano/front/src:main.js",
    format = "iife",
    link_workspace_root = True,
    output_dir = True,
    sourcemap = "true",
    deps = [
        # dev deps
        "@npm//@rollup/plugin-commonjs",
        "@npm//@rollup/plugin-json",
        "@npm//@rollup/plugin-node-resolve",
        "@npm//rollup-plugin-css-only",
        "@npm//rollup-plugin-svelte",
        "@npm//svelte-preprocess",
    ] + [
        # source deps
        "//tools/oregano/front/src:App",
    ],
)

pkg_web(
    name = "oregano",
    srcs = [
        "favicon.png",
        "global.css",
        "index.html",
        ":oregano_bundle",
    ],
)

http_server(
    name = "serve_oregano",
    args = ["tools/oregano/front/oregano"],
    data = [":oregano"],
)

"""

concatjs_devserver(
    name = "oregano_devserver",
    # This is the path we'll request from the browser, see index.html
    serving_path = "/oregano_bundle.js",
    # The devserver can serve our static files too
    static_files = [
        "index.html",
        "global.css",
        "favicon.png",
        ":oregano_bundle",
    ],
    # We'll collect all the devmode JS sources from these TypeScript libraries
    deps = [":oregano_bundle"],
)
"""
