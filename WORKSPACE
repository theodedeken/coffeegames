load("@bazel_tools//tools/build_defs/repo:http.bzl", "http_archive", "http_file")

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
