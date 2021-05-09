import os
import argparse
import subprocess


def autoupdate_file(build_file, target_name):
    deps = get_deps()
    write_oregano_rule(target_name, deps, True)


def write_oregano_rule(name, deps, autoupdate):
    print(f"oregano(")
    print(f"    name = {name},")
    print(f"    autoupdate = {autoupdate},")
    print(f"    deps = [")
    for dep in sorted(deps):
        print(" " * 8 + f'"{dep}",')
    print(f"    ],")
    print(f")")


def get_deps():
    print(os.environ["BUILD_WORKSPACE_DIRECTORY"])
    out = subprocess.check_output(
        ["bazel", "query", "attr(tags, 'RGNO', //...)"],
        cwd=os.environ["BUILD_WORKSPACE_DIRECTORY"],
        text=True,
    )
    return list(filter(lambda el: el, out.split("\n")))


if __name__ == "__main__":
    parser = argparse.ArgumentParser(
        description="Autoupdate build files with all dependencies to rgno files"
    )
    parser.add_argument(
        "build_file",
        type=str,
        help="The build file to edit",
    )
    parser.add_argument(
        "target_name",
        type=str,
        help="The target name to edit in the build file",
    )
    args = parser.parse_args()
    autoupdate_file(args.build_file, args.target_name)