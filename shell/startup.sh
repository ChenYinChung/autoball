#!/usr/bin/env bash

export JAVA_HOME="/usr/java/openjdk11"
export PATH=$JAVA_HOME/bin:$PATH

# -XX:MetaspaceSize=128m （元空间默认大小）
# -XX:MaxMetaspaceSize=128m （元空间最大大小）
# -Xms2g （堆最大大小）
# -Xmx2g （堆默认大小）
# -Xmn1g （新生代大小）
# -Xss256k （棧最大深度大小）
# -XX:SurvivorRatio=8 （新生代分区比例 8:2）
# -Xlog:gc*,gc+ref=info,gc+heap=info,gc+age=trace:file=lottery-cms-gc.log:tags,uptime,time,level:filecount=10,filesize=50m (G1GC for jdk11)
nohup java -XX:MetaspaceSize=512m -XX:MaxMetaspaceSize=512m -Xms6m -Xmx6g -Xmn1g -Xss512k -XX:SurvivorRatio=8 -XX:+UseG1GC -XX:+UseStringDeduplication \
 -Djdk.tls.client.protocols="TLSv1,TLSv1.1,TLSv1.2" \
 -javaagent:/opt/jmx-exporter/jmx_prometheus_javaagent-0.11.0.jar=6007:/opt/jmx-exporter/jmx_exporter.yml \
 -jar ab.jar &