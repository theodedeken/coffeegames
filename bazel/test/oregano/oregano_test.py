import argparse
import json

if __name__ == "__main__":
    parser = argparse.ArgumentParser(
        description="Check if a Oregano file passed its checks."
    )
    parser.add_argument(
        "oregano_file",
        type=str,
        help="The file to check",
    )
    args = parser.parse_args()
    exit(json.load(open(args.oregano_file))["exit_code"])