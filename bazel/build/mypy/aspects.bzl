PyTypeInfo = provider(
    doc = "Provider containing the import paths of typed dependencies of a target",
    fields = {
        "imports": "Mypy import paths",
    },
)

PySourceFilesInfo = provider(
    doc = "Provider containing the source files of a Python target",
    fields = {
        "files": "Source files",
    },
)

# TODO make sure the entire https://www.python.org/dev/peps/pep-0561/ is covered
def _python_typed_deps(target, ctx):
    imports = []
    if hasattr(ctx.rule.attr, "data"):
        for data_el in ctx.rule.attr.data:
            for data_file in data_el.files.to_list():
                if data_file.basename == "py.typed":
                    imports += import_paths(ctx, ctx.rule.attr.imports)
    if hasattr(ctx.rule.attr, "deps"):
        for dep in ctx.rule.attr.deps:
            imports += dep[PyTypeInfo].imports
    return [PyTypeInfo(imports = imports)]

def import_paths(ctx, imports):
    base = ctx.label.workspace_root + "/" + ctx.label.package
    out = []
    for import_path in imports:
        if import_path == ".":
            out.append(base)
        else:
            out.append(base + "/" + import_path)

    return out

def _python_source_files(target, ctx):
    files = []
    if hasattr(ctx.rule.attr, "srcs"):
        for src in ctx.rule.attr.srcs:
            for file in src.files.to_list():
                if file.extension == "py":
                    files.append(file)
    return [PySourceFilesInfo(files = files)]

python_typed_deps = aspect(
    implementation = _python_typed_deps,
    attr_aspects = ["deps"],
)

python_source_files = aspect(
    implementation = _python_source_files,
)
