import argparse
import json


def merge(output_file, input_files):
    output = []
    for f in input_files:
        output += json.load(open(f, "r"))
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
