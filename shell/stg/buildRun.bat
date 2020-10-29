cd c:/Users/sun/IdeaProjects/autoball/

gradlew.bat --parallel clean build -Penv=stg -x test


cd c:/Nexio/AutoBallController/
start AutoBallController.exe

cd c:/Users/sun/IdeaProjects/autoball/build/autoball/libs

start java -XX:MetaspaceSize=128m -XX:MaxMetaspaceSize=128m -Xms1g -Xmx1g -Xmn128m -Xss512k -XX:SurvivorRatio=8 -XX:+UseG1GC -XX:+UseStringDeduplication -Djdk.tls.client.protocols="TLSv1,TLSv1.1,TLSv1.2" -jar autoball.jar