import xml.etree.ElementTree as ET
import argparse
import json


def convert(input_file, error_code, file_prefix, output_file):
    tree = ET.parse(input_file)
    root = tree.getroot()
    prefix = open(file_prefix, "r").read()[:-1]
    out = {
        "exit_code": int(open(error_code, "r").read()),
        "file_prefix": prefix,
        "tree": {},
    }
    for file_check in root:
        full_path = file_check.attrib["name"].replace(prefix + "/", "")
        current = out["tree"]
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
        "checkstyle_input",
        type=str,
        help="The checkstyle input file in xml format",
    )
    parser.add_argument(
        "error_code",
        type=str,
        help="The file containing the exit code of the checkstyle run",
    )
    parser.add_argument(
        "file_prefix",
        type=str,
        help="The file containing the file prefix of the source files",
    )
    parser.add_argument(
        "output_json", type=str, help="The output oregano json"
    )

    args = parser.parse_args()

    convert(
        args.checkstyle_input,
        args.error_code,
        args.file_prefix,
        args.output_json,
    )
