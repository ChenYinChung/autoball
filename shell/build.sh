#!/usr/bin/env bash
set -e

#./gradlew -i flywayInfo -Penv=prd

#for localhost
#./gradlew --parallel clean build -x test

#for stg
#./gradlew --parallel clean build -Penv=stg -x test

# for prd
./gradlew --parallel clean build -Penv=prd -x test
