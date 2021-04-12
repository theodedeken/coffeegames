JavaSourceFilesInfo = provider(
    doc = "Provider containing the java source files of a target",
    fields = {
        "files": "java source files",
    },
)

# This aspect is responsible for collecting Java sources for target
# When applied to target, `JavaSourceFiles` struct will be attached to
# target info
def _java_source_files_impl(target, ctx):
    files = []
    if hasattr(ctx.rule.attr, "srcs"):
        for src in ctx.rule.attr.srcs:
            for file in src.files.to_list():
                if file.extension == "java":
                    files.append(file)
    return [JavaSourceFilesInfo(files = files)]

java_source_files = aspect(
    implementation = _java_source_files_impl,
)

def _checkstyle_lint_impl(ctx):
    exe = ctx.outputs.executable

    config_file = ctx.attr._configuration_file.files.to_list()[0]

    ctx.actions.expand_template(
        output = exe,
        template = ctx.file._script,
        is_executable = True,
        substitutions = {
            "{checkstyle}": ctx.attr._checkstyle_runner.files_to_run.executable.short_path,
            "{config_file}": config_file.short_path,
            "{files}": " ".join([f.short_path for f in ctx.attr.label[JavaSourceFilesInfo].files]),
        },
    )
    runfiles = ctx.runfiles(
        files = ctx.attr.label[JavaSourceFilesInfo].files + [config_file],
        collect_data = True,
    )
    runfiles = runfiles.merge(ctx.attr._checkstyle_runner[DefaultInfo].default_runfiles)

    # This is needed to make sure the output file of myrule is visible to the
    # resulting instantiated script.
    return DefaultInfo(
        executable = exe,
        runfiles = runfiles,
    )

checkstyle_test = rule(
    implementation = _checkstyle_lint_impl,
    attrs = {
        "label": attr.label(mandatory = True, aspects = [java_source_files]),
        "_configuration_file": attr.label(allow_single_file = True, default = Label("//bazel/checkstyle:checks.xml")),
        "_script": attr.label(allow_single_file = True, default = Label("//bazel/checkstyle:checkstyle_script.sh")),
        "_checkstyle_runner": attr.label(default = Label("//bazel/checkstyle:checkstyle_runner")),
    },
    test = True,
)
