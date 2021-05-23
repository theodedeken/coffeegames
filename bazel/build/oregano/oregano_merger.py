import argparse
import json
from typing import List


def merge(output_file: str, rgno_files: List[str]) -> None:
    output = []
    for rgno_file in rgno_files:
        runs = json.load(open(rgno_file, "r"))
        for run_info in runs:
            # FIXME: this is temporary until we have a backend
            for run_file in run_info["files"]:
                full_path = run_file["file_name"]
                run_file["content"] = open(full_path, "r").read().splitlines()
            output.append(run_info)
    json.dump(output, open(output_file, "w"))


if __name__ == "__main__":
    parser = argparse.ArgumentParser(
        description="Merge oregano files into one big file"
    )
    parser.add_argument(
        "output_file",
        type=str,
        help="The output oregano file",
    )
    parser.add_argument(
        "--rgno",
        action="append",
        type=str,
        help="The oregano files",
    )

    args = parser.parse_args()
    merge(args.output_file, args.rgno)
