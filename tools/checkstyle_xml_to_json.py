import xml.etree.ElementTree as ET
import argparse
import json


def convert(input_file, output_file):
    tree = ET.parse(input_file)
    root = tree.getroot()
    out = {"project": {}}
    for file_check in root:
        full_path = file_check.attrib["name"].split("__main__/")[-1]
        current = out["project"]
        for path_el in full_path.split("/"):
            if path_el not in current:
                current[path_el] = {}
            current = current[path_el]

        current["checks"] = []
        for error in file_check:
            current["checks"].append(error.attrib)
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