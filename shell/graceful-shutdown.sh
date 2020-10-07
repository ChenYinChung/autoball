#!/usr/bin/env bash

curl -X POST http://localhost:8008/actuator/shutdown

while :
do

  IP=$(curl -LI http://localhost:8008/actuator/health -o /dev/null -w '%{http_code}\n' -s)
  if [ $IP -eq "000" ]
  then
    echo "`date` Server is graceful shutdown status $IP"
    break;
  else
    sleep 1
    echo "`date` Server still running status $IP"
  fi
done