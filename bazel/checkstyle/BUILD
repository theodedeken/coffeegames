java_import(
    name = "checkstyle_import",
    jars = ["@checkstyle//file"],
)

java_binary(
    name = "checkstyle_runner",
    main_class = "com.puppycrawl.tools.checkstyle.Main",
    visibility = ["//visibility:public"],
    runtime_deps = [":checkstyle_import"],
)

exports_files([
    "checks.xml",
    "checkstyle_script.sh",
])
