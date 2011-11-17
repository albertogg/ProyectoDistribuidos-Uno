#!/bin/bash

lshw -quiet -C cpu | egrep -w 'description|product|vendor|version'; 
lshw -quiet -C memory | egrep -w 'description|size';
hostname;
