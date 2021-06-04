#!/bin/bash

pid=$(jps -v | grep JCatalina.jar | awk '{print $1}')
kill -9 $pid
