#!/usr/bin/env bash
# Environment Variables
# HUB_HOST
# BROWSER
# MODULE

echo "Checking if hub is ready - $HUB_HOST"

while [ "$( curl -s http://$HUB_HOST:4444/wd/hub/status | jq -r .value.ready )" != "true" ]
do
	sleep 1
done

java -cp selenium-docker.jar:selenium-docker-tests.jar:libs/* -Dcucumber.options="$CUCUMBER_OPTIONS" -DHUB_HOST=$HUB_HOST  org.testng.TestNG -testclass com.runner.TestRunner