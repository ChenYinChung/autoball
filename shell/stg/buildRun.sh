#!/usr/bin/env bash
set -e

rootDir=$pwd;
#./gradlew -i flywayInfo -Penv=prd

#for localhost
#./gradlew --parallel clean build -x test

#for stg
#./gradlew --parallel clean build -Penv=stg -x test

# for prd
cd c:\Nexio\AutoballController
./AutoballController.exe

cd ../../${rootDir}
./gradlew --parallel clean build -Penv=stg -x test

cp build/autoball/libs/autoball.jar ${rootDir}/

java -XX:MetaspaceSize=128m -XX:MaxMetaspaceSize=128m -Xms1g -Xmx1g -Xmn128m -Xss512k -XX:SurvivorRatio=8 \
-XX:+UseG1GC -XX:+UseStringDeduplication -Djdk.tls.client.protocols="TLSv1,TLSv1.1,TLSv1.2" \
-jar autoball.jar
