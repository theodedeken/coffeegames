import argparse
import json


def merge(output_file, rgno_files):
    output = []
    for f in rgno_files:
        runs = json.load(open(f, "r"))
        for run_info in runs:
            prefix = run_info["file_prefix"]
            # FIXME: this is temporary until we have a backend
            for f in run_info["files"]:
                full_path = f["file_name"]
                f["content"] = open(full_path, "r").read().splitlines()
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
