import argparse
import json

"""
TODO format to go for
SEVERITY(in color): message
 --> file_path
1 |
2 | code things
  |      ^ 
3 |
  = note: 
  = help:


    Bright Black: 
    Bright Red: \u001b[31;1m
    Bright Green: \u001b[32;1m
    Bright Yellow: \u001b[33;1m
    Bright Blue: \u001b[34;1m
    Bright Magenta: \u001b[35;1m
    Bright Cyan: \u001b[36;1m
    Bright White: \u001b[37;1m

    Reset: \u001b[0m

    Black: \u001b[30m
    Red: 
    Green: \u001b[32m
    Yellow: \u001b[33m
    Blue: \u001b[34m
    Magenta: \u001b[35m
    Cyan: \u001b[36m
    White: \u001b[37m

    Reset: \u001b[0m

"""

colors = {
    "yellow": "\u001b[33m",
    "red": "\u001b[31m",
    "bright_black": "\u001b[30;1m",
}


def color_string(string, color):
    return f"{colors[color]}{string}\u001b[0m"


def format_severity(severity):
    if severity == "info":
        return "INFO  "
    elif severity == "warn":
        return "\u001b[33mWARN\u001b[0m  "
    elif severity == "error":
        return "\u001b[31mERROR\u001b[0m "
    else:
        raise RuntimeError("Unknown severity level")


def print_code_excerpt(
    file_path, message, severity, line, spread=1, column=None
):
    lines = open(file_path, "r").readlines()
    start = max(line - spread, 1)
    end = min(line + 2, len(lines) + 1)
    for i in range(start, end):
        print(f"{i: 4}| {lines[i - 1][:-1]}")
        if i == line:
            print_message(message, severity, column)


def print_message(message, severity, column=None):
    if column:
        shifted = " " * (5 + column) + "^ " + message
    else:
        shifted = "   ^  " + message
    if severity == "warn":
        print(color_string(shifted, "yellow"))
    if severity == "error":
        print(color_string(shifted, "red"))


def print_checks(tree, subpath):
    for el in tree:
        if el == "checks":
            file_path = "/".join(subpath)
            for check in tree[el]:
                location = f":{check['line']}"
                if "column" in check:
                    location += f":{check['column']}"
                print(
                    f"{format_severity(check['severity'])}\u001b[30;1m{check['source']}\u001b[0m"
                )
                print(f"  --> \u001b[36m{file_path}{location}\u001b[0m")
                if "column" in check:
                    column = int(check["column"])
                else:
                    column = None
                print_code_excerpt(
                    file_path,
                    check["message"],
                    check["severity"],
                    int(check["line"]),
                    column=column,
                )
                print()

        else:
            print_checks(tree[el], subpath + [el])


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
    checks = json.load(open(args.oregano_file))
    print_checks(checks["tree"], [])
    exit(checks["exit_code"])
