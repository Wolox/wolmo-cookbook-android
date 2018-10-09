#!/bin/bash +x
./scripts/keystore.sh
./gradlew clean build check testStageDebugUnitTestCoverage --stacktrace
build_status=$?
if [ "$build_status" -ne "0" ]
then
	exit $build_status
fi
if [ -z "$*" ]
then
	echo "No CodeStats token argument provided"
	exit $build_status
else
	echo "Reporting to CodeStats with token: $1"
fi
rm Gemfile.lock
bundle install
CODE_STATS_TOKEN=$1 ghprbSourceBranch=$(echo $GIT_BRANCH | cut -d/ -f 2) bundle exec codestats-metrics-reporter
exit $build_status
