load("//bazel/build/oregano:providers.bzl", "OreganoInfo")

def _oregano_merge_impl(ctx):
    args = ctx.actions.args()

    #args.use_param_file("@%s", use_always = True)
    #args.set_param_file_format("multiline")

    oregano_output = ctx.actions.declare_file(ctx.label.name + ".rgno")

    args.add(oregano_output.path)
    input_files = []
    rgno_files = []
    for src in ctx.attr.srcs:
        info = src[OreganoInfo]
        input_files += info.input_files
        args.add("--rgno")
        args.add(info.output_file.path)
        rgno_files.append(info.output_file)

    # Merge all rgno files
    ctx.actions.run(
        mnemonic = "OreganoMerge",
        inputs = rgno_files,
        outputs = [oregano_output],
        progress_message = "Merging oregano files",
        executable = ctx.attr._merger.files_to_run.executable,
        arguments = [args],
    )
    return [
        DefaultInfo(files = depset([oregano_output])),
        OreganoInfo(
            input_files = input_files,
            output_file = oregano_output,
        ),
    ]

oregano_merge = rule(
    implementation = _oregano_merge_impl,
    attrs = {
        "srcs": attr.label_list(mandatory = True),
        "_merger": attr.label(
            cfg = "exec",
            executable = True,
            default = Label("//bazel/build/oregano:oregano_merger"),
        ),
    },
)
