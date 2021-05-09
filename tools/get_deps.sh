cd $BUILD_WORKING_DIRECTORY
bazel query "attr(tags, 'RGNO', //...)"