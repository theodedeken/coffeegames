set -o pipefail
{checkstyle} -c {config_file} {files} | sed 's/^.*__main__\///g'