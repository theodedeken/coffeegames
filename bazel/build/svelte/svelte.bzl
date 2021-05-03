"Implementation of the svelte rule"

load("@build_bazel_rules_nodejs//:providers.bzl", "run_node")

SvelteFilesInfo = provider("transitive_sources")

def get_transitive_srcs(srcs, deps):
    return depset(
        srcs,
        transitive = [dep[DefaultInfo].files for dep in deps],
    )

def _svelte_component(ctx):
    args = ctx.actions.args()

    args.use_param_file("@%s", use_always = True)
    args.set_param_file_format("multiline")

    args.add(ctx.file.component.path)
    args.add(ctx.outputs.js.path)
    args.add(ctx.outputs.css.path)

    run_node(
        ctx,
        execution_requirements = {"supports-workers": "1"},
        executable = "_svelte_bin",
        outputs = [ctx.outputs.js, ctx.outputs.css],
        inputs = [ctx.file.component],
        mnemonic = "SvelteCompile",
        arguments = [args],
    )

    trans_srcs = get_transitive_srcs(ctx.files.srcs + [ctx.outputs.js, ctx.outputs.css], ctx.attr.deps)

    return [
        SvelteFilesInfo(transitive_sources = trans_srcs),
        DefaultInfo(files = trans_srcs),
        OutputGroupInfo(
            js = depset([ctx.outputs.js]),
            css = depset([ctx.outputs.css]),
        ),
    ]

svelte_component = rule(
    implementation = _svelte_component,
    attrs = {
        "component": attr.label(allow_single_file = True),
        "deps": attr.label_list(),
        "srcs": attr.label_list(allow_files = True),
        "_svelte_bin": attr.label(
            default = Label("//bazel/build/svelte:svelte_compile"),
            executable = True,
            cfg = "host",
        ),
    },
    outputs = {
        "js": "%{name}.svelte.js",
        "css": "%{name}.css",
    },
)
