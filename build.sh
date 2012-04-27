#!/bin/bash

git pull

ant release

if [ $? -ne 0 ]
	then echo "build failed!";exit -1
fi

cp -r story /Volumes/AGILE\ BLUE\ 2/
cp classproject.jar  /Volumes/AGILE\ BLUE\ 2/
cp commons-lang-2.6.jar /Volumes/AGILE\ BLUE\ 2/
cp scrumfucius  /Volumes/AGILE\ BLUE\ 2/

chmod 755 /Volumes/AGILE\ BLUE\ 2/scrumfucius

/Volumes/AGILE\ BLUE\ 2/scrumfucius

exit 0
