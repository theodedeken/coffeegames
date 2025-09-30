load("//bazel/test:defs.bzl", "checkstyle_test")

def cg_java_binary(**kwargs):
    native.java_binary(**kwargs)

def cg_java_library(**kwargs):
    name = kwargs["name"]
    native.java_library(**kwargs)
    # checkstyle_test(
    #     name = "%s.checkstyle" % name,
    #     label = name,
    # )

def cg_java_test(name, srcs, test_class, deps):
    native.java_test(
        name = name,
        srcs = srcs,
        use_testrunner = False,
        main_class = "org.junit.platform.console.ConsoleLauncher",
        args = ["--select-class", test_class],
        deps = deps + [
            "@maven//:org_junit_jupiter_junit_jupiter_api",
            "@maven//:org_junit_jupiter_junit_jupiter_engine",
            "@maven//:org_junit_jupiter_junit_jupiter_params",
            "@maven//:org_junit_platform_junit_platform_suite_api",
            "@maven//:org_apiguardian_apiguardian_api",
            "@maven//:org_opentest4j_opentest4j",
        ],
        runtime_deps = [
            "@maven//:org_junit_platform_junit_platform_commons",
            "@maven//:org_junit_platform_junit_platform_console",
            "@maven//:org_junit_platform_junit_platform_engine",
            "@maven//:org_junit_platform_junit_platform_launcher",
            "@maven//:org_junit_platform_junit_platform_suite_api",
        ],
    )
