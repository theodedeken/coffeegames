#!/usr/bin/env bash

set -euo pipefail

cd $(bazel info workspace)

WORKSPACE_NAME=$(basename $PWD)

cat << EOF > ./.project
<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<projectDescription>
	<name>${WORKSPACE_NAME}</name>
	<comment/>
	<projects>
	</projects>
	<buildSpec>
		<buildCommand>
			<name>org.eclipse.jdt.core.javabuilder</name>
			<arguments>
			</arguments>
		</buildCommand>
	</buildSpec>
	<natures>
		<nature>org.eclipse.jdt.core.javanature</nature>
	</natures>
	        <filteredResources>
                <filter>
                        <id>1621190689736</id>
                        <name></name>
                        <type>30</type>
                        <matcher>
                                <id>org.eclipse.core.resources.regexFilterMatcher</id>
                                <arguments>bazel-bin|bazel-coffeegames|bazel-out|bazel-testlogs|node_modules|.git|__CREATED_BY_JAVA_LANGUAGE_SERVER__</arguments>
                        </matcher>
                </filter>
        </filteredResources>
</projectDescription>
EOF

JARS=$(find /home/theod/.cache/bazel/_bazel_theod/b2522fe812c867bbb74443d4c40248b5/external/maven/v1 -iname '*.jar')
SRC_PATHS=$(find . -type d -path '*/src/java' -o -path '*/test/java')
echo $SRC_PATHS
cat << EOF > ./.classpath
<?xml version="1.0" encoding="UTF-8"?>
<classpath>
	<classpathentry kind="con" path="org.eclipse.jdt.launching.JRE_CONTAINER/org.eclipse.jdt.internal.debug.ui.launcher.StandardVMType/JavaSE-1.8"/>
$( for PATH in $SRC_PATHS; do echo "	<classpathentry kind=\"src\" path=\"${PATH:2}\" />"; done; )
	<classpathentry kind="output" path="java-bin"/>
$( for JAR in $JARS; do echo "	<classpathentry kind=\"lib\" path=\"${JAR}\" />"; done; )
</classpath>
EOF