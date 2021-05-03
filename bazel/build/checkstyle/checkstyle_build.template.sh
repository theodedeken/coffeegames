{checkstyle} -c {config_file} -f xml -o {output} {files}
echo $? > {exit_code}
pwd > {file_prefix}