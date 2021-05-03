load("@build_bazel_rules_nodejs//:providers.bzl", "JSModuleInfo", "run_node")

def _filter_js_files(ctx):
    js = depset([f for f in ctx.attr.target.files.to_list() if f.extension == "js"])
    return [
        DefaultInfo(files = js),
        OutputGroupInfo(
            js = js,
        ),
    ]

def _compile_to_es5(ctx):
    ins = ctx.attr.target.files.to_list()
    outs = []

    for f in ins:
        args = ctx.actions.args()

        out_path = f.path.replace("external/npm/node_modules/", "")
        out = ctx.actions.declare_file(out_path)

        args.add(f.path)
        args.add("--out-file")
        args.add(out.path)
        args.add("--presets=@babel/preset-env")
        run_node(
            ctx,
            #execution_requirements = {"supports-workers": "1"},
            executable = "_babel",
            outputs = [out],
            inputs = [f],
            mnemonic = "BabelCompile",
            arguments = [args],
            tools = ctx.attr._preset[JSModuleInfo].sources.to_list(),
        )
        outs.append(out)

    return DefaultInfo(files = depset(outs))

def _add_module_root(ctx):
    info = ctx.attr.target
    lib = info.closure_js_library
    module_roots = lib.js_module_roots.to_list()

    new_module_roots = depset(module_roots + [ctx.attr.module_root])
    new_info = struct(
        exports = info.exports,
        closure_js_library = struct(
            info = lib.info,
            infos = lib.infos,
            ijs = lib.ijs,
            ijs_files = lib.ijs_files,
            srcs = lib.srcs,
            js_module_roots = new_module_roots,
            modules = lib.modules,
            descriptors = lib.descriptors,
            stylesheets = lib.stylesheets,
            has_closure_library = lib.has_closure_library,
        ),
    )

    return new_info

filter_js_files = rule(
    implementation = _filter_js_files,
    attrs = {
        "target": attr.label(),
    },
)

compile_to_es5 = rule(
    implementation = _compile_to_es5,
    attrs = {
        "target": attr.label(),
        "_babel": attr.label(
            default = Label("@npm//@babel/cli/bin:babel"),
            executable = True,
            cfg = "host",
        ),
        "_preset": attr.label(
            default = Label("@npm//@babel/preset-env"),
        ),
    },
)
add_module_root = rule(
    implementation = _add_module_root,
    attrs = {
        "target": attr.label(),
        "module_root": attr.string(),
    },
)
