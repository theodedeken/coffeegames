import os
import argparse
import subprocess
from typing import List


def autoupdate_file(target_name: str) -> None:
    deps = get_deps()
    depstr = " ".join(deps)

    subprocess.check_output(
        f"buildozer 'set deps {depstr}' {target_name}",
        cwd=os.environ["BUILD_WORKSPACE_DIRECTORY"],
        text=True,
        shell=True,
    )


def get_deps() -> List[str]:
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
        "target_name",
        type=str,
        help="The target name to edit in the build file",
    )
    args = parser.parse_args()
    autoupdate_file(args.target_name)