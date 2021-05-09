load("//bazel/build:defs.bzl", "oregano_merge", "svelte_component")

exports_files(["main.js"])

#TODO ts_library
filegroup(
    name = "routes",
    srcs = [
        "FileOverview",
        "TreeOverview",
        "routes.js",
    ],
)

oregano_merge(
    name = "more_data",
    srcs = [
        "//engines/voide/src/buffer:buffer_utils.checkstyle.rgno",
        "//engines/voide/src/collision:collision.checkstyle.rgno",
        "//engines/voide/src/math:math.checkstyle.rgno",
    ],
)

genrule(
    name = "dynamic_data",
    srcs = [":more_data"],
    outs = ["data.json"],
    cmd = "cp $< $@",
)

#TODO json plugin

svelte_component(
    name = "App",
    srcs = [
        "main.js",
    ],
    component = "App.svelte",
    visibility = ["//visibility:public"],
    deps = [
        "routes",
        "@npm//svelte-spa-router",
    ],
)

svelte_component(
    name = "FileOverview",
    srcs = ["file.json"],
    component = "FileOverview.svelte",
    deps = [
        "FileHeader",
        "Line",
        "@npm//highlight.js",
    ],
)

svelte_component(
    name = "Line",
    component = "Line.svelte",
)

svelte_component(
    name = "FileHeader",
    component = "FileHeader.svelte",
    deps = ["BreadCrumb"],
)

svelte_component(
    name = "BreadCrumb",
    component = "BreadCrumb.svelte",
)

svelte_component(
    name = "TreeOverview",
    srcs = ["api.js"],
    component = "TreeOverview.svelte",
    deps = [
        "DirectoryEntry",
        "DirectoryHeader",
        ":dynamic_data",
    ],
)

svelte_component(
    name = "DirectoryEntry",
    component = "DirectoryEntry.svelte",
    deps = [
        "ErrorHue",
        "ErrorSnippet",
    ],
)

svelte_component(
    name = "DirectoryHeader",
    component = "DirectoryHeader.svelte",
)

svelte_component(
    name = "ErrorSnippet",
    component = "ErrorSnippet.svelte",
)

svelte_component(
    name = "ErrorHue",
    component = "ErrorHue.svelte",
)

"""
Closure experiment 
load("filter.bzl", "add_module_root", "compile_to_es5", "filter_js_files")

filegroup(
    name = "errorsnippet_js",
    srcs = [":ErrorSnippet"],
    output_group = "js",
)

genrule(
    name = "files",
    srcs = ["@npm//svelte"],
    outs = ["he"],
    cmd = "echo $(SRCS)",
)

filter_js_files(
    name = "svelte_js",
    target = "@npm//svelte",
)

closure_js_library(
    name = "svelte",
    srcs = [":svelte_es5"],
    convention = "NONE",
    includes = ["."],
    suppress = [
        "JSC_VAR_MULTIPLY_DECLARED_ERROR",
        "JSC_JSDOC_MISSING_TYPE_WARNING",
        "JSC_USELESS_CODE",
        "JSC_UNDEFINED_VARIABLE",
        "JSC_UNKNOWN_EXPR_TYPE",
        "JSC_WRONG_ARGUMENT_COUNT",
        "JSC_STRICT_INEXISTENT_PROPERTY",
        "JSC_POSSIBLE_INEXISTENT_PROPERTY",
        "JSC_INEXISTENT_PROPERTY",
        "JSC_TYPE_MISMATCH",
        "JSC_INEXISTENT_PARAM",
        "JSC_UNRECOGNIZED_TYPE_ERROR",
        "JSC_SUSPICIOUS_SEMICOLON",
        "checkTypes",
        "lintChecks",
    ],
)

add_module_root(
    name = "svelte_2",
    module_root = "tools/oregano/front/src",
    target = ":svelte",
)

closure_js_library(
    name = "error",
    srcs = [":errorsnippet_js"],
    includes = ["."],
    deps = [":svelte"],
)

compile_to_es5(
    name = "svelte_es5",
    target = ":svelte_js",
)

babel(
    name = "svelte_es3",
    args = [
        "$(locations :svelte_js)",
        "--config-file",
        "$(location es5.babelrc)",
        "--out-dir",
        "$(@D)",
    ],
    data = [
        "es5.babelrc",
        ":svelte_js",
        "@npm//@babel/preset-env",
    ],
    output_dir = True,
)

closure_js_binary(
    name = "test_error",
    compilation_level = "SIMPLE",
    deps = [":error"],
)

closure_js_library(
    name = "importing",
    srcs = ["temp.js"],
    convention = "NONE",
    includes = ["."],
    suppress = [
        "CR_NOT_PROVIDED",
        "JSC_VAR_MULTIPLY_DECLARED_ERROR",
    ],
    deps = [":svelte"],
)

closure_js_binary(
    name = "comp_importing",
    #includes = ["."],
    deps = [":importing"],
)

filter_js_files(
    name = "bundle_js",
    target = ":oregano_bundle",
)

closure_js_library(
    name = "bundle_closure",
    srcs = [":bundle_js"],
    convention = "NONE",
)

closure_js_binary(
    name = "bundle_compiled",
    entry_points = ["oregano_bundle.js"],
    deps = [":bundle_closure"],
)
"""
