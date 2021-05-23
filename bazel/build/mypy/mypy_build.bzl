load("//bazel/build/mypy:aspects.bzl", "PySourceFilesInfo", "PyTypeInfo", "python_source_files", "python_typed_deps")
load("//bazel/build/oregano:providers.bzl", "OreganoInfo")

def _mypy_build_impl(ctx):
    oregano_output = ctx.actions.declare_file(ctx.label.name + ".rgno")

    input_files = ctx.attr.label[PySourceFilesInfo].files
    all_inputs = ctx.attr.label[PyInfo].transitive_sources
    typed_deps = ctx.attr.label[PyTypeInfo].imports

    args = ctx.actions.args()
    for input_file in input_files:
        args.add(input_file.path)
    if ctx.attr.configuration_file != None:
        config_file = ctx.attr.configuration_file.files.to_list()[0]
        all_inputs = depset([config_file], transitive = [all_inputs])
        args.add("--config-file")
        args.add(config_file.path)

    ctx.actions.run(
        mnemonic = "MypyRun",
        inputs = all_inputs,
        outputs = [oregano_output],
        progress_message = "Running Mypy on %s" % ctx.attr.label.label.name,
        executable = ctx.attr._mypy_runner.files_to_run.executable,
        arguments = [args],
        env = {
            "OREGANO_OUTPUT": oregano_output.path,
            "MYPYPATH": ":".join(typed_deps),
        },
    )

    return [
        DefaultInfo(files = depset([oregano_output])),
        OreganoInfo(
            input_files = input_files,
            output_file = oregano_output,
        ),
    ]

mypy_build = rule(
    implementation = _mypy_build_impl,
    attrs = {
        "label": attr.label(mandatory = True, aspects = [python_typed_deps, python_source_files]),
        "configuration_file": attr.label(
            allow_single_file = True,
        ),
        "_mypy_runner": attr.label(
            cfg = "exec",
            executable = True,
            default = Label("//bazel/build/mypy:mypy_runner"),
        ),
    },
)
