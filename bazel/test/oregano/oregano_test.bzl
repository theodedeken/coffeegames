def _oregano_test_impl(ctx):
    oregano_file = ctx.attr.checks.files.to_list()[0]
    wrapper = ctx.actions.declare_file(ctx.label.name + ".wrapper")

    # Write wrapper script
    ctx.actions.write(
        output = wrapper,
        content = "%s %s" % (ctx.attr._check.files_to_run.executable.short_path, oregano_file.short_path),
        is_executable = True,
    )

    runfiles = ctx.runfiles(
        files = [oregano_file],
        transitive_files = ctx.attr._check[DefaultInfo].default_runfiles.files,
    )

    return DefaultInfo(
        executable = wrapper,
        runfiles = runfiles,
    )

oregano_test = rule(
    implementation = _oregano_test_impl,
    attrs = {
        "checks": attr.label(mandatory = True),
        "_check": attr.label(
            cfg = "exec",
            executable = True,
            default = Label("//bazel/test/oregano:oregano_test"),
        ),
    },
    test = True,
)
