load("//bazel:macros.bzl", "cg_java_library")

package(default_visibility = ["//games/spaxel:__subpackages__"])

cg_java_library(
    name = "engine",
    srcs = ["Engine.java"],
    deps = [
        ":gamestate",
        ":resources",
        "//games/spaxel/src/input",
        "//games/spaxel/src/logger",
        "//games/spaxel/src/sound",
        "//games/spaxel/src/ui/elements:ui",
        "//games/spaxel/src/ui/elements:ui_type",
    ],
)

cg_java_library(
    name = "gamestate",
    srcs = ["GameState.java"],
    deps = [
        "//engines/voide/src/math",
        "//games/spaxel/src:constants",
    ],
)

cg_java_library(
    name = "item_catalogue",
    srcs = [
        "ItemCatalogue.java",
        "ItemProperties.java",
        "Rarety.java",
    ],
    deps = [
        "//games/spaxel/src/entity",
        "//games/spaxel/src/util:spaxel_random",
    ],
)

cg_java_library(
    name = "resources",
    srcs = ["Resources.java"],
    deps = [
        "//engines/voide/src/collision",
        "//games/spaxel/src:constants",
        "//games/spaxel/src/factories/entities",
        "//games/spaxel/src/graphics/animation",
        "//games/spaxel/src/graphics/texture",
        "//games/spaxel/src/graphics/texture:color_box",
        "//games/spaxel/src/graphics/texture:packed_texture",
        "//games/spaxel/src/graphics/texture:renderable",
        "//games/spaxel/src/graphics/texture:texture_part",
        "//games/spaxel/src/input",
        "//games/spaxel/src/loaders",
        "//games/spaxel/src/sound",
        "//games/spaxel/src/ui/elements:ui",
        "//games/spaxel/src/ui/elements:ui_type",
        "//games/spaxel/src/util:texture_util",
    ],
)

filegroup(
    name = "files",
    srcs = glob(["*.java"]),
)
