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
