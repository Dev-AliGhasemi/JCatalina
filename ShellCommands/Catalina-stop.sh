#!/bin/bash

pid=$(jps -v | grep Catalina.jar | awk '{print $1}')
kill -9 $pid
