#!/usr/bin/env bash

bazel build //games/spaxel:spaxel_pom
install bazel-bin/games/spaxel/spaxel_pom.xml games/spaxel/pom.xml
bazel build //engines/voide:voide_pom
install bazel-bin/engines/voide/voide_pom.xml engines/voide/pom.xml
