import xml.etree.ElementTree as ET
import argparse
import json


def convert(input_file, output_file):
    tree = ET.parse(input_file)
    root = tree.getroot()
    out = {"files": []}
    for file_check in root:
        out_file = {"name": file_check.attrib["name"], "checks": []}
        for error in file_check:
            out_file["checks"].append(error.attrib)
        out["files"].append(out_file)
    json.dump(out, open(output_file, "w"))


if __name__ == "__main__":
    parser = argparse.ArgumentParser(
        description="Convert checkstyle xml to lint output."
    )
    parser.add_argument(
        "checkstyle_input", type=str, help="an integer for the accumulator"
    )
    parser.add_argument(
        "output_json", type=str, help="an integer for the accumulator"
    )

    args = parser.parse_args()

    convert(args.checkstyle_input, args.output_json)