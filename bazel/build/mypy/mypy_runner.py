from mypy import api
import sys
import re
import os
import json
from tempfile import TemporaryDirectory
from pathlib import Path
import xml.etree.ElementTree as ET

message_re = r"(?P<file_name>.*):(?P<line>[0-9]*):(?P<column>[0-9]*):(?P<severity>.*):(?P<message>.*)\[(?P<source>.*)\]"


def result_to_oregano(
    result: str, exit_code: int, output_file: str, coverage_report: Path
) -> None:
    out = {
        "exit_code": exit_code,
    }
    tree = ET.parse(coverage_report)

    file_dict = {}
    for message in result.split("\n")[:-1]:
        matched = re.match(message_re, message)
        file_name = matched.group("file_name")
        if file_name not in file_dict:
            file_dict[file_name] = {"file_name": file_name, "checks": []}
        check = {
            "line": matched.group("line"),
            "column": matched.group("column"),
            "severity": matched.group("severity").strip(),
            "message": matched.group("message").strip(),
            "source": matched.group("source"),
            "tool": "mypy",
        }
        file_dict[file_name]["checks"].append(check)

    coverage = tree.getroot()
    packages = coverage.find("packages")
    for package in packages:
        for class_el in package.find("classes"):
            file_name = class_el.get("filename")
            lines = []
            for line in class_el.find("lines"):
                hit = {
                    "line": int(line.get("number")),
                    "covered": int(line.get("hits")) > 0,
                }
                lines.append(hit)
            if file_name not in file_dict:
                file_dict[file_name] = {
                    "file_name": file_name,
                    "coverage": {"lines": lines},
                }
            else:
                file_dict[file_name]["coverage"] = {"lines": lines}

    out["files"] = list(file_dict.values())
    json.dump([out], open(output_file, "w"))


if __name__ == "__main__":
    with TemporaryDirectory() as temp_dir:

        sys.argv.append("--show-error-codes")
        sys.argv.append("--show-column-numbers")
        sys.argv.append("--no-error-summary")
        sys.argv.append("--cobertura-xml-report")
        sys.argv.append(temp_dir)
        result = api.run(sys.argv[1:])
        coverage = Path(temp_dir) / "cobertura.xml"
        # Error occurred while running mypy --> exit
        if result[1]:
            print(result[1])
            exit(result[2])

        result_to_oregano(
            result[0], result[2], os.environ["OREGANO_OUTPUT"], coverage
        )
