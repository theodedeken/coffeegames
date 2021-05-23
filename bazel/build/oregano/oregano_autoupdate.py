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
    out: str = subprocess.check_output(
        ["bazel", "query", "attr(tags, 'RGNO', //...)"],
        cwd=os.environ["BUILD_WORKSPACE_DIRECTORY"],
        text=True,
    )
    return list(filter(nonempty, out.split("\n")))


def nonempty(value: str) -> bool:
    return value != ""


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
    target_name: str = args.target_name
    assert isinstance(target_name, str)
    autoupdate_file(target_name)
