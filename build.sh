#!/bin/bash

git pull

ant release

if [ $? -ne 0 ]
	then echo "build failed!";exit -1
fi

cp story.txt /Volumes/AGILE\ BLUE/
cp classproject.jar  /Volumes/AGILE\ BLUE/
cp scrumfucius  /Volumes/AGILE\ BLUE/

chmod 755 /Volumes/AGILE\ BLUE/scrumfucius

/Volumes/AGILE\ BLUE/scrumfucius

exit 0