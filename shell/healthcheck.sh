#!/usr/bin/env bash

while :
do

  IP=$(curl -LI http://localhost:8008/actuator/health -o /dev/null -w '%{http_code}\n' -s)
  if [ $IP -eq "200" ]
  then
    echo "`date` Server is running mode $IP"
    break;
  else
    sleep 1
    echo "`date` Server still in starting mode $IP"
  fi
done