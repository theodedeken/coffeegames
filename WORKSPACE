workspace(
    name = "coffeegames",
    managed_directories = {"@npm": ["tools/oregano/front/node_modules"]},
)

load("@bazel_tools//tools/build_defs/repo:http.bzl", "http_archive", "http_file")

'''TODO start using source version 16 once bazel 4.1.0 is releasedW
load("@bazel_tools//tools/jdk:remote_java_repository.bzl", "remote_java_repository")
load("@bazel_tools//tools/build_defs/repo:utils.bzl", "maybe")

maybe(
    remote_java_repository,
    name = "remotejdk16_linux",
    exec_compatible_with = [
        "@platforms//os:linux",
        "@platforms//cpu:x86_64",
    ],
    sha256 = "236b5ea97aff3cb312e743848d7efa77faf305170e41371a732ca93c1b797665",
    strip_prefix = "zulu16.28.11-ca-jdk16.0.0-linux_x64",
    urls = [
        "https://mirror.bazel.build/cdn.azul.com/zulu/bin/zulu16.28.11-ca-jdk16.0.0-linux_x64.tar.gz",
        "https://cdn.azul.com/zulu/bin/zulu16.28.11-ca-jdk16.0.0-linux_x64.tar.gz",
    ],
    version = "16",
)'''

RULES_JVM_EXTERNAL_TAG = "4.0"

RULES_JVM_EXTERNAL_SHA = "31701ad93dbfe544d597dbe62c9a1fdd76d81d8a9150c2bf1ecf928ecdf97169"

OPERATING_SYSTEM = "linux"  # TODO select statement

http_archive(
    name = "rules_jvm_external",
    sha256 = RULES_JVM_EXTERNAL_SHA,
    strip_prefix = "rules_jvm_external-%s" % RULES_JVM_EXTERNAL_TAG,
    url = "https://github.com/bazelbuild/rules_jvm_external/archive/%s.zip" % RULES_JVM_EXTERNAL_TAG,
)

load("@rules_jvm_external//:defs.bzl", "maven_install")
load("@rules_jvm_external//:specs.bzl", "maven")

# FIXME temporary variables
LWJGL_VERSION = "3.2.1"

platform = "natives-%s" % OPERATING_SYSTEM

# TODO set java source 11 in toolchain
maven_install(
    artifacts = [
        "com.fasterxml.jackson.core:jackson-core:2.7.3",
        "com.fasterxml.jackson.core:jackson-annotations:2.7.3",
        "com.fasterxml.jackson.core:jackson-databind:2.7.3",
        "com.fasterxml.jackson.dataformat:jackson-dataformat-xml:2.7.3",
        "com.fasterxml.jackson.dataformat:jackson-dataformat-yaml:2.3.0",
        "junit:junit:4.12",
        "org.lwjgl:lwjgl:%s" % LWJGL_VERSION,
        "org.lwjgl:lwjgl-assimp:%s" % LWJGL_VERSION,
        "org.lwjgl:lwjgl-bgfx:%s" % LWJGL_VERSION,
        "org.lwjgl:lwjgl-egl:%s" % LWJGL_VERSION,
        "org.lwjgl:lwjgl-glfw:%s" % LWJGL_VERSION,
        "org.lwjgl:lwjgl-jawt:%s" % LWJGL_VERSION,
        "org.lwjgl:lwjgl-jemalloc:%s" % LWJGL_VERSION,
        "org.lwjgl:lwjgl-lmdb:%s" % LWJGL_VERSION,
        "org.lwjgl:lwjgl-nanovg:%s" % LWJGL_VERSION,
        "org.lwjgl:lwjgl-nfd:%s" % LWJGL_VERSION,
        "org.lwjgl:lwjgl-nuklear:%s" % LWJGL_VERSION,
        "org.lwjgl:lwjgl-openal:%s" % LWJGL_VERSION,
        "org.lwjgl:lwjgl-opencl:%s" % LWJGL_VERSION,
        "org.lwjgl:lwjgl-opengl:%s" % LWJGL_VERSION,
        "org.lwjgl:lwjgl-opengles:%s" % LWJGL_VERSION,
        "org.lwjgl:lwjgl-openvr:%s" % LWJGL_VERSION,
        "org.lwjgl:lwjgl-ovr:%s" % LWJGL_VERSION,
        "org.lwjgl:lwjgl-par:%s" % LWJGL_VERSION,
        "org.lwjgl:lwjgl-sse:%s" % LWJGL_VERSION,
        "org.lwjgl:lwjgl-stb:%s" % LWJGL_VERSION,
        "org.lwjgl:lwjgl-tinyexr:%s" % LWJGL_VERSION,
        "org.lwjgl:lwjgl-tinyfd:%s" % LWJGL_VERSION,
        "org.lwjgl:lwjgl-vulkan:%s" % LWJGL_VERSION,
        "org.lwjgl:lwjgl-xxhash:%s" % LWJGL_VERSION,
        "org.lwjgl:lwjgl-yoga:%s" % LWJGL_VERSION,
        "org.lwjgl:lwjgl:%s:%s" % (LWJGL_VERSION, platform),
        "org.lwjgl:lwjgl-assimp:%s:%s" % (LWJGL_VERSION, platform),
        "org.lwjgl:lwjgl-bgfx:%s:%s" % (LWJGL_VERSION, platform),
        "org.lwjgl:lwjgl-glfw:%s:%s" % (LWJGL_VERSION, platform),
        "org.lwjgl:lwjgl-jemalloc:%s:%s" % (LWJGL_VERSION, platform),
        "org.lwjgl:lwjgl-lmdb:%s:%s" % (LWJGL_VERSION, platform),
        "org.lwjgl:lwjgl-nanovg:%s:%s" % (LWJGL_VERSION, platform),
        "org.lwjgl:lwjgl-nfd:%s:%s" % (LWJGL_VERSION, platform),
        "org.lwjgl:lwjgl-nuklear:%s:%s" % (LWJGL_VERSION, platform),
        "org.lwjgl:lwjgl-openal:%s:%s" % (LWJGL_VERSION, platform),
        "org.lwjgl:lwjgl-opengl:%s:%s" % (LWJGL_VERSION, platform),
        "org.lwjgl:lwjgl-opengles:%s:%s" % (LWJGL_VERSION, platform),
        "org.lwjgl:lwjgl-openvr:%s:%s" % (LWJGL_VERSION, platform),
        "org.lwjgl:lwjgl-ovr:%s:%s" % (LWJGL_VERSION, platform),
        "org.lwjgl:lwjgl-par:%s:%s" % (LWJGL_VERSION, platform),
        "org.lwjgl:lwjgl-sse:%s:%s" % (LWJGL_VERSION, platform),
        "org.lwjgl:lwjgl-stb:%s:%s" % (LWJGL_VERSION, platform),
        "org.lwjgl:lwjgl-tinyexr:%s:%s" % (LWJGL_VERSION, platform),
        "org.lwjgl:lwjgl-tinyfd:%s:%s" % (LWJGL_VERSION, platform),
        "org.lwjgl:lwjgl-xxhash:%s:%s" % (LWJGL_VERSION, platform),
        "org.lwjgl:lwjgl-yoga:%s:%s" % (LWJGL_VERSION, platform),
        maven.artifact(
            group = "org.lwjgl",
            artifact = "lwjgl",
            version = LWJGL_VERSION,
            classifier = platform,
        ),
        maven.artifact(
            group = "org.lwjgl",
            artifact = "lwjgl-glfw",
            version = LWJGL_VERSION,
            classifier = platform,
        ),
        maven.artifact(
            group = "org.lwjgl",
            artifact = "lwjgl-opengl",
            version = LWJGL_VERSION,
            classifier = platform,
        ),
    ],
    repositories = [
        # Private repositories are supported through HTTP Basic auth
        "https://maven.google.com",
        "https://repo1.maven.org/maven2",
    ],
)

http_file(
    name = "checkstyle",
    downloaded_file_path = "checkstyle.jar",
    sha256 = "cd6ea08d1bb96041f4e3109bb16db415e15f98183e8be696dd6a558814228172",
    urls = ["https://github.com/checkstyle/checkstyle/releases/download/checkstyle-8.41.1/checkstyle-8.41.1-all.jar"],
)

# Rules nodejs
http_archive(
    name = "build_bazel_rules_nodejs",
    sha256 = "1134ec9b7baee008f1d54f0483049a97e53a57cd3913ec9d6db625549c98395a",
    urls = ["https://github.com/bazelbuild/rules_nodejs/releases/download/3.4.0/rules_nodejs-3.4.0.tar.gz"],
)

load("@build_bazel_rules_nodejs//:index.bzl", "yarn_install")

yarn_install(
    name = "npm",
    package_json = "//tools/oregano/front:package.json",
    yarn_lock = "//tools/oregano/front:yarn.lock",
)

# Closure rules
http_archive(
    name = "io_bazel_rules_closure",
    patches = ["//:rules_closure_roots_fix.patch"],
    sha256 = "7d206c2383811f378a5ef03f4aacbcf5f47fd8650f6abbc3fa89f3a27dd8b176",
    strip_prefix = "rules_closure-0.10.0",
    urls = [
        "https://mirror.bazel.build/github.com/bazelbuild/rules_closure/archive/0.10.0.tar.gz",
        "https://github.com/bazelbuild/rules_closure/archive/0.10.0.tar.gz",
    ],
)

load("@io_bazel_rules_closure//closure:repositories.bzl", "rules_closure_dependencies", "rules_closure_toolchains")

rules_closure_dependencies()

rules_closure_toolchains()
