load("//bazel/build/checkstyle:aspects.bzl", "JavaSourceFilesInfo", "java_source_files")

def _checkstyle_build_impl(ctx):
    output_file = ctx.actions.declare_file(ctx.label.name + ".output")
    exit_code_file = ctx.actions.declare_file(ctx.label.name + ".exit_code")
    prefix_file = ctx.actions.declare_file(ctx.label.name + ".file_prefix")
    config_file = ctx.attr._configuration_file.files.to_list()[0]
    checkstyle_script = ctx.actions.declare_file(ctx.label.name + ".script")

    # Prepare checkstyle script
    ctx.actions.expand_template(
        template = ctx.attr._checkstyle_build_template.files.to_list()[0],
        output = checkstyle_script,
        substitutions = {
            "{files}": " ".join([f.short_path for f in ctx.attr.label[JavaSourceFilesInfo].files]),
            "{checkstyle}": ctx.attr._checkstyle_runner.files_to_run.executable.path,
            "{config_file}": config_file.path,
            "{exit_code}": exit_code_file.path,
            "{output}": output_file.path,
            "{file_prefix}": prefix_file.path,
        },
        is_executable = True,
    )

    input_files = depset(
        ctx.attr.label[JavaSourceFilesInfo].files + [config_file],
    )

    # Run checkstyle to create a file of errors and the exit code
    ctx.actions.run(
        mnemonic = "CheckstyleRun",
        inputs = input_files,
        outputs = [output_file, exit_code_file, prefix_file],
        tools = ctx.attr._checkstyle_runner[DefaultInfo].default_runfiles.files,
        progress_message = "Running Checkstyle on %s" % ctx.attr.label.label.name,
        executable = checkstyle_script,
    )

    oregano_output = ctx.actions.declare_file(ctx.label.name + ".rgno")
    args = ctx.actions.args()
    args.add(output_file.path)
    args.add(exit_code_file.path)
    args.add(prefix_file.path)
    args.add(oregano_output.path)

    # Convert the checkstyle output to the format of Oregano
    ctx.actions.run(
        mnemonic = "CheckstyleOutputToOregano",
        inputs = [output_file, exit_code_file, prefix_file],
        outputs = [oregano_output],
        progress_message = "Converting Checkstyle output of %s" % ctx.attr.label.label.name,
        executable = ctx.attr._format_converter.files_to_run.executable,
        arguments = [args],
    )

    return DefaultInfo(files = depset([oregano_output]))

checkstyle_build = rule(
    implementation = _checkstyle_build_impl,
    attrs = {
        "label": attr.label(mandatory = True, aspects = [java_source_files]),
        "_configuration_file": attr.label(
            #TODO: make this a configurable through some kind of toolchain
            allow_single_file = True,
            default = Label("//bazel/build/checkstyle:checks.xml"),
        ),
        "_checkstyle_runner": attr.label(
            cfg = "exec",
            executable = True,
            default = Label("//bazel/build/checkstyle:checkstyle_runner"),
        ),
        "_checkstyle_build_template": attr.label(
            allow_single_file = True,
            default = Label("//bazel/build/checkstyle:checkstyle_build.template.sh"),
        ),
        "_format_converter": attr.label(
            cfg = "exec",
            executable = True,
            default = Label("//bazel/build/checkstyle:checkstyle_xml_to_json"),
        ),
    },
)
