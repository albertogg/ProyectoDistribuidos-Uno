#!/bin/bash

#Hostname
hostname;
#General
lshw -quiet | head -n 9 | egrep -w 'description|width|capabilities' | awk '{$1 = ""; print}';
#Core
lshw -quiet | head -n 12 | tail -n 2 | awk '{$1 = "";print}'; 
#CPU
lshw -quiet -C cpu | egrep -w 'description|product|vendor|version' | awk '{$1 = ""; print}';
#Memory
lshw -quiet -C memory | egrep -w 'description|size' | awk '{$1 = ""; print}';
#Firmware 
lshw -quiet | head -n 19 | tail -n 6 | egrep -w 'description|vendor|version|capabilities' | awk '{$1 = ""; print}';