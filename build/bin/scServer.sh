#!/usr/bin/env bash

function findServer(){
    dataNodePid=`ps -ef | grep serverCore |  grep -v "grep" | awk '{print $2}'`
    for id in $dataNodePid
    do
	    echo "find $id"
    done
    echo ${dataNodePid[*]}
}

function stopServer(){
    kill -9 $1
}

function startServer(){
    nohup sudo java -jar core/serverCore.jar > log/SmartCanServer.log &
}

function startService(){
    if [ ! $(findServer) ]; then
     startServer
     echo server started success!
    else
      echo alredy running!
    fi
}



function stopService(){
    arr=$(findServer)
    len=${#arr[@]}
    if [ $len == 0 ]; then
        echo no server started!
    else
        for id in $(findServer)
        do
	        stopServer  $id
        done
        echo stopServer!
    fi
}

if [ "$1" == "start" ]; then
   startService
fi
if [ "$1" == "stop" ]; then
    stopService
fi

if [ "$1" == "restart" ]; then
    stopService
    startServer
fi